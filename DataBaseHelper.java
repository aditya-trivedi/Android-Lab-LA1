package com.example.noiceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME = "registration";




    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user (username TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);

    }

    public  boolean insertData(String username , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);

        long res = db.insert("user", null,cv);
        if(res == -1)
            return  false;
        else
            return true;
    }

    public boolean login(String username , String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c =  db.query("user", null, "username = ? " , new String[]{username} , null , null ,null);
        if(c.getCount() == 0 )
            return false;
        else {
            if (c.moveToNext()) {
                String pass = c.getString(1);
                return pass.equals(password);
            } else
                return false;
        }
    }

    public boolean deleteData(String username){

        SQLiteDatabase db = this.getWritableDatabase();
        int res  = db.delete("user","username = ?",new String[]{username});
        return res > 0;
    }

    public boolean updateData(String username, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);

        Integer res ;
        res = db.update("user",cv , "username = ?",new String[]{username});
        return res > 0;
    }
}

