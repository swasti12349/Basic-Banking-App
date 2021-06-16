package com.sro.basicbaningapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.sro.basicbaningapp.R;
import com.sro.basicbaningapp.database.TransactionHelper;
import com.sro.basicbaningapp.adapter.SendToUserAdapter;
import com.sro.basicbaningapp.model.user;
import com.sro.basicbaningapp.database.userHelper;

import java.util.ArrayList;

public class SendToUser extends AppCompatActivity implements SendToUserAdapter.ViewHolder.OnUserListener {
    // RecyclerView
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<user> userArrayList;

    // Database
    private userHelper dbHelper;

    int toUserAccountBalance;
    String fromUserAccountName, fromUserAccountNo, toUserAccountNo, fromUserAccountBalance, transferAmount, toUserAccountName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_user);
        setTitle("Select User");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromUserAccountName = bundle.getString("FROM_USER_NAME");
            fromUserAccountNo = bundle.getString("FROM_USER_ACCOUNT_NO");
            fromUserAccountBalance = bundle.getString("FROM_USER_ACCOUNT_BALANCE");
            transferAmount = bundle.getString("TRANSFER_AMOUNT");
        }


        userArrayList = new ArrayList<user>();
        dbHelper = new userHelper(this, "userDB", null, 1);

        recyclerView = findViewById(R.id.send_to_user_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public void onUserClick(int position) {

        toUserAccountNo = userArrayList.get(position).getAcno();
        toUserAccountName = userArrayList.get(position).getName();
        toUserAccountBalance = userArrayList.get(position).getBal();

        calculateAmount();

        new TransactionHelper(this).insertTransferData(fromUserAccountName, toUserAccountName, transferAmount, 1);
        Toast.makeText(this, "Transaction Successful!!", Toast.LENGTH_LONG).show();

        startActivity(new Intent(SendToUser.this, FirstActivity.class));
        finish();
    }

    private void calculateAmount() {
        Integer currentAmount = Integer.parseInt(fromUserAccountBalance);
        Integer transferAmountInt = Integer.parseInt(transferAmount);
        Integer remainingAmount = currentAmount - transferAmountInt;
        Integer increasedAmount = transferAmountInt + toUserAccountBalance;

        // Update amount in the database
        new userHelper(this, "userDB", null, 1).updateAmount(fromUserAccountNo, remainingAmount);
        new userHelper(this, "userDB", null, 1).updateAmount(toUserAccountNo, increasedAmount);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder_exitButton = new AlertDialog.Builder(SendToUser.this);
        builder_exitButton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", (dialogInterface, i) -> {

                    // Transactions Cancelled
                    TransactionHelper dbHelper = new TransactionHelper(getApplicationContext());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put("COL_FROM", fromUserAccountName);
                    values.put("COL_TO", toUserAccountName);
                    values.put("STATUS", 0);
                    values.put("AMOUNT", transferAmount);

                    db.insert("TRANSACTION_TABLE", null, values);

                    Toast.makeText(SendToUser.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SendToUser.this, FirstActivity.class));
                    finish();
                }).setNegativeButton("No", null);
        AlertDialog alertExit = builder_exitButton.create();
        alertExit.show();
    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "NAME",
                "ACCOUNT_NUMBER",
                "IFSC_CODE",
                "EMAIL",
                "PHONE_NUMBER",
                "BALANCE",
        };

        Cursor cursor = db.query(
                "USER",
                projection,
                null,
                null,
                null,
                null,
                null);

        try {

            int phoneNoColumnIndex = cursor.getColumnIndex("PHONE_NUMBER");
            int emailColumnIndex = cursor.getColumnIndex("EMAIL");
            int ifscCodeColumnIndex = cursor.getColumnIndex("IFSC_CODE");
            int accountNumberColumnIndex = cursor.getColumnIndex("ACCOUNT_NUMBER");
            int nameColumnIndex = cursor.getColumnIndex("NAME");
            int accountBalanceColumnIndex = cursor.getColumnIndex("BALANCE");


            while (cursor.moveToNext()) {

                String currentName = cursor.getString(nameColumnIndex);
                String accountNumber = cursor.getString(accountNumberColumnIndex);
                String email = cursor.getString(emailColumnIndex);
                String phoneNumber = cursor.getString(phoneNoColumnIndex);
                String ifscCode = cursor.getString(ifscCodeColumnIndex);
                int accountBalance = cursor.getInt(accountBalanceColumnIndex);

                userArrayList.add(new user(currentName, accountNumber, phoneNumber, ifscCode, email, accountBalance));

            }
        } finally {

            cursor.close();
        }
        myAdapter = new SendToUserAdapter(this,userArrayList);
        recyclerView.setAdapter(myAdapter);

    }
}