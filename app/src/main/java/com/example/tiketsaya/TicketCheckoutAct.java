package com.example.tiketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class TicketCheckoutAct extends AppCompatActivity {
    Button btn_buy_ticket,btn_minus,btn_plus;
    TextView jml_tiket,total_harga,current_balance,nama_wisata,lokasi,ketentuan;
    Integer valueJumlahTiket = 1;
    Integer myBalance = 0;
    Integer valueTotalHarga = 0;
    Integer valueHargaTiket = 0;
    ImageView notice_uang;
    DatabaseReference reference, referenceUser, referenceTiket;
    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new;
    Integer nomor_transaksi = new Random().nextInt();

    String date_wisata = "";
    String time_wisata = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_checkout);
        getUsernameLocal();
        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_tiket_baru = bundle.getString("jenis_tiket");
        btn_buy_ticket = findViewById(R.id.btn_buy_ticket);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        jml_tiket = findViewById(R.id.jml_tiket);
        total_harga = findViewById(R.id.total_harga);
        current_balance = findViewById(R.id.current_balance);
        notice_uang = findViewById(R.id.notice_uang);
        nama_wisata = findViewById(R.id.nama_wisata);
        lokasi = findViewById(R.id.lokasi);
        ketentuan = findViewById(R.id.ketentuan);

        //set default value
        jml_tiket.setText(valueJumlahTiket.toString());

        //hide btn minus by default
        btn_minus.animate().alpha(0).setDuration(300).start();
        btn_minus.setEnabled(false);

        notice_uang.setVisibility(View.GONE);
        //mengambil data user dari firebase
        referenceUser = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        referenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myBalance = Integer.valueOf(dataSnapshot.child("user_balance").getValue().toString());
                current_balance.setText("$ " + myBalance+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //mengambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(jenis_tiket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
                valueHargaTiket = Integer.valueOf(dataSnapshot.child("harga_tiket").getValue().toString());
                total_harga.setText("$ " + valueHargaTiket * valueJumlahTiket+"");

                date_wisata = dataSnapshot.child("date_wisata").getValue().toString();
                time_wisata = dataSnapshot.child("time_wisata").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tambah jumlah tiket
                valueJumlahTiket+=1;
                jml_tiket.setText(valueJumlahTiket.toString());
                if (valueJumlahTiket > 1){
                    btn_minus.animate().alpha(1).setDuration(300).start();
                    btn_minus.setEnabled(true);
                }
                //hitung total harga
                valueTotalHarga = valueHargaTiket * valueJumlahTiket;
                total_harga.setText("$ "+ valueTotalHarga+"");

                //validasi total harga dan balance
                if (valueTotalHarga > myBalance){
                    btn_buy_ticket.animate().translationY(250)
                            .alpha(0).setDuration(300).start();
                    btn_buy_ticket.setEnabled(false);
                    current_balance.setTextColor(Color.parseColor("#D1206B"));
                    notice_uang.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueJumlahTiket-=1;
                jml_tiket.setText(valueJumlahTiket.toString());
                if (valueJumlahTiket < 2){
                    btn_minus.animate().alpha(0).setDuration(300).start();
                    btn_minus.setEnabled(false);
                }
                //hitung total harga
                valueTotalHarga = valueHargaTiket * valueJumlahTiket;
                total_harga.setText("$ "+ valueTotalHarga+"");

                //validasi total harga dan balance
                if (valueTotalHarga <= myBalance){
                    btn_buy_ticket.animate().translationY(0)
                            .alpha(1).setDuration(300).start();
                    btn_buy_ticket.setEnabled(true);
                    current_balance.setTextColor(Color.parseColor("#203DD1"));
                    notice_uang.setVisibility(View.GONE);
                }
            }
        });
        btn_buy_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //meyimpan data user ke firebase dan membuat tabel baru "MyTickets"
                referenceTiket = FirebaseDatabase.getInstance().getReference().child("MyTickets")
                        .child(username_key_new).child(nama_wisata.getText().toString() + nomor_transaksi);
                referenceTiket.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        referenceTiket.getRef().child("nama_wisata").setValue(nama_wisata
                                .getText().toString());
                        referenceTiket.getRef().child("lokasi").setValue(lokasi
                                .getText().toString());
                        referenceTiket.getRef().child("ketentuan").setValue(ketentuan
                                .getText().toString());
                        referenceTiket.getRef().child("jumlah_tiket").setValue(valueJumlahTiket);
                        referenceTiket.getRef().child("date_wisata")
                                .setValue(date_wisata);
                        referenceTiket.getRef().child("time_wisata").setValue(time_wisata);
                        Intent gotoSuccessTicket = new Intent(TicketCheckoutAct.this,
                                SuccessBuyTicketAct.class);
                        startActivity(gotoSuccessTicket);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}