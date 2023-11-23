package com.example.tqlphone.Model;

public class KhachHang {
    private String tenKh, diaChi, maKh, soDienthoai;

    public KhachHang() {
    }

    public KhachHang(String tenKh, String diaChi, String maKh, String soDienthoai) {
        this.tenKh = tenKh;
        this.diaChi = diaChi;
        this.maKh = maKh;
        this.soDienthoai = soDienthoai;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getSoDienthoai() {
        return soDienthoai;
    }

    public void setSoDienthoai(String soDienthoai) {
        this.soDienthoai = soDienthoai;
    }
}
