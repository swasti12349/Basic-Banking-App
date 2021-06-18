package com.sro.basicbaningapp.activity;
// Code written by SWASTI RANJAN OJHA
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sro.basicbaningapp.R;
import com.sro.basicbaningapp.database.TransactionHelper;
import com.sro.basicbaningapp.adapter.TransactionAdapter;
import com.sro.basicbaningapp.model.Transaction;

import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<Transaction> transactionArrayList;

    // Database
    private TransactionHelper dbHelper;

    TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        setTitle("Transaction History");

        emptyList = findViewById(R.id.empty_text);


        transactionArrayList = new ArrayList<Transaction>();


        dbHelper = new TransactionHelper(this);


        displayDatabaseInfo();

        recyclerView = findViewById(R.id.transactions_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new TransactionAdapter(transactionArrayList);
        recyclerView.setAdapter(myAdapter);

    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "COL_FROM",
                "COL_TO",
                "AMOUNT",
                "STATUS"
        };

        Cursor cursor = db.query(
                "TRANSACTION_TABLE",
                projection,
                null,
                null,
                null,
                null,
                null);

        try {

            int fromNameColumnIndex = cursor.getColumnIndex("COL_FROM");
            int ToNameColumnIndex = cursor.getColumnIndex("COL_TO");
            int amountColumnIndex = cursor.getColumnIndex("AMOUNT");
            int statusColumnIndex = cursor.getColumnIndex("STATUS");

            while (cursor.moveToNext()) {

                String fromName = cursor.getString(fromNameColumnIndex);
                String ToName = cursor.getString(ToNameColumnIndex);
                int accountBalance = cursor.getInt(amountColumnIndex);
                String status = cursor.getString(statusColumnIndex);
                transactionArrayList.add(new Transaction(fromName, ToName, accountBalance, status));
            }
        } finally {

            cursor.close();
        }

        if (transactionArrayList.isEmpty()) {
            emptyList.setVisibility(View.VISIBLE);
        } else {
            emptyList.setVisibility(View.GONE);
        }
    }
}