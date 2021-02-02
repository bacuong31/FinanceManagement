package com.example.financemanager.Model;

import java.util.Date;

public class TienChoMuon {
    private int MaTienChoMuon = 0;
    private Date NgayChoMuon;
    private Date NgayHenTra;
    private double LuongTien;
    private int MaViTien;
    private String ThongTinChoMuon;
    private String TrangThai;

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public TienChoMuon(int maTienChoMuon, Date ngayChoMuon, Date ngayHenTra, double luongTien, int maViTien, String thongTinChoMuon) {
        MaTienChoMuon = maTienChoMuon;
        NgayChoMuon = ngayChoMuon;
        NgayHenTra = ngayHenTra;
        LuongTien = luongTien;
        MaViTien = maViTien;
        ThongTinChoMuon = thongTinChoMuon;
        TrangThai = "0";
    }

    public TienChoMuon(){}

    public TienChoMuon( Date ngayChoMuon, Date ngayHenTra, double luongTien, int maViTien, String thongTinChoMuon) {

        NgayChoMuon = ngayChoMuon;
        NgayHenTra = ngayHenTra;
        LuongTien = luongTien;
        MaViTien = maViTien;
        ThongTinChoMuon = thongTinChoMuon;
        TrangThai = "0";
    }

    public String getThongTinChoMuon() {
        return ThongTinChoMuon;
    }

    public void setThongTinChoMuon(String thongTinChoMuon) {
        ThongTinChoMuon = thongTinChoMuon;
    }

    public int getMaTienChoMuon() {
        return MaTienChoMuon;
    }

    public void setMaTienChoMuon(int maTienChoMuon) {
        MaTienChoMuon = maTienChoMuon;
    }

    public Date getNgayChoMuon() {
        return NgayChoMuon;
    }

    public void setNgayChoMuon(Date ngayChoMuon) {
        NgayChoMuon = ngayChoMuon;
    }

    public Date getNgayHenTra() {
        return NgayHenTra;
    }

    public void setNgayHenTra(Date ngayHenTra) {
        NgayHenTra = ngayHenTra;
    }

    public double getLuongTien() {
        return LuongTien;
    }

    public void setLuongTien(double luongTien) {
        LuongTien = luongTien;
    }

    public int getMaViTien() {
        return MaViTien;
    }

    public void setMaViTien(int maViTien) {
        MaViTien = maViTien;
    }
}
