package com.linkable.linkable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.linkable.linkable.Category;
import com.linkable.linkable.R;
import com.linkable.linkable.adapter.CategoryRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.LoginActivity.token;
import static com.linkable.linkable.activity.MainActivity.URL;

public class CategoryActivity extends AppCompatActivity {
    private CategoryRecyclerAdapter adapter;
    static public String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        category();

        RecyclerView categoryRecyclerView = findViewById(R.id.categoryRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CategoryRecyclerAdapter();
        categoryRecyclerView.setAdapter(adapter);

        // 탭바
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_category);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.menu_gauge:
                        intent = new Intent(getApplicationContext(), GaugeActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.menu_category:
                        //intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        //startActivity(intent);
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
                        intent = new Intent(getApplicationContext(), MybooksActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    public void category() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call<List<Category>> call = retrofitService.category("Token "+ token);
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.i("h", response.headers().get("list"));
                selected = response.headers().get("list");
                List<Category> repo = response.body();
                for(Category rep: repo) {
                    adapter.addItem(rep);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.i("adsf", "adfadsf");
            }
        });
    }
}
