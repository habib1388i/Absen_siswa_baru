package com.ziyata.absen.ui.Profil;

import android.content.Context;

import com.ziyata.absen.model.login.LoginData;

public interface ProfilContract {

    interface View{
        void showProgress();
        void hideProgress();
        void showSuccessUpdate(String message);
        void showDataUser(LoginData loginData);
    }

    interface Presenter{
        void updateDataUser(Context context, LoginData loginData);
        void getDataUser(Context context);
        void logoutSession(Context context);
    }
}
