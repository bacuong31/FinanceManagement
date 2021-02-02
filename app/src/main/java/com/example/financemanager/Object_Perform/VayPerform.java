package com.example.financemanager.Object_Perform;

import java.util.Date;

public class VayPerform {
    private Date NgayTra;
    private String NoiDung;
    private Double SoTien;
    private int MaVayMuon;

    public int getMaVayMuon() {
        return MaVayMuon;
    }

    public void setMaVayMuon(int maVayMuon) {
        MaVayMuon = maVayMuon;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date ngayTra) {
        NgayTra = ngayTra;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public Double getSoTien() {
        return SoTien;
    }

    public void setSoTien(Double soTien) {
        SoTien = soTien;
    }

    public VayPerform(Date ngayTra, String noiDung, Double soTien) {
        NgayTra = ngayTra;
        NoiDung = noiDung;
        SoTien = soTien;
    }
    public VayPerform(){}
}
