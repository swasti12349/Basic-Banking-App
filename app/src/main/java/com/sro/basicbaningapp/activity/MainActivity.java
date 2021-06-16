package com.sro.basicbaningapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.sro.basicbaningapp.R;
import com.sro.basicbaningapp.model.user;
import com.sro.basicbaningapp.adapter.userAdapter;
import com.sro.basicbaningapp.database.userHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    com.sro.basicbaningapp.database.userHelper userHelper;
    SQLiteDatabase database;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ArrayList<user> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Users");
        setContentView(R.layout.activity_main);
        userHelper = new userHelper(this, "userDB", null, 1);
        database = userHelper.getWritableDatabase();
        userList = new ArrayList<>();

        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = database.rawQuery("SELECT * FROM USER", new String[]{});
        cursor.moveToFirst();
        String name, acno, ifsc, email, phno;
        int bal;
        do {
            name = cursor.getString(1);
            acno = cursor.getString(2);
            ifsc = cursor.getString(3);
            email = cursor.getString(4);
            phno = cursor.getString(5);
            bal = cursor.getInt(6);
            userList.add(new user(name,acno,ifsc,email,phno,bal));

        }
        while (cursor.moveToNext());
        adapter = new userAdapter(userList,getApplicationContext());
        recyclerView.setAdapter(adapter);

        cursor.moveToFirst();



    }
}