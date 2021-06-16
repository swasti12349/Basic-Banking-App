package com.sro.basicbaningapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TransactionHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transaction.db";

    private static final int DATABASE_VERSION = 1;

    public TransactionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE = "CREATE TABLE TRANSACTION_TABLE (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "COL_FROM TEXT, COL_TO TEXT, AMOUNT TEXT, " +
                "STATUS TEXT)";


        db.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + "TRANSACTION_TABLE");
            onCreate(db);
        }
    }

    public boolean insertTransferData (String fromName, String toName, String amount, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("COL_FROM", fromName);
        contentValues.put("COL_TO", toName);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert("TRANSACTION_TABLE", null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}
