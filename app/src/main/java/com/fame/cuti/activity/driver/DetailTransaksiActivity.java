package com.fame.cuti.activity.driver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityDetailTransaksiBinding;
import com.fame.cuti.model.TransaksiResponseModel;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiActivity extends Core {

    ActivityDetailTransaksiBinding v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityDetailTransaksiBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
        settingComponent();
    }

    private void settingComponent() {
        v.sweepRefresh.setOnRefreshListener(this::getData);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        v.sweepRefresh.setRefreshing(false);
        Intent intent = getIntent();
        v.tvJudul.setText("DETAIL TRANSAKSI");
        v.etKodeBooking.setText(intent.getStringExtra("kode_booking"));
        v.etNamaUser.setText(intent.getStringExtra("nama_user"));
        v.etAlamatUser.setText(intent.getStringExtra("alamat"));
        v.etTanggal.setText(intent.getStringExtra("tanggal"));
        if(intent.getStringExtra("status_jemput").equalsIgnoreCase("1")) {
            v.etStatus.setText("MENUNGGU");
            v.tvAction.setText("SAYA SUDAH JEMPUT");
        } else if (intent.getStringExtra("status_jemput").equalsIgnoreCase("2")) {
            v.etStatus.setText("DIJEMPUT");
            v.tvAction.setText("SELESAI");
        } else if (intent.getStringExtra("status_jemput").equalsIgnoreCase("3")) {
            v.etStatus.setText("SELESAI");
            v.btnMenujuLokasi.setVisibility(View.GONE);
            v.btnAction.setVisibility(View.GONE);
        }
        v.etKotaAsal.setText(intent.getStringExtra("kota_origin"));
        v.etKotaTujuan.setText(intent.getStringExtra("kota_destination"));
        v.btnMenujuLokasi.setOnClickListener(x -> {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+intent.getStringExtra("lat_jemput")+","+intent.getStringExtra("lng_jemput"));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
        v.btnAction.setOnClickListener(x -> {
            ProgressDialog loading = new ProgressDialog(context);
            loading.setTitle("Proses Login");
            loading.setMessage("Mohon tunggu beberapa saat ...");
            loading.setCancelable(false);
            loading.show();

            Map<String, String> r = new HashMap<>();
            r.put("id_driver", preferences.getCredential().getData().getUid());
            r.put("kode_booking", intent.getStringExtra("kode_booking"));

            if(intent.getStringExtra("status_jemput").equalsIgnoreCase("1")) {

                Api.createService(context, Repo.class)
                        .prosesJemput(r)
                        .enqueue(new Callback<TransaksiResponseModel>() {
                            @Override
                            public void onResponse(Call<TransaksiResponseModel> call, Response<TransaksiResponseModel> response) {
                                loading.dismiss();
                                if (response.isSuccessful()) {
                                    if (response.body().getCode() == 200) {
                                        b.swal_sukses("Berhasil melakukan penjemputan 😊");
                                    } else {
                                        b.swal_warning(response.body().getMessage());
                                    }
                                } else {
                                    ce.showError(response.errorBody());
                                }
                            }

                            @Override
                            public void onFailure(Call<TransaksiResponseModel> call, Throwable t) {
                                loading.dismiss();
                                b.swal_warning(t.getMessage());
                            }
                        });

            } else if(intent.getStringExtra("status_jemput").equalsIgnoreCase("2")) {
                Api.createService(context, Repo.class)
                        .prosesSelesai(r)
                        .enqueue(new Callback<TransaksiResponseModel>() {
                            @Override
                            public void onResponse(Call<TransaksiResponseModel> call, Response<TransaksiResponseModel> response) {
                                loading.dismiss();
                                if (response.isSuccessful()) {
                                    if (response.body().getCode() == 200) {
                                        b.swal_sukses("Berhasil menyelesaiikan Transaksi 😊");
                                    } else {
                                        b.swal_warning(response.body().getMessage());
                                    }
                                } else {
                                    ce.showError(response.errorBody());
                                }
                            }

                            @Override
                            public void onFailure(Call<TransaksiResponseModel> call, Throwable t) {
                                loading.dismiss();
                                b.swal_warning(t.getMessage());
                            }
                        });
            }
        });
    }
}