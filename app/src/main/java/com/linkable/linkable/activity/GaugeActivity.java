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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.linkable.linkable.Category;
import com.linkable.linkable.Data;
import com.linkable.linkable.adapter.GaugeCategoryRecyclerAdapter;
import com.linkable.linkable.adapter.GaugeRecyclerAdapter;
import com.linkable.linkable.R;
import com.linkable.linkable.adapter.RecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.CategoryActivity.selected;
import static com.linkable.linkable.activity.LoginActivity.token;
import static com.linkable.linkable.activity.MainActivity.URL;
//import static com.linkable.linkable.adapter.GaugeRecyclerAdapter.gaugeList;

public class GaugeActivity extends AppCompatActivity {
    private GaugeRecyclerAdapter adapter2;
    private GaugeCategoryRecyclerAdapter adapter1;
    Button button;
    String gaugeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauge);

        gaugeList = "";

        final RecyclerView gaugeRecyclerView = findViewById(R.id.gaugeListRecyclerView);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        gaugeRecyclerView.setLayoutManager(linearLayoutManager1);

        myCategory();
        adapter1 = new GaugeCategoryRecyclerAdapter();
        gaugeRecyclerView.setAdapter(adapter1);

        final RecyclerView gaugeListRV = findViewById(R.id.gaugeRecyclerView);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        gaugeListRV.setLayoutManager(linearLayoutManager2);

        adapter2 = new GaugeRecyclerAdapter();
        gaugeListRV.setAdapter(adapter2);


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
        // 버튼
        button = findViewById(R.id.gaugeButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View view2 = gaugeRecyclerView.getLayoutManager().findViewByPosition(0);
                int cnt = gaugeRecyclerView.getChildCount();
                for (int i = 0; i < cnt; i++) {
                    //View view = gaugeRecyclerView.getLayoutManager().findViewByPosition(i);
                            //gaugeRecyclerView.getChildAt(i);
                    //RecyclerView.ViewHolder childViewHolder = gaugeRecyclerView.getChildViewHolder(view);

                    //String str = ((TextView) gaugeRecyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.textView2)).getText().toString();
                    //Log.i("qwas", str);
                    //SeekBar seekBar = findViewById(R.id.gaugeBar);
                    //gaugeList = gaugeList + seekBar.getPmyrogress() + " ";
                    //TextView textView = findViewById(R.id.textView2);
                    //gaugeList = gaugeList + str + " ";
                }
                Log.i("qwas", "" + gaugeList);
                gaugeList="10 20 30 ";
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
                        for (Data rep: repo) {
                            adapter1.addItem(rep);
                        }
                        adapter1.notifyDataSetChanged();

                        ViewGroup.LayoutParams categoryParams = gaugeListRV.getLayoutParams();
                        categoryParams.height = 0;
                        categoryParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        gaugeListRV.setLayoutParams(categoryParams);
                        gaugeListRV.setVisibility(View.INVISIBLE);

                        button.setVisibility(View.INVISIBLE);
                        button.setEnabled(false);

                        ScrollView scrollView = findViewById(R.id.listScrollView);
                        ViewGroup.LayoutParams listParams = scrollView.getLayoutParams();
                        listParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                        listParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        scrollView.setLayoutParams(listParams);
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {
                        Log.i("adsf", "adfadsf");
                    }
                });
            }
        });
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

                String[] strnums = selected.split(" ");
                int[] nums = new int[strnums.length];
                for (int i = 0; i < strnums.length; i++)
                     nums[i] = Integer.parseInt(strnums[i]);
                sort(nums);

                List<Category> repo = response.body();
                for(Category rep: repo) {
                    //String[] nums = selected.split(" ");
                    for (int i : nums) {
                        if (i == rep.getId()) {
                            Log.i("selectedid", "" + i);
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

    public void sort(int[] data){
        int size = data.length;
        int min; //최소값을 가진 데이터의 인덱스 저장 변수
        int temp;

        for(int i = 0; i < size - 1; i++){ // size-1 : 마지막 요소는 자연스럽게 정렬됨
            min = i;
            for(int j = i + 1; j < size; j++){
                if(data[min] > data[j])
                    min = j;
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
    }
}
