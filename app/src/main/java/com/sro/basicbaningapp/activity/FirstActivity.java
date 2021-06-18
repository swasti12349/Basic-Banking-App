package com.sro.basicbaningapp.activity;
// Code written by SWASTI RANJAN OJHA
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.sro.basicbaningapp.R;

public class FirstActivity extends AppCompatActivity {
Button users, transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        users = findViewById(R.id.user);
        transaction = findViewById(R.id.transaction);

        users.setOnClickListener(v -> startActivity(new Intent(FirstActivity.this, MainActivity.class)));

        transaction.setOnClickListener(v -> startActivity(new Intent(FirstActivity.this, TransactionHistory.class)));

    }
}