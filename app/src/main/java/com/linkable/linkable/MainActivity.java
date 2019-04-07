package com.linkable.linkable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    TextView titleTextView;
    TextView authorTextView;
    ImageView bookImageView;
    String imageURL;
    Bitmap bitmap;
    InflatedBookLayout bookLayout;

    static final String URL = "http://10.91.102.85:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout bestList = (LinearLayout)findViewById(R.id.bestList);

        //bookImageView = (ImageView)findViewById(R.id.bookImage);
        //titleTextView = (TextView)findViewById(R.id.bookTitleTextView);
        //authorTextView = (TextView)findViewById(R.id.bookAuthorTextView);
        imageURL = "http://image.yes24.com/momo/TopCate1833/MidCate006/183255693.jpg";

        //

        for (int i = 1; i <= 5; i++) {
            bookLayout = new InflatedBookLayout(getApplicationContext());

            bookImageView = (ImageView)bookLayout.findViewById(R.id.bookImage);
            titleTextView = (TextView)bookLayout.findViewById(R.id.bookTitleTextView);
            authorTextView = (TextView)bookLayout.findViewById(R.id.bookAuthorTextView);
            //imageURL = "http://image.yes24.com/momo/TopCate1833/MidCate006/183255693.jpg";

            book(Integer.toString(i));

            bestList.addView(bookLayout);
        }

        for (int i = 0; i < 5; i++) {
            bookLayout = new InflatedBookLayout(getApplicationContext());
            LinearLayout recommendList = (LinearLayout) findViewById(R.id.recommendList);
            recommendList.addView(bookLayout);
        }
        //
        //book();
    }

    public void book(String num) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitExService retrofitService = retrofit.create(RetrofitExService.class);
        Call<Data> call = retrofitService.gettitle(num);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data repo = response.body();
                titleTextView.setText(repo.getTitle());
                authorTextView.setText(repo.getAutor());
                imageURL = repo.getImagesource();
                Log.i("qwer", "onResponse: "+repo.getTitle());
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        mThread.start();

        try {
            mThread.join();
            Log.i("zxcv", "asdsgsgrd" + bitmap);
            bookImageView.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
