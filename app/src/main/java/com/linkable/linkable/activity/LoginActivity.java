package com.linkable.linkable.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.linkable.linkable.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText)findViewById(R.id.idEditText);
        final EditText pwdText = (EditText)findViewById(R.id.passEditText);

        final String admin = "admin";
        final String adminpwd = "0000";

        Button button = (Button)findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String pass = pwdText.getText().toString();

                if(id.equals(admin) && pass.equals(adminpwd)){
                    Toast.makeText(getApplicationContext(),"로그인 되었습니다",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class );
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"아이디나 비밀번호가 올바르지 않습니다",Toast.LENGTH_LONG).show();
                    idText.setText(null);
                    pwdText.setText(null);

                }
            }
        });
    }
}