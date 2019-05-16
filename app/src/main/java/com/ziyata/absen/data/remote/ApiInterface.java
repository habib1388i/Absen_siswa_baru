package com.ziyata.absen.data.remote;

import com.ziyata.absen.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("registeruser.php")
    Call<LoginResponse> registerUser(
        @Field("id_kelas") String idKelas,
        @Field("username") String username,
        @Field("password") String password,
        @Field("nama_siswa") String namaSiswa,
        @Field("alamat") String alamat,
        @Field("jenkel") String jenkel,
        @Field("no_telp") String no_telp
    );

    @FormUrlEncoded
    @POST("updateuser.php")
    Call<LoginResponse> updateUser(
            @Field("iduser") int iduser,
            @Field("namauser") String namauser,
            @Field("alamat") String alamat,
            @Field("jenkel") String jenkel,
            @Field("notelp") String notelp
    );
}
