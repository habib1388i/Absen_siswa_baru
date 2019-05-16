package com.ziyata.absen.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("id_user")
    private String id_user;

    @SerializedName("id_kelas")
    private String idKelas;

    @SerializedName("nama_siswa")
    private String namaSiswa;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("jenkel")
    private String jenkel;

    @SerializedName("no_telp")
    private String noTelp;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
