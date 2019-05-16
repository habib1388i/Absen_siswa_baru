package com.ziyata.absen.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ziyata.absen.R;
import com.ziyata.absen.model.login.LoginData;
import com.ziyata.absen.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    @BindView(R.id.edtIdKelas)
    EditText edtIdKelas;
    @BindView(R.id.edtnama)
    EditText edtnama;
    @BindView(R.id.edtalamat)
    EditText edtalamat;
    @BindView(R.id.edtnotelp)
    EditText edtnotelp;
    @BindView(R.id.edtusername)
    EditText edtusername;
    @BindView(R.id.edtpassword)
    TextInputEditText edtpassword;
    @BindView(R.id.edtpasswordconfirm)
    TextInputEditText edtpasswordconfirm;
    @BindView(R.id.radioLaki)
    RadioButton radioLaki;
    @BindView(R.id.radioPerempuan)
    RadioButton radioPerempuan;
    @BindView(R.id.btnregister)
    Button btnregister;

    private ProgressDialog progressDialog;
    private RegisterPresenter mRegisterPresenter = new RegisterPresenter(this);
    private String jenkel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        setRadio();
    }

    private void setRadio() {
        if (radioLaki.isChecked()) {
            jenkel = "L";
        } else {
            jenkel = "P";
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterSucces(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @OnClick({R.id.radioLaki, R.id.radioPerempuan, R.id.btnregister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radioLaki:
                jenkel = "L";
                break;
            case R.id.radioPerempuan:
                jenkel = "P";
                break;
            case R.id.btnregister:
                LoginData mLoginData = new LoginData();

                mLoginData.setIdKelas(edtIdKelas.getText().toString());
                mLoginData.setNamaSiswa(edtnama.getText().toString());
                mLoginData.setAlamat(edtalamat.getText().toString());
                mLoginData.setNoTelp(edtnotelp.getText().toString());
                mLoginData.setUsername(edtusername.getText().toString());
                mLoginData.setPassword(edtpassword.getText().toString());
                mLoginData.setJenkel(jenkel);


                mRegisterPresenter.doRegisterUser(mLoginData);
                break;
        }
    }
}
