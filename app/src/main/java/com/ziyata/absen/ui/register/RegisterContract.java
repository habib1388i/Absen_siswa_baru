package com.ziyata.absen.ui.register;

import com.ziyata.absen.model.login.LoginData;

public interface RegisterContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showError(String message);
        void showRegisterSucces(String message);
    }

    interface Presenter{
        void doRegisterUser(LoginData loginData);
    }
}
