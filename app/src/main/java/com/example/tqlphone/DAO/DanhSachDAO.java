package com.example.tqlphone.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tqlphone.DataBase.DbHelper;
import com.example.tqlphone.Model.DanhSach;

import java.util.ArrayList;

public class DanhSachDAO {
    private final DbHelper dbHelper;


    public DanhSachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<DanhSach> selectAllDanhSach() {
        ArrayList<DanhSach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from DANHSACH", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    DanhSach ds = new DanhSach();
                    ds.setTenDt(cursor.getString(0));
                    ds.setHangDt(cursor.getString(1));
                    ds.setGiaTien(cursor.getString(2));
                    list.add(ds);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);

        }
        return list;
    }
    public boolean insert(DanhSach ds) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDt", ds.getTenDt());
        values.put("hangDt", ds.getHangDt());
        values.put("giaTien", ds.getGiaTien());
        long row = db.insert("DANHSACH", null, values);
        return (row > 0);

    }

    public boolean update(DanhSach ds) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDt", ds.getTenDt());
        values.put("hangDt", ds.getHangDt());
        values.put("giaTien", ds.getGiaTien());
        long row = db.update("DANHSACH", values, "tenDt=?", new String[]{String.valueOf(ds.getTenDt())});
        return (row > 0);
    }
    public boolean delete(String tenDt) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("DANHSACH", "tenDt=?", new String[]{String.valueOf(tenDt)});
        return (row > 0);
    }
}
