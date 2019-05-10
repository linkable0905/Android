package com.linkable.linkable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.linkable.linkable.Data;
import com.linkable.linkable.R;
import com.linkable.linkable.User;

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

import static com.linkable.linkable.activity.LoginActivity.token;
import static com.linkable.linkable.activity.MainActivity.URL;

public class DetailActivity extends AppCompatActivity {
    int pk;
    ImageView detailImage;
    TextView titleTextView;
    TextView authorTextView;
    TextView descriptionTextView;
    Button containbutton;
    Button buybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImage = findViewById(R.id.detailImage);
        titleTextView = findViewById(R.id.titleTextView);
        authorTextView = findViewById(R.id.authorTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        containbutton = findViewById(R.id.containButton);
        buybutton = findViewById(R.id.buyButton); //임시로 만들었습니다.
        containbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBook();
            }
        });

        buybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MybooksActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });

        Intent intent = getIntent();
        pk = intent.getIntExtra("index",-1);
        bookInfo();

    }

    public void bookInfo() {
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

    public void addBook(){
        Log.i("token",token);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call call = retrofitService.addMyBook("Token "+token,pk+1,pk+1);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    Log.i("addbook", "추가됨");
                    Toast.makeText(getApplicationContext(), "내 책에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.i("addbook", "실패");
                    Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}