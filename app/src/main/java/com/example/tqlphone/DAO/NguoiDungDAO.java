package com.example.tqlphone.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tqlphone.DataBase.DbHelper;

public class NguoiDungDAO {
    private final DbHelper dbHelper;

    public NguoiDungDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public boolean CheckLogin(String username,String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau = ?", new String[]{username, password});
        return cursor.getCount() > 0;

    }
    public boolean Register(String username, String password, String hoten) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangnhap", username);
        contentValues.put("matkhau", password);
        long check = sqLiteDatabase.insert("NGUOIDUNG", null, contentValues);
        return check != -1;


    }
}
