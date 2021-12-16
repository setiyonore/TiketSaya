package com.example.tiketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOnceAct extends AppCompatActivity {
    LinearLayout btn_back_in_register_one;
    Button btn_continue_register_one;
    EditText username,password,email_address;
    DatabaseReference reference;

    String USERNAME_KEY = "username_key";
    String username_key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_once);

        btn_back_in_register_one = findViewById(R.id.btn_back_in_register_one);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email_address = findViewById(R.id.email_address);

        btn_back_in_register_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pindah halaman
                Intent gotoSignIn = new Intent(RegisterOnceAct.this,
                        SigninAct.class);
                startActivity(gotoSignIn);
            }
        });
        btn_continue_register_one = findViewById(R.id.btn_continue_register_one);
        btn_continue_register_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_continue_register_one.setEnabled(false);
                btn_continue_register_one.setText("Loading...");
                //menyimpan data ke local storage
                SharedPreferences sharedPreferences =
                        getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key,username.getText().toString());
                editor.apply();

                //simpan ke database

                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(username.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("username").setValue(username
                                .getText().toString());
                        dataSnapshot.getRef().child("password").setValue(password
                                .getText().toString());
                        dataSnapshot.getRef().child("email_address").setValue(email_address
                                .getText().toString());
                        dataSnapshot.getRef().child("user_balance").setValue(800);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //pindah halaman
                Intent gotoRegisterTwo = new Intent(RegisterOnceAct.this,
                        RegisterTwoAct.class);
                startActivity(gotoRegisterTwo);
            }
        });
    }
}