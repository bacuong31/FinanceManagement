package com.example.financemanager.Model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class HoatDongChiTieu {
    private int MaHoatDongChiTieu = 0;
    private Date NgayChiTieu;
    private double LuongTien;
    private int MaChiTieu;
    private int MaViTien;
    private String TenChiTieu;

    public HoatDongChiTieu(int maHoatDongChiTieu, Date ngayChiTieu, double luongTien, int maChiTieu, int maViTien, String tenChiTieu) {
        MaHoatDongChiTieu = maHoatDongChiTieu;
        NgayChiTieu = ngayChiTieu;
        LuongTien = luongTien;
        MaChiTieu = maChiTieu;
        MaViTien = maViTien;
        TenChiTieu = tenChiTieu;
    }

    public HoatDongChiTieu(){}

    public HoatDongChiTieu(Date ngayChiTieu, double luongTien, int maChiTieu, int maViTien, String tenChiTieu) {
        NgayChiTieu = ngayChiTieu;
        LuongTien = luongTien;
        MaChiTieu = maChiTieu;
        MaViTien = maViTien;
        TenChiTieu = tenChiTieu;
    }

    public int getMaHoatDongChiTieu() {
        return MaHoatDongChiTieu;
    }

    public void setMaHoatDongChiTieu(int maHoatDongChiTieu) {
        MaHoatDongChiTieu = maHoatDongChiTieu;
    }

    public Date getNgayChiTieu() {
        return NgayChiTieu;
    }

    public void setNgayChiTieu(Date ngayChiTieu) {
        NgayChiTieu = ngayChiTieu;
    }

    public double getLuongTien() {
        return LuongTien;
    }

    public void setLuongTien(double luongTien) {
        LuongTien = luongTien;
    }

    public int getMaChiTieu() {
        return MaChiTieu;
    }

    public void setMaChiTieu(int maChiTieu) {
        MaChiTieu = maChiTieu;
    }

    public int getMaViTien() {
        return MaViTien;
    }

    public void setMaViTien(int maViTien) {
        MaViTien = maViTien;
    }

    public String getTenChiTieu() {
        return TenChiTieu;
    }

    public void setTenChiTieu(String tenChiTieu) {
        TenChiTieu = tenChiTieu;
    }
}
