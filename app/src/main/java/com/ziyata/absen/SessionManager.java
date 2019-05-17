package com.ziyata.absen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ziyata.absen.model.login.LoginData;
import com.ziyata.absen.ui.login.LoginActivity;

public class SessionManager {
    // Membuat variable global untuk shared preference
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final Context context;

    public SessionManager(Context context) {
        this.context = context;

        // Membuat object SharedPreference untuk siap digunakan
        pref = context.getSharedPreferences(Constant.pref_name, 0);
        // Membuat SharedPreference dengan mode edit
        editor = pref.edit();
    }

    // Function untuk membuat session login
    public void createSession(LoginData loginData){
        // Memasukkan data user yang sudah login ke dalam SharedPreference
        editor.putBoolean(Constant.KEY_IS_LOGIN, true);
        editor.putString(Constant.KEY_USER_ID, loginData.getId_user());
        editor.putString(Constant.KEY_ID_KELAS, loginData.getIdKelas());
        editor.putString(Constant.KEY_USER_NAMA, loginData.getNamaSiswa());
        editor.putString(Constant.KEY_USER_ALAMAT, loginData.getAlamat());
        editor.putString(Constant.KEY_USER_JENKEL, loginData.getJenkel());
        editor.putString(Constant.KEY_USER_NOTELP, loginData.getNoTelp());
        editor.putString(Constant.KEY_USER_USERNAME, loginData.getUsername());
        // Mengeksekusi penyimpanan
        editor.commit();
    }

    // Function untuk mencek apakah user sudah pernah login
    public boolean isLogin(){
        // Mengembalikan nilai boolean dengan mengambil data dari pref KEY_IS_LOGIN
        return pref.getBoolean(Constant.KEY_IS_LOGIN, false);
    }

    // Function untuk melakukan logout atau menghapus isi di dalam shared preference
    public void logout(){
        // Memanggil method clear untuk menghapus data sharedpreference
        editor.clear();
        // Mengeksekusi perintah clear
        editor.commit();
        // Membuat intent untuk berpindah halaman
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



}
