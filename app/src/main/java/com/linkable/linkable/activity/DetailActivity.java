package com.linkable.linkable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.linkable.linkable.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImage = findViewById(R.id.detailImage);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView authorTextView = findViewById(R.id.authorTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        int bookid = intent.getIntExtra("bookid", -1);
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String description = intent.getStringExtra("description");
        String imageurl = intent.getStringExtra("imageurl");


        titleTextView.setText(title);
        authorTextView.setText(author);
        Glide.with(detailImage.getContext()).load(imageurl).into(detailImage);
        descriptionTextView.setText("~책 소개~");
    }
}