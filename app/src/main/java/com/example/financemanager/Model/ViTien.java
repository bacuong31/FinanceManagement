package com.example.financemanager.Model;

import java.util.Date;

public class ViTien {
    private int MaViTien = 0;
    private String TenViTien;
    private double TienDu;
    private String GhiChu;

    public ViTien(int maViTien, String tenViTien, double tienDu, String ghiChu) {
        MaViTien = maViTien;
        TenViTien = tenViTien;
        TienDu = tienDu;
        GhiChu = ghiChu;
    }

    public int getMaViTien() {
        return MaViTien;
    }

    public void setMaViTien(int maViTien) {
        MaViTien = maViTien;
    }

    public String getTenViTien() {
        return TenViTien;
    }

    public void setTenViTien(String tenViTien) {
        TenViTien = tenViTien;
    }

    public double getTienDu() {
        return TienDu;
    }

    public void setTienDu(double tienDu) {
        TienDu = tienDu;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public ViTien(String tenViTien, double tienDu, String ghiChu) {
        TenViTien = tenViTien;
        TienDu = tienDu;
        GhiChu = ghiChu;
    }

    public ViTien(){}

}
