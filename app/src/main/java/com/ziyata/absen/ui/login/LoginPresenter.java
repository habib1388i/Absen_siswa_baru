package com.ziyata.absen.ui.login;

import com.ziyata.absen.data.remote.ApiLoginClient;
import com.ziyata.absen.data.remote.ApiLoginInterface;
import com.ziyata.absen.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;
    ApiLoginInterface apiLoginInterface = ApiLoginClient.getClient().create(ApiLoginInterface.class);

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }


    @Override
    public void whenLogin(String email, String password) {
        if (email == null || email.isEmpty()){
            view.onFailed("Masukan Email Anda");
            return;
        }
        if (password == null || password.isEmpty()) {
            view.onFailed("Masukan Password Anda");
            return;
    }
    view.showProgress();
        Call<LoginResponse> loginResponseCall = apiLoginInterface.loginUser(email, password);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.onSukses("Data Berhasil di Tambahkan .. ", response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideProgress();
                view.onFailed("Login Anda Gagal .. ");
            }
            });
    }
}
