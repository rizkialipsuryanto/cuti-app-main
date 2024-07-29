package com.fame.cuti.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fame.cuti.adapter.ProfileAdapter;
import com.fame.cuti.adapter.ProfilecutiAdapter;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityRegisterBinding;
import com.fame.cuti.model.ResponseProfileModel;
import com.fame.cuti.model.ResponseProfilesisacutiModel;
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
//        v.btnDaftar.setOnClickListener(x -> prosesDaftar());
//        getDataProfil();
//        v.etJenisKelamin.setOnClickListener(x -> getKelamin());
    }
    protected void onResume() {
        super.onResume();
        getDataProfil();
    }

//    private void getKelamin() {
//        String[] pilihan = {"LAKI-LAKI", "PEREMPUAN"};
//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        alert.setTitle("Pilih Jenis Kelamin")
//                .setItems(pilihan, (dialog, which) -> {
//                    switch (which) {
//                        case 0:
//                            v.etJenisKelamin.setText(pilihan[0]);
//                            break;
//                        case 1:
//                            v.etJenisKelamin.setText(pilihan[1]);
//                            break;
//                        default:
//                            b.toastLong("Tidak diketahui");
//                            break;
//                    }
//                });
//        alert.show();
//    }
private void getDataProfil() {
    Map<String, String> query = new HashMap<>();
//        query.put("uid", preferences.getCredential().getData().getUid());
    query.put("uid", "1");

    Api.createService(Repo.class)
            .listProfile(query)
            .enqueue(new Callback<ResponseProfileModel>() {
                @Override
                public void onResponse(Call<ResponseProfileModel> call, Response<ResponseProfileModel> response) {

                    if (response.isSuccessful()) {
                        if(response.body().getStatus().toString().equals("true")) {
//                            ResponseProfileModel responseProfil = response.body();
//                            ProfileAdapter profileAdapter = new ProfileAdapter(context, responseProfil);
//                            profileAdapter.setHasStableIds(true);
//                            LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
//                            if(response.body().getStatus().toString().equals("true")) {
//                                v.layoutNotFound.setVisibility(View.VISIBLE);
//                                v.tvError.setText("Data tidak ditemukan");
                                Log.d("OBJEK", response.body().toString());
                                Log.d("OBJEK", response.body().getData().getUid().toString());
                                Log.d("OBJEK", response.body().getData().getNama_emp().toString());

//                                v.etNik.setText(response.body().getData().getNik().toString());
                            v.etNik.setText(response.body().getData().getNik().toString());
                            v.etNip.setText(response.body().getData().getNip().toString());
                            v.etNama.setText(response.body().getData().getNama_emp().toString());
                            v.etStatusPegawai.setText(response.body().getData().getStatus_pegawai().toString());
                            v.etProfesi.setText(response.body().getData().getNama_profesi().toString());
                            v.etAlamat.setText(response.body().getData().getAlamat().toString());
                            v.etJenisKelamin.setText(response.body().getData().getJenis_kelamin().toString());
                            v.etNoHp.setText(response.body().getData().getTelp_emp().toString());
                            v.etUsername.setText(response.body().getData().getUsername().toString());
                            v.etPassword.setText(response.body().getData().getPassw().toString());
                            v.etKepalaInstalasi.setText(response.body().getData().getKepala_instalasi().toString());
                            v.etVerifikatorCuti.setText(response.body().getData().getVerifikator_cuti().toString());
//                            }else {
////                                v.layoutNotFound.setVisibility(View.GONE);
//                                Log.d("OBJEK", response.body().toString());
//                                Log.d("OBJEK", response.body().getData().toString());
//                            }
                        }else {
                            Log.e("TAG", "onResponse: 200: Error");
//                            v.layoutNotFound.setVisibility(View.VISIBLE);
//                            v.tvError.setText(response.body().getMessage());
                        }
                    }else {
                        Log.e("TAG", "onResponse: Error");
//                        v.layoutNotFound.setVisibility(View.VISIBLE);
//                        v.tvError.setText("Terjadi kesalahan");
                    }
                }

                @Override
                public void onFailure(Call<ResponseProfileModel> call, Throwable t) {
//                    v.sweepRefresh.setRefreshing(false);
                    Log.e("TAG", "onFailure: " + t.getMessage());
//                    v.layoutNotFound.setVisibility(View.VISIBLE);
//                    v.tvError.setText(t.getMessage());
                }
            });
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