package com.example.tiketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import io.github.florent37.shapeofview.shapes.CircleView;

public class HomeAct extends AppCompatActivity {
    LinearLayout btn_ticket_pisa,
            btn_tiket_tori,
            btn_ticket_pagoda,
            btn_tiket_spinx,
            btn_tiket_candi,
                btn_tiket_monas;
    CircleView btn_to_profile;
    ImageView foto_home_user;
    TextView user_balance,nama_lengkap,bio;
    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getUsernameLocal();
        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_tiket_tori = findViewById(R.id.btn_ticket_tori);
        btn_ticket_pagoda = findViewById(R.id.btn_ticket_pagoda);
        btn_tiket_spinx = findViewById(R.id.btn_ticket_spinx);
        btn_tiket_candi = findViewById(R.id.btn_ticket_candi);
        btn_tiket_monas = findViewById(R.id.btn_ticket_monas);

        btn_to_profile = findViewById(R.id.btn_to_profile);
        foto_home_user = findViewById(R.id.foto_home_user);
        user_balance = findViewById(R.id.user_balance);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);

        reference = FirebaseDatabase.getInstance().getReference()
                    .child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                user_balance.setText("$ " + dataSnapshot.child("user_balance").getValue().toString());
                Picasso.with(HomeAct.this)
                        .load(dataSnapshot.child("url_photo_profile").getValue().toString())
                        .fit().centerCrop().into(foto_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoPisaTicket = new Intent(HomeAct.this,TicketDetailAct.class);
                gotoPisaTicket.putExtra("jenis_tiket","Pisa");
                startActivity(gotoPisaTicket);
            }
        });
        btn_tiket_tori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetilTicket = new Intent(HomeAct.this,TicketDetailAct.class);
                gotoDetilTicket.putExtra("jenis_tiket", "Tori");
                startActivity(gotoDetilTicket);
            }
        });
        btn_ticket_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetilTicket = new Intent(HomeAct.this,TicketDetailAct.class);
                gotoDetilTicket.putExtra("jenis_tiket", "Pagoda");
                startActivity(gotoDetilTicket);
            }
        });
        btn_tiket_spinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetilTicket = new Intent(HomeAct.this,TicketDetailAct.class);
                gotoDetilTicket.putExtra("jenis_tiket", "Spink");
                startActivity(gotoDetilTicket);
            }
        });
        btn_tiket_candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetilTicket = new Intent(HomeAct.this,TicketDetailAct.class);
                gotoDetilTicket.putExtra("jenis_tiket", "Candi");
                startActivity(gotoDetilTicket);
            }
        });
        btn_tiket_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetilTicket = new Intent(HomeAct.this,TicketDetailAct.class);
                gotoDetilTicket.putExtra("jenis_tiket", "Monas");
                startActivity(gotoDetilTicket);
            }
        });
        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfile = new Intent(HomeAct.this, MyProfileAct.class);
                startActivity(gotoProfile);
            }
        });
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}