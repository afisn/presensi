package com.example.lenovoq.skripsiq.JadwalMhs;

public class JMahasiswa {

    private int met_id;
    private String kd_mk, nama_mk, kelas, hari, jam_mulai, jam_selesai, pengajar;

    public JMahasiswa(String kd_mk, String nama_mk, String kelas,
                      String hari, String jam_mulai, String jam_selesai, String pengajar) {
        this.kd_mk = kd_mk;
        this.nama_mk = nama_mk;
        this.kelas = kelas;
        this.hari = hari;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
        this.pengajar = pengajar;
    }

    public JMahasiswa(int met_id, String kd_mk, String nama_mk, String kelas) {
        this.met_id = met_id;
        this.kd_mk = kd_mk;
        this.nama_mk = nama_mk;
        this.kelas = kelas;
    }

    public int getMet_id() {
        return met_id;
    }

    public void setMet_id(int met_id) {
        this.met_id = met_id;
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

    public String getPengajar() {
        return pengajar;
    }
}
