package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessRegisterAct extends AppCompatActivity {
    Button btn_explore;
    Animation app_splash,bottom_to_top, top_to_bottom;
    ImageView icon_success;
    TextView register_success, sub_register_success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);
        icon_success = findViewById(R.id.icon_success);
        register_success = findViewById(R.id.register_success);
        sub_register_success = findViewById(R.id.sub_register_success);
        btn_explore = findViewById(R.id.btn_explore);
        //load animation
        app_splash = AnimationUtils.loadAnimation(this,R.anim.app_splash);
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        //run animation
        btn_explore.startAnimation(bottom_to_top);
        icon_success.startAnimation(app_splash);
        register_success.startAnimation(top_to_bottom);
        sub_register_success.startAnimation(top_to_bottom);

        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHome = new Intent(SuccessRegisterAct.this,
                        HomeAct.class);
                startActivity(gotoHome);
            }
        });
    }
}