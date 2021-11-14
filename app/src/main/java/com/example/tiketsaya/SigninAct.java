package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SigninAct extends AppCompatActivity {
    TextView text_new_account;
    Button btn_sign_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        text_new_account = findViewById(R.id.text_new_account);
        text_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegisterOne = new Intent(SigninAct.this,
                        RegisterOnceAct.class);
                startActivity(gotoRegisterOne);
            }
        });

        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHome = new Intent(SigninAct.this,HomeAct.class);
                startActivity(gotoHome);
            }
        });
    }
}