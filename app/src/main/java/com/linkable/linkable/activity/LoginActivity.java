package com.linkable.linkable.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.linkable.linkable.R;
import com.linkable.linkable.User;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.MainActivity.URL;

public class LoginActivity extends AppCompatActivity {

    EditText idText;
    EditText pwdText;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = (EditText)findViewById(R.id.idEditText);
        pwdText = (EditText)findViewById(R.id.passEditText);

        Button button = (Button)findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("login: ", idText.getText().toString());
                String id = idText.getText().toString();
                String pass = pwdText.getText().toString();
                HashMap<String,Object> login = new HashMap<>();
                login.put("username",id);
                login.put("password",pass);
                post(login);
            }
        });

        Button signup = findViewById(R.id.signupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class );
                startActivity(intent);
            }
        });
    }
    public void post(HashMap<String,Object> h){
        /*Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", token)
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
                //.client(okHttpClient)
                .build();
        RetrofitExService retrofitExService = retrofit.create(RetrofitExService.class);
        Call<User> call = retrofitExService.postFirst(h);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Log.i("plz", response.body().getToken());
                    token=response.body().getToken();
                    Toast.makeText(getApplicationContext(),"로그인 되었습니다",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class );
                    startActivity(intent);
                    finish();
                }
                else{
                    Log.i("plz",response.headers().toString());
                    Toast.makeText(getApplicationContext(),"아이디나 비밀번호가 올바르지 않습니다",Toast.LENGTH_LONG).show();
                    idText.setText(null);
                    pwdText.setText(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("fail","로그인실패");
            }
        });
    }
}