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
import android.widget.EditText;
import android.widget.ImageView;

import com.linkable.linkable.Data;
import com.linkable.linkable.R;
import com.linkable.linkable.adapter.SearchRecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.linkable.linkable.activity.MainActivity.URL;

public class SearchActivity extends AppCompatActivity {
    private SearchRecyclerAdapter adapter;
    EditText search;
    ImageView searchbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = (EditText) findViewById(R.id.editText);
        searchbutton = (ImageView) findViewById(R.id.search_button);


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String querry = search.getText().toString();
                search(querry);
                RecyclerView bestRecyclerView = findViewById(R.id.searchRecyclerView);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

                bestRecyclerView.setLayoutManager(linearLayoutManager);

                adapter = new SearchRecyclerAdapter();
                bestRecyclerView.setAdapter(adapter);
            }
        });

        // 탭바
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_search);
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
                        intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.menu_home:
                        finish();
                        return true;

                    case R.id.menu_search:
                        //intent = new Intent(getApplicationContext(), SearchActivity.class);
                        //startActivity(intent);
                        //return true;
                        break;

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

    public void search(String s) {
        Log.i("asdf","asdf");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call<List<Data>> call = retrofitService.search(s);
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