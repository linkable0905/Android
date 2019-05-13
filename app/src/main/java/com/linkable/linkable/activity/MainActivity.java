package com.linkable.linkable.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.linkable.linkable.Data;
import com.linkable.linkable.R;
import com.linkable.linkable.adapter.RecommendRecyclerAdapter;
import com.linkable.linkable.adapter.RecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.LoginActivity.token;

public class MainActivity extends AppCompatActivity {
    TextView best;
    TextView recommend;
    RecyclerView bestRecyclerView;
    RecyclerView recommendRecyclerView;

    private RecyclerAdapter adapter1;
    private RecommendRecyclerAdapter adapter2;

    static public final String URL = "http://10.91.212.129:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 베스트 목록, 추천 책 목록
        best = (TextView)findViewById(R.id.bestTextView);
        recommend = (TextView)findViewById(R.id.recommendTextView);

        best.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookListActivity.class);
                intent.putExtra("str",best.getText().toString());
                v.getContext().startActivity(intent);
            }
        });

        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookListActivity.class);
                intent.putExtra("str",recommend.getText().toString());
                v.getContext().startActivity(intent);
            }
        });

        // 베스트셀러
        bestBook();

        bestRecyclerView = findViewById(R.id.bestRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        bestRecyclerView.setLayoutManager(linearLayoutManager);

        adapter1 = new RecyclerAdapter();
        bestRecyclerView.setAdapter(adapter1);

        // 추천 책
        recommendRecyclerView = findViewById(R.id.recommendRecyclerView);

        LinearLayoutManager rvLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recommendRecyclerView.setLayoutManager(rvLinearLayoutManager);

        adapter2 = new RecommendRecyclerAdapter();
        recommendRecyclerView.setAdapter(adapter2);

        recommendBook();

        // 탭바
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.menu_gauge:
                        intent = new Intent(getApplicationContext(), GaugeActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.menu_category:
                        intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.menu_home:
                        //intent = new Intent(getApplicationContext(), MainActivity.class);
                        //startActivity(intent);
                        return true;

                    case R.id.menu_search:
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.menu_mybooks:
                        intent = new Intent(getApplicationContext(), MybooksActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        // swipe

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.mainSwipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bestBook();
                adapter1 = new RecyclerAdapter();
                bestRecyclerView.setAdapter(adapter1);

                recommendBook();
                adapter2 = new RecommendRecyclerAdapter();
                recommendRecyclerView.setAdapter(adapter2);

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void bestBook() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call<List<Data>> call = retrofitService.best5();
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                List<Data> repo = response.body();
                for(Data rep: repo){
                    adapter1.addItem(rep);
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.i("adsf", "adfadsf");
            }
        });
    }

    public void recommendBook() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call<List<Data>> call = retrofitService.recommend("Token "+token);
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                List<Data> repo = response.body();
                for(Data rep: repo){
                    adapter2.addItem(rep);
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.i("adsf", "adfadsf");
            }
        });
    }
}