package com.example.lenovoq.skripsiq.History;

public class Rekapan_Obj  {
    private String  metode, materi, catatan;
    private int pertemuan, hadir, sakit, ijin, tugas, alpa;

    public Rekapan_Obj (int pertemuan,String materi, String metode, String catatan
                        , int hadir, int sakit, int ijin, int tugas, int alpa){
        this.pertemuan = pertemuan;
        this.materi = materi;
        this.metode = metode;
        this.catatan = catatan;
        this.hadir = hadir;
        this.sakit = sakit;
        this.ijin = ijin;
        this.tugas = tugas;
        this.alpa = alpa;

    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public int getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(int pertemuan) {
        this.pertemuan = pertemuan;
    }

    public int getHadir() {
        return hadir;
    }

    public void setHadir(int hadir) {
        this.hadir = hadir;
    }

    public int getSakit() {
        return sakit;
    }

    public void setSakit(int sakit) {
        this.sakit = sakit;
    }

    public int getIjin() {
        return ijin;
    }

    public void setIjin(int ijin) {
        this.ijin = ijin;
    }

    public int getTugas() {
        return tugas;
    }

    public void setTugas(int tugas) {
        this.tugas = tugas;
    }

    public int getAlpa() {
        return alpa;
    }

    public void setAlpa(int alpa) {
        this.alpa = alpa;
    }
}
