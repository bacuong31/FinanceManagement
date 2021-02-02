package com.example.financemanager.Object_Perform;

import java.util.Date;

public class ChiTieuPerform {
    private int MaHoatDongChiTieu;
    private int MaChiTieu;
    private Date NgayChiTieu;
    private String TenHoatDongChiTieu;
    private String TenViTien;
    private double LuongTien;

    public int getMaHoatDongChiTieu() {
        return MaHoatDongChiTieu;
    }

    public void setMaHoatDongChiTieu(int maHoatDongChiTieu) {
        MaHoatDongChiTieu = maHoatDongChiTieu;
    }

    public String getTenViTien() {
        return TenViTien;
    }

    public void setTenViTien(String tenViTien) {
        TenViTien = tenViTien;
    }

    public ChiTieuPerform(Date ngayChiTieu, String tenHoatDongChiTieu, double luongTien) {
        NgayChiTieu = ngayChiTieu;
        TenHoatDongChiTieu = tenHoatDongChiTieu;
        LuongTien = luongTien;
    }

    public int getMaChiTieu() {
        return MaChiTieu;
    }

    public void setMaChiTieu(int maChiTieu) {
        MaChiTieu = maChiTieu;
    }

    public ChiTieuPerform(){}
    public Date getNgayChiTieu() {
        return NgayChiTieu;
    }

    public void setNgayChiTieu(Date ngayChiTieu) {
        NgayChiTieu = ngayChiTieu;
    }

    public String getTenHoatDongChiTieu() {
        return TenHoatDongChiTieu;
    }

    public void setTenHoatDongChiTieu(String tenHoatDongChiTieu) {
        TenHoatDongChiTieu = tenHoatDongChiTieu;
    }

    public double getLuongTien() {
        return LuongTien;
    }

    public void setLuongTien(double luongTien) {
        LuongTien = luongTien;
    }
}
