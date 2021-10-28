package com.example.dts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key,contact TEXT,dob Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists Userdetails");
    }
    public boolean insertUserData(String name,String contact,String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name ",name);
        contentValues.put("contact", contact);
        contentValues.put("dob",dob);
        long result = db.insert("Userdetails",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean updateUserData(String name,String contact,String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        Cursor cursor = db.rawQuery("Select * from Userdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0) {
            long result = db.update("Userdetails", contentValues, "name =?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public boolean deleteUserData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select*from Userdetails where name=?",new String[]{name});
        if(cursor.getCount()>0){
            long result = db.delete("Userdetails","name=?",new String[]{name});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select*from Userdetails",null);
        return cursor;
    }
}
