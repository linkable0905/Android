package com.linkable.linkable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import static com.linkable.linkable.activity.LoginActivity.token;
import static com.linkable.linkable.activity.MainActivity.URL;

import com.linkable.linkable.Data;
import com.linkable.linkable.R;
import com.linkable.linkable.adapter.MyBooksRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MybooksActivity extends AppCompatActivity {
    private MyBooksRecyclerAdapter adapter;
    RecyclerView mybooksRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooks);

        myBooks();

        mybooksRecyclerView = findViewById(R.id.mybooksRecyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        mybooksRecyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MyBooksRecyclerAdapter();
        mybooksRecyclerView.setAdapter(adapter);

        // 탭바
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_mybooks);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.menu_cardnews:
                        intent = new Intent(getApplicationContext(), CardActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.menu_category:
                        intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.menu_home:
                        finish();
                        return true;

                    case R.id.menu_search:
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.menu_mybooks:
                        //intent = new Intent(getApplicationContext(), MybooksActivity.class);
                        //startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        // swipe

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.mybooksSwipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myBooks();
                adapter = new MyBooksRecyclerAdapter();
                mybooksRecyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void myBooks() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call<List<Data>> call = retrofitService.mybook("Token "+token);
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                List<Data> repo = response.body();
                for(Data rep: repo){
                    adapter.addItem(rep);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.i("adsf", "adfadsf");
            }
        });
    }
}