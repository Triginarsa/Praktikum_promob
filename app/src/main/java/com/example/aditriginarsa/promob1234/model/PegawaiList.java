package com.example.aditriginarsa.promob1234.model;



public class PegawaiList {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSerius() {
        return serius;
    }

    public void setSerius(String serius) {
        this.serius = serius;
    }

    public String getBerhasil() {
        return berhasil;
    }

    public void setBerhasil(String berhasil) {
        this.berhasil = berhasil;
    }

    public String getGagal() {
        return gagal;
    }

    public void setGagal(String gagal) {
        this.gagal = gagal;
    }

    public PegawaiList(){
        this.id     = id;
        this.nama   = nama;
        this.email  = email;
        this.jk     = jk;
        this.hobby  = hobby;
        this.serius = serius;
    }

    private String id;
    private String nama;
    private String email;
    private String jk;
    private String hobby;
    private String serius;
    private String berhasil;
    private String gagal;
}
