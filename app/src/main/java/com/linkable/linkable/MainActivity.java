package com.linkable.linkable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;

    static final String URL = "http://10.91.35.108:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView)findViewById(R.id.textView3);
        textView2 = (TextView)findViewById(R.id.textView6);
        book();
    }

    public void book() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call<Data> call = retrofitService.gettitle("1");
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data repo = response.body();
                textView1.setText(repo.getAutor());
                textView2.setText(repo.getLocation());
//                Log.i("adsf", "onResponse: "+repo.getTitle());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.i("adsf","adfadsf");
            }
        });
    }
}
