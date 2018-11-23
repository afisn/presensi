package com.example.lenovoq.skripsiq.Presensi;

public class NamaMhs_Obj {
    private String nrp, nama, status;
    private boolean check;
    private int id_status;

    public NamaMhs_Obj(String nrp, String nama) {
        this.nrp = nrp;
        this.nama = nama;
        this.status = status;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return check;
    }

    public void setSelected(boolean check) {
        this.check = check;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

}
