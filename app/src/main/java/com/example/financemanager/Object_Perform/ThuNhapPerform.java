package com.example.financemanager.Object_Perform;

import java.util.Date;

public class ThuNhapPerform {
    private Date NgayThuNhap;
    private String TenThuNhap;
    private Double LuongTien;
    private String TenViTien;
    private int MaThuNhap;


    public String getTenViTien() {
        return TenViTien;
    }

    public void setTenViTien(String tenViTien) {
        TenViTien = tenViTien;
    }

    public int getMaThuNhap() {
        return MaThuNhap;
    }

    public void setMaThuNhap(int maThuNhap) {
        MaThuNhap = maThuNhap;
    }

    public ThuNhapPerform(){}

    public Date getNgayThuNhap() {
        return NgayThuNhap;
    }

    public void setNgayThuNhap(Date ngayThuNhap) {
        NgayThuNhap = ngayThuNhap;
    }

    public String getTenThuNhap() {
        return TenThuNhap;
    }

    public void setTenThuNhap(String tenThuNhap) {
        TenThuNhap = tenThuNhap;
    }

    public Double getLuongTien() {
        return LuongTien;
    }

    public void setLuongTien(Double luongTien) {
        LuongTien = luongTien;
    }

    public ThuNhapPerform(Date ngayThuNhap, String tenThuNhap, Double luongTien) {
        NgayThuNhap = ngayThuNhap;
        TenThuNhap = tenThuNhap;
        LuongTien = luongTien;
    }
}
