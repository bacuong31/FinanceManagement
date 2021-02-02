package com.example.financemanager.Model;

import java.util.Date;

public class ThuNhap {
    private int MaThuNhap = 0;
    private String TenThuNhap;

    private double LuongTien;
    private Date NgayThuNhap;
    private String TenViTien = "";
    private int MaViTien;

    public ThuNhap(int maThuNhap, String tenThuNhap, double luongTien, Date ngayThuNhap, int maViTien) {
        MaThuNhap = maThuNhap;
        TenThuNhap = tenThuNhap;
        LuongTien = luongTien;
        NgayThuNhap = ngayThuNhap;

        MaViTien = maViTien;
    }

    public String getTenViTien() {
        return TenViTien;
    }

    public void setTenViTien(String tenViTien) {
        TenViTien = tenViTien;
    }

    public ThuNhap(){}

    public ThuNhap(String tenThuNhap, double luongTien, Date ngayThuNhap, int maViTien) {
        TenThuNhap = tenThuNhap;
        LuongTien = luongTien;
        NgayThuNhap = ngayThuNhap;

        MaViTien = maViTien;
    }


    public int getMaThuNhap() {
        return MaThuNhap;
    }

    public void setMaThuNhap(int maThuNhap) {
        MaThuNhap = maThuNhap;
    }

    public Date getNgayThuNhap() {
        return NgayThuNhap;
    }

    public void setNgayThuNhap(Date ngayThuNhap) {
        NgayThuNhap = ngayThuNhap;
    }

    public double getLuongTien() {
        return LuongTien;
    }

    public void setLuongTien(double luongTien) {
        LuongTien = luongTien;
    }

    public String getTenThuNhap() {
        return TenThuNhap;
    }

    public void setTenThuNhap(String tenThuNhap) {
        TenThuNhap = tenThuNhap;
    }

    public int getMaViTien() {
        return MaViTien;
    }

    public void setMaViTien(int maViTien) {
        MaViTien = maViTien;
    }
}
