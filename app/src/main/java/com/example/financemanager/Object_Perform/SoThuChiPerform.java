package com.example.financemanager.Object_Perform;

public class SoThuChiPerform {
    private int MaViTien;
    private String TenViTien;
    private double TienDu;

    public int getMaViTien() {
        return MaViTien;
    }

    public void setMaViTien(int maViTien) {
        MaViTien = maViTien;
    }

    public SoThuChiPerform(String tenViTien, double tienDu) {
        TenViTien = tenViTien;
        TienDu = tienDu;
    }
    public SoThuChiPerform(){};

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
}
