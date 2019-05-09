package com.linkable.linkable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linkable.linkable.Data;
import com.linkable.linkable.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.MainActivity.URL;

public class DetailActivity extends AppCompatActivity {
    int pk;
    ImageView detailImage;
    TextView titleTextView;
    TextView authorTextView;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImage = findViewById(R.id.detailImage);
        titleTextView = findViewById(R.id.titleTextView);
        authorTextView = findViewById(R.id.authorTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        pk = intent.getIntExtra("index",-1);
        BookInfo();

    }

    public void BookInfo() {
        /*Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("authorization", token)
                        .build();
                return chain.proceed(newRequest);
            }
        };
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(interceptor);
        OkHttpClient okHttpClient = okHttpBuilder.build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call call = retrofitService.book(pk+1);
        call.enqueue(new Callback <Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data repo = response.body();
                titleTextView.setText(repo.getTitle());
                authorTextView.setText(repo.getAutor());
                Glide.with(detailImage.getContext()).load(repo.getImagesource()).into(detailImage);
                descriptionTextView.setText(repo.getDiscription());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}