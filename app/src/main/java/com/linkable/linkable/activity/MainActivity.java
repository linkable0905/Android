package com.linkable.linkable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkable.linkable.Data;
import com.linkable.linkable.R;
import com.linkable.linkable.adapter.RecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView authorTextView;
    ImageView bookImageView;
    private RecyclerAdapter adapter1;
    private RecyclerAdapter adapter2;
    

    String imageURL;

    static final String URL = "http://10.91.124.15:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageURL = "";

        bookImageView = (ImageView)findViewById(R.id.bookImage);
        titleTextView = (TextView)findViewById(R.id.bookTitleTextView);
        authorTextView = (TextView)findViewById(R.id.bookAuthorTextView);

        // 베스트셀러
        bestBook();

        RecyclerView bestRecyclerView = findViewById(R.id.bestRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        bestRecyclerView.setLayoutManager(linearLayoutManager);

        adapter1 = new RecyclerAdapter();
        bestRecyclerView.setAdapter(adapter1);

        // 추천 책
        RecyclerView recommendRecyclerView = findViewById(R.id.recommendRecyclerView);

        LinearLayoutManager rvLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recommendRecyclerView.setLayoutManager(rvLinearLayoutManager);

        adapter2 = new RecyclerAdapter();
        recommendRecyclerView.setAdapter(adapter2);

        recommendBook();
    }

    public void bestBook() {
        Retrofit retrofit = new Retrofit.Builder()
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
        Call<List<Data>> call = retrofitService.recommend("1");
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