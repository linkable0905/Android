package com.linkable.linkable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.linkable.linkable.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 탭바
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.menu_cardnews:
                        intent = new Intent(getApplicationContext(), CardActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.menu_category:
                        intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.menu_home:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    case R.id.menu_search:
                        //intent = new Intent(getApplicationContext(), SearchActivity.class);
                        //startActivity(intent);
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
    }
}