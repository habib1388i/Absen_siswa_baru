package com.ziyata.absen.data.remote;

import com.ziyata.absen.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiLoginInterface {
    @FormUrlEncoded
    @POST("login_user.php")
    Call<LoginResponse> loginUser(@Field("email") String email,
                                  @Field("password")String password);
}
