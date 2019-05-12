package com.linkable.linkable.activity;

import android.content.Intent;
import android.net.Uri;
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
    int node;
    String exist;

    ImageView detailImage;
    TextView titleTextView;
    TextView authorTextView;
    TextView descriptionTextView;
    Button containButton;
    Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImage = findViewById(R.id.detailImage);
        titleTextView = findViewById(R.id.titleTextView);
        authorTextView = findViewById(R.id.authorTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        containButton = findViewById(R.id.containButton);
        buyButton = findViewById(R.id.buyButton); //임시로 만들었습니다.

        containButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (containButton.getText().toString()){
                    case "담기": addBook(); containButton.setText("삭제"); break;
                    case "삭제": deleteBook(); containButton.setText("담기"); break;
                    default: break;
                }
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.yes24.com/Product/Goods/"+ node);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        node = intent.getIntExtra("node",-1);
        bookInfo();
    }

    public void bookInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call call = retrofitService.book("Token " + token, node);
        call.enqueue(new Callback <Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Log.i("hh",response.headers().get("exist"));
                exist = response.headers().get("exist");
                Data repo = response.body();
                titleTextView.setText(repo.getTitle());
                authorTextView.setText(repo.getAutor());
                Glide.with(detailImage.getContext()).load(repo.getImagesource()).into(detailImage);
                descriptionTextView.setText(repo.getDiscription());

                if (exist.equals("true")) containButton.setText("삭제");
                else
                    containButton.setText("담기");
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
        Call call = retrofitService.addMyBook("Token " + token, node);
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

    public void deleteBook() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call call = retrofitService.deleteMyBook("Token " + token, node);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    Log.i("deletebook", "삭제됨");
                    Toast.makeText(getApplicationContext(), "내 책에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.i("deletebook", "실패");
                    Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}