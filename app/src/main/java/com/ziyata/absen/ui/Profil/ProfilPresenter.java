package com.ziyata.absen.ui.Profil;

import android.content.Context;
import android.content.SharedPreferences;


import com.ziyata.absen.Constant;
import com.ziyata.absen.SessionManager;
import com.ziyata.absen.data.remote.ApiClient;
import com.ziyata.absen.data.remote.ApiInterface;
import com.ziyata.absen.model.login.LoginData;
import com.ziyata.absen.model.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilPresenter implements ProfilContract.Presenter {

    private final ProfilContract.View view;
    private SharedPreferences pref;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public ProfilPresenter(ProfilContract.View view) {
        this.view = view;
    }

    @Override
    public void updateDataUser(final Context context, final LoginData loginData) {
        // show progress
        view.showProgress();

        // Membuat object call dan memanggil method updateUser serta mengirimkan datanya
        Call<LoginResponse> call = apiInterface.updateUser(Integer.valueOf(
                loginData.getId_user()),
                Integer.valueOf(loginData.getIdKelas()),
                loginData.getNamaSiswa(),
                loginData.getAlamat(),
                loginData.getJenkel(),
                loginData.getNoTelp());

        // Mengeksekusi call
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                // Menghilangkan progress
                view.hideProgress();

                // Mengecek response dan isi body
                if (response.isSuccessful() && response.body() != null){
                    // Mencek apakah result 1
                    if (response.body().getResult() == 1){
                        // Setelah berhasil update ke server online lalu update ke SharedPreference
                        // Membuat object SharedPreference yang sudah ada di SessionManager
                        pref = context.getSharedPreferences(Constant.pref_name,0);
                        // Mengubah mode SharedPreference menjadi edit
                        SharedPreferences.Editor editor = pref.edit();
                        // Memasukkan data kedalam SharedPreference
                        editor.putString(Constant.KEY_USER_NAMA, loginData.getNamaSiswa());
                        editor.putString(Constant.KEY_USER_ALAMAT, loginData.getAlamat());
                        editor.putString(Constant.KEY_USER_NOTELP, loginData.getNoTelp());
                        editor.putString(Constant.KEY_USER_JENKEL,loginData.getJenkel());
                        // apply perubahan
                        editor.apply();
                        view.showSuccessUpdate(response.body().getMessage());
                    }else {
                        view.showSuccessUpdate(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideProgress();
                view.showSuccessUpdate(t.getMessage());
            }
        });
    }

    @Override
    public void getDataUser(Context context) {
        // Pengambilan data dari SharedPreference
        pref = context.getSharedPreferences(Constant.pref_name,0);

        // Membuat object model logindata untuk menampung
        LoginData loginData = new LoginData();

        // Memasukkan data ke SharedPreference ke dalam model loginData
        loginData.setId_user(pref.getString(Constant.KEY_USER_ID,""));
        loginData.setNamaSiswa(pref.getString(Constant.KEY_USER_NAMA,""));
        loginData.setAlamat(pref.getString(Constant.KEY_USER_ALAMAT,""));
        loginData.setNoTelp(pref.getString(Constant.KEY_USER_NOTELP,""));
        loginData.setJenkel(pref.getString(Constant.KEY_USER_JENKEL,""));

        // kirim data model loginData ke view
        view.showDataUser(loginData);

    }

    @Override
    public void logoutSession(Context context) {
        // Membuat object Session Manager untuk memanggil method logout
        SessionManager msessionManager = new SessionManager(context);
        msessionManager.logout();
    }
}
