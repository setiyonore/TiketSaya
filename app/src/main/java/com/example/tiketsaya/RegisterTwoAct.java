package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class RegisterTwoAct extends AppCompatActivity {
    LinearLayout btn_back_register_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        btn_back_register_two = findViewById(R.id.btn_back_in_register_two);
        btn_back_register_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSignOne = new Intent(RegisterTwoAct.this,
                        RegisterOnceAct.class);
                startActivity(gotoSignOne);
            }
        });
    }
}