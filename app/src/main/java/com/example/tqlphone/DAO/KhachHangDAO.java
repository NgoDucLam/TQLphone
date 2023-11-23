package com.example.tqlphone.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tqlphone.DataBase.DbHelper;
import com.example.tqlphone.Model.KhachHang;
import com.example.tqlphone.Model.LoaiHang;

import java.util.ArrayList;

public class KhachHangDAO {
    private final DbHelper dbHelper;


    public KhachHangDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public KhachHangDAO(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<KhachHang> selectAllKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from KHACHHANG", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKh(cursor.getString(0));
                    kh.setTenKh(cursor.getString(1));
                    kh.setDiaChi(cursor.getString(3));
                    kh.setSoDienthoai(cursor.getString(4));
                    list.add(kh);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);

        }
        return list;
    }
    public boolean insert(KhachHang kh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKh", kh.getMaKh());
        values.put("tenKh", kh.getTenKh());
        values.put("diaChi", kh.getDiaChi());
        values.put("soDienthoai", kh.getSoDienthoai());
        long row = db.insert("KHACHHANG", null, values);
        return (row > 0);

    }

    public boolean update(KhachHang kh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKh", kh.getMaKh());
        values.put("tenKh", kh.getTenKh());
        values.put("diaChi", kh.getDiaChi());
        values.put("soDienthoai", kh.getSoDienthoai());
        long row = db.update("KHACHHANG", values, "makH=?", new String[]{String.valueOf(kh.getMaKh())});
        return (row > 0);
    }
    public boolean delete(String maKh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("KHACHHANG", "maKh=?", new String[]{String.valueOf(maKh)});
        return (row > 0);
    }
}
