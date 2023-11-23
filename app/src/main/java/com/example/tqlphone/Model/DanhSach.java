package com.example.tqlphone.Model;

public class DanhSach {
    private String tenDt, hangDt, giaTien;

    public DanhSach() {
    }

    public DanhSach(String tenDt, String hangDt, String giaTien) {
        this.tenDt = tenDt;
        this.hangDt = hangDt;
        this.giaTien = giaTien;
    }

    public String getTenDt() {
        return tenDt;
    }

    public void setTenDt(String tenDt) {
        this.tenDt = tenDt;
    }

    public String getHangDt() {
        return hangDt;
    }

    public void setHangDt(String hangDt) {
        this.hangDt = hangDt;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }
}
