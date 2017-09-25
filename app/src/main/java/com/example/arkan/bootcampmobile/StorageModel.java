package com.example.arkan.bootcampmobile;

/**
 * Created by arkan on 9/23/17.
 */

public class StorageModel {

    private String urlApi = "192.168.56.1:3000/";
    private int idPembeli;
    private String nm_pembeli;
    private String email_pembeli;
    private String password;
    private String hp_pembeli;
    private String gd_pembeli;

    private double uang_transfer_validasi;
    private String pilihan_bank;

    private String tgl_order;

    private double harga_tiket;
    private double total_transfer;


    public String getUrlApi() {
        return urlApi;
    }

    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }

    public int getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(int idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getNm_pembeli() {
        return nm_pembeli;
    }

    public void setNm_pembeli(String nm_pembeli) {
        this.nm_pembeli = nm_pembeli;
    }

    public String getEmail_pembeli() {
        return email_pembeli;
    }

    public void setEmail_pembeli(String email_pembeli) {
        this.email_pembeli = email_pembeli;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHp_pembeli() {
        return hp_pembeli;
    }

    public void setHp_pembeli(String hp_pembeli) {
        this.hp_pembeli = hp_pembeli;
    }

    public String getGd_pembeli() {
        return gd_pembeli;
    }

    public void setGd_pembeli(String gd_pembeli) {
        this.gd_pembeli = gd_pembeli;
    }

    public double getUang_transfer_validasi() {
        return uang_transfer_validasi;
    }

    public void setUang_transfer_validasi(double uang_transfer_validasi) {
        this.uang_transfer_validasi = uang_transfer_validasi;
    }

    public String getPilihan_bank() {
        return pilihan_bank;
    }

    public void setPilihan_bank(String pilihan_bank) {
        this.pilihan_bank = pilihan_bank;
    }

    public String getTgl_order() {
        return tgl_order;
    }

    public void setTgl_order(String tgl_order) {
        this.tgl_order = tgl_order;
    }

    public double getHarga_tiket() {
        return harga_tiket;
    }

    public void setHarga_tiket(double harga_tiket) {
        this.harga_tiket = harga_tiket;
    }

    public double getTotal_transfer() {
        return total_transfer;
    }

    public void setTotal_transfer(double total_transfer) {
        this.total_transfer = total_transfer;
    }
}
