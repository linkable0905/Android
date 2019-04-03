package com.linkable.linkable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    ImageView imageView;
    String imageURL;
    Bitmap bitmap;
    Handler handler;

    static final String URL = "http://10.91.48.96:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        textView1 = (TextView)findViewById(R.id.bestTitleTextView);
        textView2 = (TextView)findViewById(R.id.bestAuthorTextView);
        imageView = (ImageView)findViewById(R.id.bestbookImage);
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
                textView1.setText(repo.getTitle());
                textView2.setText(repo.getAutor());
                imageURL = repo.getImagesource();
                //Log.i("qwer", "onResponse: "+repo.getImagesource());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.i("adsf","adfadsf");
            }
        });



        // url에서 이미지

        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageURL);
                    Log.i("zxcv", "url: "+ url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                    /*handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                    imageView.setImageBitmap(bitmap);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        mThread.start();

        try {
            mThread.join();
            Log.i("zxcv", "asdsgsgrd" + bitmap);
            imageView.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
