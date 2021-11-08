package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RegisterOnceAct extends AppCompatActivity {
    LinearLayout btn_back_in_register_one;
    Button btn_continue_register_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_once);

        btn_back_in_register_one = findViewById(R.id.btn_back_in_register_one);
        btn_back_in_register_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSignIn = new Intent(RegisterOnceAct.this,
                        SigninAct.class);
                startActivity(gotoSignIn);
            }
        });
        btn_continue_register_one = findViewById(R.id.btn_continue_register_one);
        btn_continue_register_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegisterTwo = new Intent(RegisterOnceAct.this,
                        RegisterTwoAct.class);
                startActivity(gotoRegisterTwo);
            }
        });
    }
}