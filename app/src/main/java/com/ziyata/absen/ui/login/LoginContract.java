package com.ziyata.absen.ui.login;

import com.ziyata.absen.model.login.LoginResponse;

public interface LoginContract {
    interface View {
        void showProgress();
        void hideProgress();
        void onSukses(String msg, LoginResponse body);
        void onFailed(String msg);

    }
    interface Presenter {
        void whenLogin(String email, String password);
    }
}
