package com.example.lenovoq.skripsiq.Presensi;

public class MatkulDosen_Obj {
    private int tahun;
    private String periode, kd_mk, nama_mk, kelas, hari, jam_mulai, jam_selesai;


    public MatkulDosen_Obj(int tahun, String periode, String kd_mk, String nama_mk, String kelas,
                           String hari, String jam_mulai, String jam_selesai) {
        this.tahun = tahun;
        this.periode = periode;
        this.kd_mk = kd_mk;
        this.nama_mk = nama_mk;
        this.kelas = kelas;
        this.hari = hari;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
    }

    public int getTahun() { return tahun; }

    public String getPeriode() {
        return periode;
    }

    public String getKd_mk() {
        return kd_mk;
    }

    public String getNama_mk() {
        return nama_mk;
    }

    public String getKelas() {
        return kelas;
    }

    public String getHari() {
        return hari;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

}