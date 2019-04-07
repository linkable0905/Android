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

    TextView titleTextView1;
    TextView authorTextView1;
    ImageView bookImageView1;

    TextView titleTextView2;
    TextView authorTextView2;
    ImageView bookImageView2;

    TextView titleTextView3;
    TextView authorTextView3;
    ImageView bookImageView3;

    TextView titleTextView4;
    TextView authorTextView4;
    ImageView bookImageView4;

    TextView titleTextView5;
    TextView authorTextView5;
    ImageView bookImageView5;

    TextView titleTextView6;
    TextView authorTextView6;
    ImageView bookImageView6;

    TextView titleTextView7;
    TextView authorTextView7;
    ImageView bookImageView7;

    TextView titleTextView8;
    TextView authorTextView8;
    ImageView bookImageView8;

    TextView titleTextView9;
    TextView authorTextView9;
    ImageView bookImageView9;

    TextView titleTextView10;
    TextView authorTextView10;
    ImageView bookImageView10;

    String imageURL;
    Bitmap bitmap;

    static final String URL = "http://10.91.102.85:8000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*titleTextView = new TextView[10];
        authorTextView = new TextView[10];
        bookImageView = new ImageView[10];

        j = 1;
        bookImageView[0] = (ImageView)findViewById(R.id.bookImage1);
        titleTextView[0] = (TextView)findViewById(R.id.bookTitleTextView1);
        authorTextView[0] = (TextView)findViewById(R.id.bookAuthorTextView1);
        book();
        j++;

        bookImageView[1] = (ImageView)findViewById(R.id.bookImage2);
        titleTextView[1] = (TextView)findViewById(R.id.bookTitleTextView2);
        authorTextView[1] = (TextView)findViewById(R.id.bookAuthorTextView2);
        book();
        j++;

        bookImageView[2] = (ImageView)findViewById(R.id.bookImage3);
        titleTextView[2] = (TextView)findViewById(R.id.bookTitleTextView3);
        authorTextView[2] = (TextView)findViewById(R.id.bookAuthorTextView3);
        book();
        j++;

        bookImageView[3] = (ImageView)findViewById(R.id.bookImage4);
        titleTextView[3] = (TextView)findViewById(R.id.bookTitleTextView4);
        authorTextView[3] = (TextView)findViewById(R.id.bookAuthorTextView4);
        book();
        j++;

        bookImageView[4] = (ImageView)findViewById(R.id.bookImage5);
        titleTextView[4] = (TextView)findViewById(R.id.bookTitleTextView5);
        authorTextView[4] = (TextView)findViewById(R.id.bookAuthorTextView5);
        book();
        j++;

        bookImageView[5] = (ImageView)findViewById(R.id.bookImage6);
        titleTextView[5] = (TextView)findViewById(R.id.bookTitleTextView6);
        authorTextView[5] = (TextView)findViewById(R.id.bookAuthorTextView6);
        book();
        j++;

        bookImageView[6] = (ImageView)findViewById(R.id.bookImage7);
        titleTextView[6] = (TextView)findViewById(R.id.bookTitleTextView7);
        authorTextView[6] = (TextView)findViewById(R.id.bookAuthorTextView7);
        book();
        j++;

        bookImageView[7] = (ImageView)findViewById(R.id.bookImage8);
        titleTextView[7] = (TextView)findViewById(R.id.bookTitleTextView8);
        authorTextView[7] = (TextView)findViewById(R.id.bookAuthorTextView8);
        book();
        j++;

        bookImageView[8] = (ImageView)findViewById(R.id.bookImage9);
        titleTextView[8] = (TextView)findViewById(R.id.bookTitleTextView9);
        authorTextView[8] = (TextView)findViewById(R.id.bookAuthorTextView9);
        book();
        j++;

        bookImageView[9] = (ImageView)findViewById(R.id.bookImage10);
        titleTextView[9] = (TextView)findViewById(R.id.bookTitleTextView10);
        authorTextView[9] = (TextView)findViewById(R.id.bookAuthorTextView10);
        book();*/

        imageURL = "";

        bookImageView1 = (ImageView)findViewById(R.id.bookImage1);
        titleTextView1 = (TextView)findViewById(R.id.bookTitleTextView1);
        authorTextView1 = (TextView)findViewById(R.id.bookAuthorTextView1);
        book();

        bookImageView2 = (ImageView)findViewById(R.id.bookImage2);
        titleTextView2 = (TextView)findViewById(R.id.bookTitleTextView2);
        authorTextView2 = (TextView)findViewById(R.id.bookAuthorTextView2);
        book();

        bookImageView3 = (ImageView)findViewById(R.id.bookImage3);
        titleTextView3 = (TextView)findViewById(R.id.bookTitleTextView3);
        authorTextView3 = (TextView)findViewById(R.id.bookAuthorTextView3);
        book();

        bookImageView4 = (ImageView)findViewById(R.id.bookImage4);
        titleTextView4 = (TextView)findViewById(R.id.bookTitleTextView4);
        authorTextView4 = (TextView)findViewById(R.id.bookAuthorTextView4);
        book();

        bookImageView5 = (ImageView)findViewById(R.id.bookImage5);
        titleTextView5 = (TextView)findViewById(R.id.bookTitleTextView5);
        authorTextView5 = (TextView)findViewById(R.id.bookAuthorTextView5);
        book();

        bookImageView6 = (ImageView)findViewById(R.id.bookImage6);
        titleTextView6 = (TextView)findViewById(R.id.bookTitleTextView6);
        authorTextView6 = (TextView)findViewById(R.id.bookAuthorTextView6);
        book();

        bookImageView7 = (ImageView)findViewById(R.id.bookImage7);
        titleTextView7 = (TextView)findViewById(R.id.bookTitleTextView7);
        authorTextView7 = (TextView)findViewById(R.id.bookAuthorTextView7);
        book();

        bookImageView8 = (ImageView)findViewById(R.id.bookImage8);
        titleTextView8 = (TextView)findViewById(R.id.bookTitleTextView8);
        authorTextView8 = (TextView)findViewById(R.id.bookAuthorTextView8);
        book();

        bookImageView9 = (ImageView)findViewById(R.id.bookImage9);
        titleTextView9 = (TextView)findViewById(R.id.bookTitleTextView9);
        authorTextView9 = (TextView)findViewById(R.id.bookAuthorTextView9);
        book();

        bookImageView10 = (ImageView)findViewById(R.id.bookImage10);
        titleTextView10 = (TextView)findViewById(R.id.bookTitleTextView10);
        authorTextView10 = (TextView)findViewById(R.id.bookAuthorTextView10);
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
                    titleTextView1.setText(repo.getTitle());
                    authorTextView1.setText(repo.getAutor());
                    imageURL = repo.getImagesource();
                    Log.i("qwer", "title: " + repo.getTitle());
                    Log.i("qwer", "title: " + repo.getImagesource());
                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.i("adsf", "adfadsf");
                }
            });


            // url에서 이미지

            Thread mThread = new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(imageURL);
                        Log.i("zxcv", "url: " + url);
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
                bookImageView1.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
