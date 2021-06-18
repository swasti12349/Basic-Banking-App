package com.sro.basicbaningapp.activity;
// Code written by SWASTI RANJAN OJHA
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sro.basicbaningapp.R;

public class userDetail extends AppCompatActivity {
    TextView name, email, accountNo, balance, ifscCode, phoneNo;
    Button transferMoney;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        setTitle("User Detail");
        name = findViewById(R.id.name);
        email = findViewById(R.id.email_id);
        accountNo = findViewById(R.id.account_no);
        balance = findViewById(R.id.avail_balance);
        ifscCode = findViewById(R.id.ifsc_id);
        phoneNo = findViewById(R.id.phone_no);
        transferMoney = findViewById(R.id.transfer_money);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        if (extras != null) {
            name.setText(extras.getString("username"));
            accountNo.setText(extras.getString("useraccno"));
            email.setText(extras.getString("useremail"));
            phoneNo.setText(extras.getString("userphno"));
            ifscCode.setText(extras.getString("userifsc"));
            balance.setText(String.valueOf(extras.getInt("userbalance")));
        }

        transferMoney.setOnClickListener(v -> enterAmount());
    }

    private void enterAmount() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(userDetail.this);
        View mView = getLayoutInflater().inflate(R.layout.dialogtv, null);
        mBuilder.setTitle("Enter Amount").setView(mView).setCancelable(false);

        final EditText mAmount = (EditText) mView.findViewById(R.id.enter_money);
        mBuilder.setPositiveButton("SEND", (dialogInterface, i) -> {

        }).setNegativeButton("CANCEL", (dialog, which) -> {
            dialog.dismiss();
            transactionCancel();
        });

        dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(view -> {

            int currentBalance = Integer.parseInt(String.valueOf(balance.getText()));
            int enteredAmt = Integer.parseInt(mAmount.getText().toString());

            if (mAmount.getText().toString().isEmpty()) {
                mAmount.setError("Amount can't be empty");

            } else if (enteredAmt > currentBalance) {
                mAmount.setError("Your account don't have enough balance");
            } else {
                Intent intent = new Intent(userDetail.this, SendToUser.class);
                intent.putExtra("FROM_USER_ACCOUNT_NO", accountNo.getText().toString());    // PRIMARY_KEY
                intent.putExtra("FROM_USER_NAME", name.getText());
                intent.putExtra("FROM_USER_ACCOUNT_BALANCE", balance.getText());
                intent.putExtra("TRANSFER_AMOUNT", mAmount.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    private void transactionCancel() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(userDetail.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", (dialogInterface, i) -> Toast.makeText(userDetail.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show()).setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                    enterAmount();
                });
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }
}