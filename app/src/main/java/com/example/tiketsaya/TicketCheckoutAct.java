package com.example.tiketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TicketCheckoutAct extends AppCompatActivity {
    Button btn_buy_ticket,btn_minus,btn_plus;
    TextView jml_tiket,total_harga,current_balance;
    Integer valueJumlahTiket = 1;
    Integer myBalance = 200;
    Integer valueTotalHarga = 0;
    Integer valueHargaTiket = 50;
    ImageView notice_uang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_checkout);
        btn_buy_ticket = findViewById(R.id.btn_buy_ticket);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        jml_tiket = findViewById(R.id.jml_tiket);
        total_harga = findViewById(R.id.total_harga);
        current_balance = findViewById(R.id.current_balance);
        notice_uang = findViewById(R.id.notice_uang);

        //set default value
        jml_tiket.setText(valueJumlahTiket.toString());
        total_harga.setText("$ " + valueHargaTiket * valueJumlahTiket+"");
        current_balance.setText("$ " + myBalance+"");

        //hide btn minus by default
        btn_minus.animate().alpha(0).setDuration(300).start();
        btn_minus.setEnabled(false);

        notice_uang.setVisibility(View.GONE);

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
                Intent gotoSuccessTicket = new Intent(TicketCheckoutAct.this,
                        SuccessBuyTicketAct.class);
                startActivity(gotoSuccessTicket);
            }
        });
    }
}