package com.example.assignment1.Helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment1.Model.Account;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "account.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS account(username TEXT PRIMARY KEY, password TEXT, created_date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS account");
        onCreate(db);
    }

    public Account getAccountByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query("account", new String[]{"username", "password", "created_date"}, "username = ?",
                new String[]{username}, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        else return null;
        if (cursor.getCount() == 0) return null;
        return new Account(cursor.getString(0), cursor.getString(1), cursor.getString(2));
    }

    public void updatePassword(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", account.getPassword());
        db.update("account", values,   "username = ?",
                new String[] { String.valueOf(account.getUsername()) });
        db.close();
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());
        values.put("created_date", account.getCreatedDate());
        db.insert("account", null, values);
        db.close();
    }

    public void deleteAccountByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("account", "username = ?", new String[]{username});
        db.close();
    }
}
