package com.example.tqlphone.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_DuAn1 = "DuAn1";


    public DbHelper(Context context) {
        super(context, Db_DuAn1, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qNguoiDung = "CREATE TABLE NGUOIDUNG(tendangnhap TEXT, matkhau TEXT)";
        sqLiteDatabase.execSQL(qNguoiDung);
        String dNguoiDung = "INSERT INTO NGUOIDUNG VALUES ('lamnd', '112'), ('namnt','987')";
        sqLiteDatabase.execSQL(dNguoiDung);

        String qLoaiHang = "CREATE TABLE LOAIHANG(" +
                "maHang TEXT PRIMARY KEY AUTOINCREMENT," +
                "tenHang TEXT NOT NULL)";
        sqLiteDatabase.execSQL(qLoaiHang);

        String qDanhSach = "CREATE TABLE DANHSACH (" +
                "tenDt TEXT NOT NULL, " +
                "hangDt TEXT NOT NULL, " +
                "giaTien TEXT NOT NULL, " +
                "FOREIGN KEY(hangDt) REFERENCES LOAIHANG(hangDt))";
        sqLiteDatabase.execSQL(qDanhSach);
        String dDanhSach ="INSERT INTO DANHSACH VALUES ('iPhone 15 ProMax', 'IOS', '15000000')" +
                ",('iPhone 14 ProMax', 'IOS', '10000000')" +
                ",('SamSung Galaxy', 'ANDROID', '7000000')" +
                ",('Z Fold 5', 'ANDROID', '9000000')";
        sqLiteDatabase.execSQL(dDanhSach);

        String qKhachHang = "CREATE TABLE KHACHHANG (" +
                "maKh TEXT PRIMARY KEY AUTOINCREMENT, " +
                "tenKh TEXT NOT NULL, " +
                "diaChi TEXT NOT NULL," +
                "soDienthoai TEXT NOT NULL )";
        sqLiteDatabase.execSQL(qKhachHang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAIHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DANHSACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            onCreate(sqLiteDatabase);
        }
    }
}
