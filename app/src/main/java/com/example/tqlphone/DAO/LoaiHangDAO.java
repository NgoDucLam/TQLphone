package com.example.tqlphone.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tqlphone.DataBase.DbHelper;
import com.example.tqlphone.Model.DanhSach;
import com.example.tqlphone.Model.LoaiHang;

import java.util.ArrayList;

public class LoaiHangDAO {
    private final DbHelper dbHelper;


    public LoaiHangDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public LoaiHangDAO(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<LoaiHang> selectAllLoaiHang() {
        ArrayList<LoaiHang> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from LOAIHANG", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    LoaiHang lh = new LoaiHang();
                    lh.setMaHang(cursor.getString(0));
                    lh.setTenHang(cursor.getString(1));
                    list.add(lh);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);

        }
        return list;
    }
    public boolean insert(LoaiHang lh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHang", lh.getMaHang());
        values.put("tenHang", lh.getTenHang());
        long row = db.insert("LOAIHANG", null, values);
        return (row > 0);

    }

    public boolean update(LoaiHang lh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHang", lh.getMaHang());
        values.put("tenHang", lh.getTenHang());
        long row = db.update("LOAIHANG", values, "maHang=?", new String[]{String.valueOf(lh.getMaHang())});
        return (row > 0);
    }
    public boolean delete(String maHang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("LOAIHANG", "maHang=?", new String[]{String.valueOf(maHang)});
        return (row > 0);
    }
}
