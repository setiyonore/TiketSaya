package com.example.tiketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyTicketDetailAct extends AppCompatActivity {
    DatabaseReference reference;
    TextView nama_wisata,lokasi,date_wisata,time_wisata,ketentuan;
    LinearLayout btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_detail);

        nama_wisata = findViewById(R.id.nama_wisata);
        lokasi = findViewById(R.id.lokasi);
        date_wisata = findViewById(R.id.date_wisata);
        time_wisata = findViewById(R.id.time_wisata);
        ketentuan = findViewById(R.id.ketentuan);
        btn_back = findViewById(R.id.btn_back);

        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        String nama_wisata_baru = bundle.getString("nama_wisata");
        //ambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(nama_wisata_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                date_wisata.setText(dataSnapshot.child("date_wisata").getValue().toString());
                time_wisata.setText(dataSnapshot.child("time_wisata").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToHome = new Intent(MyTicketDetailAct.this,HomeAct.class);
                startActivity(backToHome);
            }
        });
    }
}