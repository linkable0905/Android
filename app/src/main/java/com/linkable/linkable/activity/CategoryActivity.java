package com.linkable.linkable.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.linkable.linkable.Data;
import com.linkable.linkable.R;
import com.linkable.linkable.adapter.CategoryRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.MainActivity.URL;

public class CategoryActivity extends AppCompatActivity {
    private CategoryRecyclerAdapter adapter;

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
    }

    public void category() {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call<List<Data>> call = retrofitService.index();
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
        });*/
    }
}
