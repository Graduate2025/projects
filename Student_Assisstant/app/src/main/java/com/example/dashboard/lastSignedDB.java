package com.example.dashboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class lastSignedDB extends SQLiteOpenHelper {
    public lastSignedDB(@Nullable Context context) {
        super(context, "last_signed.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE last (email TEXT, stay_signed TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void userSigned(String email, Boolean stay){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM last");
        ContentValues cv = new ContentValues();

        cv.put("email",email);
        cv.put("stay_signed",Boolean.toString(stay));
        db.insert("last",null,cv);

        db.close();
    }

    public Boolean isStaySigned(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT stay_signed FROM last",null);
        Boolean b = null;
        while(c.moveToNext()){
            b = Boolean.parseBoolean(c.getString(0));
        }
        return b;
    }

    public String lastSigned(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT email FROM last",null);
        String email = null;
        while(c.moveToNext()){
            email = c.getString(0);
        }
        return email;
    }

    public void logOut(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE last SET stay_signed = '" + "false" + "'");
    }
}
