package com.fame.cuti.activity.driver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityDetailTransaksiBinding;
import com.fame.cuti.model.ApproveResponseModel;
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
        kondisi();
    }

    private void settingComponent() {
        v.sweepRefresh.setOnRefreshListener(this::getData);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        kondisi();
    }

    private void kondisi(){
        Intent intent = getIntent();
        if (intent.getStringExtra("status").equals("Approved"))
        {
            v.btnAction.setVisibility(View.GONE);
            v.btnMenujuLokasi.setVisibility(View.GONE);
        }
        else {
            v.btnAction.setVisibility(View.VISIBLE);
            v.btnMenujuLokasi.setVisibility(View.VISIBLE);
        }
    }
    private void getData() {
        v.sweepRefresh.setRefreshing(false);
        Intent intent = getIntent();
        v.tvJudul.setText("DETAIL CUTI");
        v.etKodeBooking.setText(intent.getStringExtra("no_cuti"));
        v.etNamaUser.setText(intent.getStringExtra("nama_emp"));
//        v.etAlamatUser.setText(intent.getStringExtra("alamat"));
        v.etTanggal.setText(intent.getStringExtra("tglambilcuti"));
        v.etStatus.setText(intent.getStringExtra("status"));
//        if(intent.getStringExtra("status_jemput").equalsIgnoreCase("1")) {
//            v.etStatus.setText("MENUNGGU");
//            v.tvAction.setText("SAYA SUDAH JEMPUT");
//        } else if (intent.getStringExtra("status_jemput").equalsIgnoreCase("2")) {
//            v.etStatus.setText("DIJEMPUT");
//            v.tvAction.setText("SELESAI");
//        } else if (intent.getStringExtra("status_jemput").equalsIgnoreCase("3")) {
//            v.etStatus.setText("SELESAI");
//            v.btnMenujuLokasi.setVisibility(View.GONE);
//            v.btnAction.setVisibility(View.GONE);
//        }
//        v.etKotaAsal.setText(intent.getStringExtra("kota_origin"));
//        v.etKotaTujuan.setText(intent.getStringExtra("kota_destination"));
//        v.btnMenujuLokasi.setOnClickListener(x -> {
//            Uri gmmIntentUri = Uri.parse("google.navigation:q="+intent.getStringExtra("lat_jemput")+","+intent.getStringExtra("lng_jemput"));
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//            mapIntent.setPackage("com.google.android.apps.maps");
//            startActivity(mapIntent);
//        });
        v.btnAction.setOnClickListener(x -> {
            ProgressDialog loading = new ProgressDialog(context);
            loading.setTitle("Proses Approve");
            loading.setMessage("Mohon tunggu beberapa saat ...");
            loading.setCancelable(false);
            loading.show();

            Map<String, String> r = new HashMap<>();
            r.put("no_cuti", intent.getStringExtra("no_cuti"));
            r.put("stt_cuti", "3");

//            if(intent.getStringExtra("status_jemput").equalsIgnoreCase("1")) {

                Api.createService(context, Repo.class)
                        .approveInstalasi(r)
                        .enqueue(new Callback<ApproveResponseModel>() {
                            @Override
                            public void onResponse(Call<ApproveResponseModel> call, Response<ApproveResponseModel> response) {
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
                            public void onFailure(Call<ApproveResponseModel> call, Throwable t) {
                                loading.dismiss();
                                b.swal_warning(t.getMessage());
                            }
                        });

        });
    }
}