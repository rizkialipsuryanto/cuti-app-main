package com.fame.cuti.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fame.cuti.activity.atasanLangsung.ListDisetujuiActivity;
import com.fame.cuti.activity.karyawan.BookingSekarangActivity;
import com.fame.cuti.activity.karyawan.ListMenungguActivity;
//import com.fame.cuti.adapter.BookingAdapter;
import com.fame.cuti.adapter.RiwayatAdapter;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityMainBinding;
import com.fame.cuti.model.ResponseListRiwayatCutiModel;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Core {

    ActivityMainBinding v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
        settingComponent();
    }

    private void settingComponent() {
        v.ivLogout.setOnClickListener(x -> preferences.logout());
        v.tvNama.setText("Halo, "+preferences.getCredential().getData().getNama_emp());
        v.btnBooking.setOnClickListener(x -> startActivity(new Intent(context, BookingSekarangActivity.class)));
        v.btnLihatSemua.setOnClickListener(x -> startActivity(new Intent(context, ListMenungguActivity.class)));
        v.btnAtasan.setOnClickListener(x -> startActivity(new Intent(context, ListDisetujuiActivity.class)));

        if (preferences.getCredential().getData().getHak_akses().equals("1")){
            v.btnAtasan.setVisibility(View.GONE);
        }
        else{
            v.btnAtasan.setVisibility(View.VISIBLE);
        }
//        v.imgBtnMenunggu.setOnClickListener(x -> startActivity(new Intent(context, ListMenungguActivity.class)));
//        v.imgBtnSetujui.setOnClickListener(x -> startActivity(new Intent(context, ListDisetujuiActivity.class)));
//        v.imgBtnTolak.setOnClickListener(x -> startActivity(new Intent(context, ListDitolakActivity.class)));
//        v.sweepRefresh.setOnRefreshListener(this::getData);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        v.layoutNotFound.setVisibility(View.VISIBLE);
        Map<String, String> query = new HashMap<>();
//        query.put("id_user", preferences.getCredential().getData().getId());
        query.put("uid", "1");
        query.put("page", "1");
        query.put("perpage", "10");

        Api.createService(Repo.class)
                .listRiwayat(query)
                .enqueue(new Callback<ResponseListRiwayatCutiModel>() {
                    @Override
                    public void onResponse(Call<ResponseListRiwayatCutiModel> call, Response<ResponseListRiwayatCutiModel> response) {
                        v.sweepRefresh.setRefreshing(false);
                        if (response.isSuccessful()) {
                            if(response.body().getCode() == 200) {
                                ResponseListRiwayatCutiModel responseListBookingModel = response.body();
                                RiwayatAdapter bookingAdapter = new RiwayatAdapter(context, responseListBookingModel);
                                bookingAdapter.setHasStableIds(true);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                                v.shimmer.setLayoutManager(layoutManager);
                                v.shimmer.setAdapter(bookingAdapter);
                                if(response.body().getData().getItems().size() < 1) {
                                    v.layoutNotFound.setVisibility(View.VISIBLE);
                                    v.tvError.setText("Data tidak ditemukan");
                                }else {
                                    v.layoutNotFound.setVisibility(View.GONE);
                                }
                            }else {
                                Log.e("TAG", "onResponse: 200: Error");
                                v.layoutNotFound.setVisibility(View.VISIBLE);
                                v.tvError.setText(response.body().getMessage());
                            }
                        }else {
                            Log.e("TAG", "onResponse: Error");
                            v.layoutNotFound.setVisibility(View.VISIBLE);
                            v.tvError.setText("Terjadi kesalahan");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseListRiwayatCutiModel> call, Throwable t) {
                        v.sweepRefresh.setRefreshing(false);
                        Log.e("TAG", "onFailure: " + t.getMessage());
                        v.layoutNotFound.setVisibility(View.VISIBLE);
                        v.tvError.setText(t.getMessage());
                    }
                });
    }
}