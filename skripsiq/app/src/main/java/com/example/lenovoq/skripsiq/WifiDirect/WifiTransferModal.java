package com.example.lenovoq.skripsiq.WifiDirect;

import java.io.Serializable;

public class WifiTransferModal implements Serializable {

    private String FileName;
    private Long FileLength;
    private String InetAddress;


    public WifiTransferModal() {

    }

    public WifiTransferModal(String inetaddress) {
        this.InetAddress = inetaddress;
    }

    public WifiTransferModal(String name, Long filelength) {
        this.FileName = name;
        this.FileLength = filelength;
    }

    public String getInetAddress() {
        return InetAddress;
    }

    public void setInetAddress(String inetAddress) {
        InetAddress = inetAddress;
    }


    public Long getFileLength() {
        return FileLength;
    }

    public void setFileLength(Long fileLength) {
        FileLength = fileLength;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }
}

