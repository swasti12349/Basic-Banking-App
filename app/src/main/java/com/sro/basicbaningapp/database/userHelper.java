package com.sro.basicbaningapp.database;
// Code written by SWASTI RANJAN OJHA
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class userHelper extends SQLiteOpenHelper {


    public userHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cmd = "CREATE TABLE USER (_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT, ACCOUNT_NUMBER TEXT, IFSC_CODE TEXT, " +
                "EMAIL TEXT, PHONE_NUMBER TEXT, BALANCE INTEGER)";

        db.execSQL(cmd);

        insert("Aman", "113483435352",
                "3454", "aman23@gmail.com", "9454845236", 10000, db);
        insert("Dhruv", "115445635352",
                "8467", "raj43@gmail.com", "9054343455", 12040, db);
        insert("Alex", "11828382392",
                "5467", "alex234@gmail.com", "9754845235", 18340, db);
        insert("Raman", "1129283283",
                "2930", "raman54@gmail.com", "9954654638", 12930, db);
        insert("Anuj", "1129283283",
                "6948", "anuj@gmail.com", "9954845235", 23452, db);
        insert("Shreya", "1129283283",
                "4392", "shreya@gmail.com", "9930493929", 52356, db);
        insert("Ritesh", "1129283283",
                "8294", "ritesh@gmail.com", "9954845456", 36554, db);
        insert("Rohit", "1129283283",
                "9385", "rohit@gmail.com", "9954845364", 25375, db);
        insert("Rahul", "1129283283",
                "6352", "rahul@gmail.com", "9954845263", 346737, db);
        insert("Anjali", "1129283283",
                "5356", "anjali@gmail.com", "9954757474", 46467, db);
    }

    void insert(String name, String acno, String ifsc, String email, String phno, int bal, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("ACCOUNT_NUMBER", acno);
        values.put("IFSC_CODE", ifsc);
        values.put("EMAIL", email);
        values.put("PHONE_NUMBER", phno);
        values.put("BALANCE", bal);

        db.insert("USER", null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + "USER");
            onCreate(db);
        }
    }

    public void updateAmount(String accountNo, int amount) {
        Log.d("TAG", "update Amount");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + "USER" + " set " + "BALANCE" + " = " + amount + " where " +
                "ACCOUNT_NUMBER" + " = " + accountNo);
    }
}


