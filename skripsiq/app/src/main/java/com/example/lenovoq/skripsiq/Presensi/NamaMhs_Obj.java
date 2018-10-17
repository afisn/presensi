package com.example.lenovoq.skripsiq.Presensi;

public class NamaMhs_Obj {
    private String nrp, nama;
    private boolean isSelected;

    public NamaMhs_Obj(String nrp, String nama) {
        this.nrp = nrp;
        this.nama = nama;
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

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
