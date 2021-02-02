package com.example.financemanager.Model;

import java.util.Date;

public class TienNo {
    private int MaTienNo = 0;
    private Date NgayMuonNo;
    private Date NgayHenTra;
    private double LuongTien;
    private int MaViTien;
    private String ThongTinMuonNo;
    private String TrangThai;

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public TienNo(int maTienNo, Date ngayMuonNo, Date ngayHenTra, double luongTien, int maViTien, String thongTinMuonNo) {
        MaTienNo = maTienNo;
        NgayMuonNo = ngayMuonNo;
        NgayHenTra = ngayHenTra;
        LuongTien = luongTien;
        MaViTien = maViTien;
        ThongTinMuonNo = thongTinMuonNo;
        TrangThai = "0";
    }

    public int getMaTienNo() {
        return MaTienNo;
    }

    public void setMaTienNo(int maTienNo) {
        MaTienNo = maTienNo;
    }

    public Date getNgayMuonNo() {
        return NgayMuonNo;
    }

    public void setNgayMuonNo(Date ngayMuonNo) {
        NgayMuonNo = ngayMuonNo;
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

    public String getThongTinMuonNo() {
        return ThongTinMuonNo;
    }

    public void setThongTinMuonNo(String thongTinMuonNo) {
        ThongTinMuonNo = thongTinMuonNo;
    }

    public TienNo(Date ngayMuonNo, Date ngayHenTra, double luongTien, int maViTien, String thongTinMuonNo) {
        NgayMuonNo = ngayMuonNo;
        NgayHenTra = ngayHenTra;
        LuongTien = luongTien;
        MaViTien = maViTien;
        ThongTinMuonNo = thongTinMuonNo;
        TrangThai = "0";
    }

    public TienNo(){}
}
