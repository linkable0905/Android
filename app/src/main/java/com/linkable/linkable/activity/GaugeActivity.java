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
import android.view.View;
import android.widget.Button;

import com.linkable.linkable.Category;
import com.linkable.linkable.Data;
import com.linkable.linkable.adapter.BookListRecyclerAdapter;
import com.linkable.linkable.adapter.GaugeCategoryRecyclerAdapter;
import com.linkable.linkable.adapter.GaugeRecyclerAdapter;
import com.linkable.linkable.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.CategoryActivity.selected;
import static com.linkable.linkable.activity.LoginActivity.token;
import static com.linkable.linkable.activity.MainActivity.URL;
import static com.linkable.linkable.adapter.GaugeRecyclerAdapter.gaugeList;

public class GaugeActivity extends AppCompatActivity {
    private GaugeRecyclerAdapter adapter2;
    private GaugeCategoryRecyclerAdapter adapter1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauge);

        RecyclerView cardRecyclerView = findViewById(R.id.gaugeRecyclerView);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        cardRecyclerView.setLayoutManager(linearLayoutManager1);

        adapter1 = new GaugeCategoryRecyclerAdapter();
        cardRecyclerView.setAdapter(adapter1);

        RecyclerView gaugeListRV = findViewById(R.id.gaugeListRecyclerView);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        gaugeListRV.setLayoutManager(linearLayoutManager2);

        adapter2 = new GaugeRecyclerAdapter();
        cardRecyclerView.setAdapter(adapter2);

        myCategory();

        // 탭바
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_card);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.menu_gauge:
                        //intent = new Intent(getApplicationContext(), GaugeActivity.class);
                        //startActivity(intent);
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
                        intent = new Intent(getApplicationContext(), MybooksActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                }
                return false;
            }
        });
/*
        // 버튼
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
                Call<List<Data>> call = retrofitService.gauge("Token "+ token, gaugeList);
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
        });*/
    }

    public void myCategory() {
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
                    String[] nums = selected.split(" ");
                    for (String i : nums) {
                        if (Integer.parseInt(i) == rep.getId()) {
                            Log.i("selectedid", i);
                            adapter2.addItem(rep);
                        }
                    }
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.i("adsf", "adfadsf");
            }
        });
    }
}
