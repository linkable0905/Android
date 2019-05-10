package com.linkable.linkable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linkable.linkable.R;
import com.linkable.linkable.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.linkable.linkable.activity.MainActivity.URL;

public class SignUpActivity extends AppCompatActivity {
    EditText nameText;
    EditText idText;
    EditText pwdText;
    EditText emailText;
    Button signupButton;

    String name;
    String id;
    String pwd;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameText = findViewById(R.id.nameSignupText);
        idText = findViewById(R.id.idSignupText);
        pwdText = findViewById(R.id.pwdSignupText);
        emailText = findViewById(R.id.emailSignupText);

        signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameText.getText().toString();
                id = idText.getText().toString();
                pwd = pwdText.getText().toString();
                email = emailText.getText().toString();

                if (name.equals("") || id.equals("") || pwd.equals("") || email.equals("")) {
                    Toast.makeText(getApplicationContext(),"빈 칸을 모두 채워주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    HashMap<String,Object> signup = new HashMap<>();
                    signup.put("name", name);
                    signup.put("username", id);
                    signup.put("password", pwd);
                    signup.put("email", email);
                    post(signup);
                }
            }
        });
    }

    public void post(HashMap<String, Object> h) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitExService retrofitExService = retrofit.create(RetrofitExService.class);
        Call<User> call = retrofitExService.register(h);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.i("register", "회원가입 성공");
                    finish();
                }
                else {
                    Log.i("plz",response.headers().toString());
                    Toast.makeText(getApplicationContext(),"이미 존재하는 아이디입니다.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("fail", "회원가입 실패");
            }
        });
    }
}
