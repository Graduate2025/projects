package com.example.dashboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class accountsDB extends SQLiteOpenHelper {
    public accountsDB(@Nullable Context context) {
        super(context, "accounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE accounts (email TEXT, password TEXT, first_name TEXT, last_name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createNewUser(String email, String password, String first, String last){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email",email);
        cv.put("password",password);
        cv.put("first_name",first);
        cv.put("last_name",last);
        db.insert("accounts",null,cv);
        db.close();
    }

    public Boolean checkIfPasswordIsRight(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT password FROM accounts WHERE email = '"+ email + "'",null);
        String pass = null;
        while (c.moveToNext()){
            pass = c.getString(0);
        }
        c.close();
        return pass.equals(password);
    }

    public String getName(String email){

        String first = null, last = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM accounts WHERE email = '"+email+"'",null);
        while(c.moveToNext()){
            first = c.getString(2);
            last = c.getString(3);
        }
        c.close();
        return first + " " + last;
    }

    public void updatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE accounts SET password = '" + password + "' WHERE email = '" + email + "'");
    }

    public void updateName(String email,String first,String last){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update accounts set first_name = '" + first + "',last_name = '" + last + "' where email = '" + email + "'");
        db.close();
    }
}