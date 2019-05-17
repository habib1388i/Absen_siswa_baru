package com.ziyata.absen.ui.Profil;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ziyata.absen.R;
import com.ziyata.absen.model.login.LoginData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment implements ProfilContract.View {


    @BindView(R.id.picture)
    CircleImageView picture;
    @BindView(R.id.fabChoosePic)
    FloatingActionButton fabChoosePic;
    @BindView(R.id.layoutPicture)
    RelativeLayout layoutPicture;
    @BindView(R.id.edt_nama)
    EditText edtNama;
    @BindView(R.id.edt_alamat)
    EditText edtAlamat;
    @BindView(R.id.edt_notelp)
    EditText edtNotelp;
    @BindView(R.id.spin_gender)
    Spinner spinGender;
    @BindView(R.id.layoutProfil)
    CardView layoutProfil;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.layoutJenkel)
    CardView layoutJenkel;
    Unbinder unbinder;

    private ProfilPresenter mProfilpresenter = new ProfilPresenter(this);

    private String idSiswa, nama, alamat, notelp;
    private int gender;
    private Menu action;

    private String mGender;
    private static final int GENDER_MALE = 1;
    private static final int GENDER_FEMALE = 2;
    private ProgressDialog progressDialog;


    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupSpiner();
        mProfilpresenter.getDataUser(getContext());
        setHasOptionsMenu(true);
        return view;
    }

    private void setupSpiner() {
        final ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.array_gender_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinGender.setAdapter(genderSpinnerAdapter);
        spinGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selection = (String) adapterView.getItemAtPosition(5);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = "L";
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = "P";
                    }
                }
            }

        });
    }


    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("saving...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showSuccessUpdate(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        mProfilpresenter.getDataUser(getContext());

    }

    @Override
    public void showDataUser(LoginData loginData) {
        idSiswa = loginData.getId_user();
        nama = loginData.getNamaSiswa();
        alamat = loginData.getAlamat();
        notelp = loginData.getNoTelp();
        if (loginData.getJenkel().equals("L")) {
            gender = 1;
        } else {
            gender = 2;
        }
        if (!TextUtils.isEmpty(idSiswa)) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profil" + nama);
            edtNama.setText(nama);
            edtAlamat.setText(alamat);
            edtNotelp.setText(notelp);
            switch (gender) {
                case GENDER_MALE:
                    Log.i("cek male", String.valueOf(gender));
                    spinGender.setSelection(0);
                    break;

                case GENDER_FEMALE:
                    spinGender.setSelection(1);
                    break;
            }
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profil");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_logout)
    public void onViewClicked() {
        mProfilpresenter.logoutSession(getContext());
        getActivity().finish();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                editMode();
                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);
                return true;
            case R.id.menu_save:
                if (!TextUtils.isEmpty(idSiswa)) {
                    if (TextUtils.isEmpty(edtNama.getText().toString()) ||
                            TextUtils.isEmpty(edtAlamat.getText().toString()) ||
                            TextUtils.isEmpty(edtNotelp.getText().toString())) {
                        AlertDialog.Builder alertdialog = new AlertDialog.Builder(getContext());
                        alertdialog.setMessage("please compalate the field!");
                        alertdialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertdialog.show();
                    } else {
                        LoginData loginData = new LoginData();
                        loginData.setId_user(idSiswa);
                        loginData.setNamaSiswa(edtNama.getText().toString());
                        loginData.setAlamat(edtAlamat.getText().toString());
                        loginData.setNoTelp(edtNotelp.getText().toString());
                        loginData.setJenkel(mGender);
                        mProfilpresenter.updateDataUser(getContext(), loginData);
                        readMode();
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                    }
                }else {
                    readMode();
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                }
                return true;
                default:
                    return super.onOptionsItemSelected(item);

        }
    }
    @SuppressLint("RestrictedApi")
    private void readMode() {
        edtNama.setFocusableInTouchMode(true);
        edtAlamat.setFocusableInTouchMode(true);
        edtNotelp.setFocusableInTouchMode(true);
        spinGender.setEnabled(true);
        fabChoosePic.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("RestrictedApi")
    private void editMode() {
        edtNama.setFocusableInTouchMode(true);
        edtNotelp.setFocusableInTouchMode(true);
        edtAlamat.setFocusableInTouchMode(true);
        edtNama.setFocusable(false);
        edtNama.setFocusable(false);
        edtNama.setFocusable(false);
        spinGender.setEnabled(true);
        fabChoosePic.setVisibility(View.INVISIBLE);

    }
}
