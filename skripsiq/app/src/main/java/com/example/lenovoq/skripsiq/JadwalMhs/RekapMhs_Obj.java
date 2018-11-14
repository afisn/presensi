package com.example.lenovoq.skripsiq.JadwalMhs;

public class RekapMhs_Obj {
    private int pertemuan;
    private String tanggal, status, materi;

    public RekapMhs_Obj(int per, String tgl, String materi,String status){
        this.pertemuan = per;
        this.tanggal = tgl;
        this.status = status;
        this.materi = materi;
    }

    public int getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(int pertemuan) {
        this.pertemuan = pertemuan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }
}
