package com.linkable.linkable.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.linkable.linkable.R;
import com.linkable.linkable.adapter.MyBooksRecyclerAdapter;

public class MybooksActivity extends AppCompatActivity {
    private MyBooksRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooks);

        myBooks();

        RecyclerView mybooksRecyclerView = findViewById(R.id.mybooksRecyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        mybooksRecyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MyBooksRecyclerAdapter();
        mybooksRecyclerView.setAdapter(adapter);
    }

    public void myBooks() {
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