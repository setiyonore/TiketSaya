package com.example.tiketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SigninAct extends AppCompatActivity {
    TextView text_new_account;
    Button btn_sign_in;
    EditText username,password;
    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        text_new_account = findViewById(R.id.text_new_account);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        text_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegisterOne = new Intent(SigninAct.this,
                        RegisterOnceAct.class);
                startActivity(gotoRegisterOne);
            }
        });
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(username.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            //ambil data password
                            String passwordFromFirebase = dataSnapshot.child("password").getValue()
                                    .toString();
                            //validasi password
                            if(password.getText().toString().equals(passwordFromFirebase)) {
                                //simpan username ke local
                                SharedPreferences sharedPreferences =
                                        getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(username_key,username.getText().toString());
                                editor.apply();
                                //berpindah ke home
                                Intent gotoHome = new Intent(SigninAct.this,HomeAct.class);
                                startActivity(gotoHome);
                            } else {
                                Toast.makeText(getApplicationContext(),"Password Salah",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(),"Username Tidak Ada",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}