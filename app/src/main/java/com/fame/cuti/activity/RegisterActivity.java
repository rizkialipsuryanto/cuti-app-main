package com.fame.cuti.activity;

import androidx.appcompat.app.AlertDialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityRegisterBinding;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Core {

    ActivityRegisterBinding v;
    private String jenisKelamin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
        settingComponent();
    }

    private void settingComponent() {
        v.btnDaftar.setOnClickListener(x -> prosesDaftar());
        v.etJenisKelamin.setOnClickListener(x -> getKelamin());
    }

    private void getKelamin() {
        String[] pilihan = {"LAKI-LAKI", "PEREMPUAN"};
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Pilih Jenis Kelamin")
                .setItems(pilihan, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            v.etJenisKelamin.setText(pilihan[0]);
                            break;
                        case 1:
                            v.etJenisKelamin.setText(pilihan[1]);
                            break;
                        default:
                            b.toastLong("Tidak diketahui");
                            break;
                    }
                });
        alert.show();
    }

    private void prosesDaftar() {
        ProgressDialog loading = new ProgressDialog(context);
        loading.setTitle("Proses Daftar");
        loading.setMessage("Mohon tunggu beberapa saat ...");
        loading.setCancelable(false);
        loading.show();

        Map<String, String> r = new HashMap<>();
        r.put("username", v.etUsername.getText().toString());
        r.put("password", v.etPassword.getText().toString());
        r.put("nama", v.etNama.getText().toString());
        r.put("jenis_kelamin", v.etJenisKelamin.getText().toString());
        r.put("no_hp", v.etNoHp.getText().toString());
        r.put("konfirmasi_password", v.etPasswordKonfirmasi.getText().toString());

        Log.e(TAG, "prosesDaftar: " + new Gson().toJson(r));

//        Api.createService(context, Repo.class)
//                .register(r)
//                .enqueue(new Callback<ResponseUniversalModel>() {
//                    @Override
//                    public void onResponse(Call<ResponseUniversalModel> call, Response<ResponseUniversalModel> response) {
//                        loading.dismiss();
//                        if (response.isSuccessful()) {
//                            b.toastLong(response.body().getMessage());
//                            if (response.body().getCode() == 200) {
//                                finish();
//                            }
//                        } else {
//                            ce.showError(response.errorBody());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseUniversalModel> call, Throwable t) {
//                        loading.dismiss();
//                        b.swal_warning(t.getMessage());
//                    }
//                });
    }
}