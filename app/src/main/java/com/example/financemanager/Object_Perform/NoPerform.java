package com.example.financemanager.Object_Perform;

import java.util.Date;

public class NoPerform {
    private Date NgayTra;
    private String NoiDung;
    private Double SoTien;
    private int MaTienNo;

    public int getMaTienNo() {
        return MaTienNo;
    }

    public void setMaTienNo(int maTienNo) {
        MaTienNo = maTienNo;
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

    public NoPerform(Date ngayTra, String noiDung, Double soTien) {
        NgayTra = ngayTra;
        NoiDung = noiDung;
        SoTien = soTien;
    }
    public NoPerform(){}
}
