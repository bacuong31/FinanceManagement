package com.example.financemanager.Model;


public class DanhMucChiTieu {
    private String MaChiTieu = "";
    private String TenChiTieu;
    public DanhMucChiTieu(){}
    public DanhMucChiTieu(String tenChiTieu) {
        TenChiTieu = tenChiTieu;
    }

    public String getMaChiTieu() {
        return MaChiTieu;
    }

    public void setMaChiTieu(String maChiTieu) {
        MaChiTieu = maChiTieu;
    }

    public String getTenChiTieu() {
        return TenChiTieu;
    }

    public void setTenChiTieu(String tenChiTieu) {
        TenChiTieu = tenChiTieu;
    }
}
