package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import io.github.florent37.shapeofview.shapes.CircleView;

public class HomeAct extends AppCompatActivity {
    LinearLayout btn_ticket_pisa;
    CircleView btn_to_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoPisaTicket = new Intent(HomeAct.this,TicketDetailAct.class);
                startActivity(gotoPisaTicket);
            }
        });

        btn_to_profile = findViewById(R.id.btn_to_profile);
        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfile = new Intent(HomeAct.this, MyProfileAct.class);
                startActivity(gotoProfile);
            }
        });
    }
}