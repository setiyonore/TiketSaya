package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessBuyTicketAct extends AppCompatActivity {

    Animation app_splash,bottom_to_top, top_to_bottom;
    Button btn_view_ticket,btn_my_dasboard;
    ImageView icon_success_ticket;
    TextView success_buy_ticket,sub_succes_buy_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy_ticket);

        btn_view_ticket = findViewById(R.id.btn_view_ticket);
        btn_my_dasboard = findViewById(R.id.btn_my_dasboard);
        icon_success_ticket = findViewById(R.id.icon_success_ticket);
        success_buy_ticket = findViewById(R.id.success_buy_ticket);
        sub_succes_buy_ticket = findViewById(R.id.success_buy_ticket);

        //load animation
        app_splash = AnimationUtils.loadAnimation(this,R.anim.app_splash);
        bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);

        //run animation
        icon_success_ticket.startAnimation(app_splash);

        success_buy_ticket.startAnimation(top_to_bottom);
        sub_succes_buy_ticket.startAnimation(top_to_bottom);

        btn_view_ticket.startAnimation(bottom_to_top);
        btn_my_dasboard.startAnimation(bottom_to_top);


    }
}