package com.fame.cuti.activity.driver;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fame.cuti.adapter.TransaksiAdapter;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityMainDriverBinding;
import com.fame.cuti.model.TransaksiResponseModel;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDriverActivity extends Core {

    ActivityMainDriverBinding v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityMainDriverBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
        settingComponent();
    }

    private void settingComponent() {
        v.ivLogout.setOnClickListener(x -> preferences.logout());
        v.tvNama.setText(preferences.getCredential().getData().getNama_emp());
        v.btnTransaksiSelengkapnya.setOnClickListener(x -> startActivity(new Intent(context, ListTransaksiActivity.class)));
        v.sweepRefresh.setOnRefreshListener(this::getData);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        v.layoutNotFound.setVisibility(View.VISIBLE);
        Map<String, String> query = new HashMap<>();
        query.put("id_driver", preferences.getCredential().getData().getUid());
        query.put("page", "1");
        query.put("perpage", "10");

        Api.createService(Repo.class)
                .listTransaksi(query)
                .enqueue(new Callback<TransaksiResponseModel>() {
                    @Override
                    public void onResponse(Call<TransaksiResponseModel> call, Response<TransaksiResponseModel> response) {
                        v.sweepRefresh.setRefreshing(false);
                        if (response.isSuccessful()) {
                            if(response.body().getCode() == 200) {
                                TransaksiResponseModel transaksiResponseModel = response.body();
                                TransaksiAdapter transaksiAdapter = new TransaksiAdapter(context, transaksiResponseModel);
                                transaksiAdapter.setHasStableIds(true);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                                v.shimmer.setLayoutManager(layoutManager);
                                v.shimmer.setAdapter(transaksiAdapter);
                                if(response.body().getData().getItems().size() < 1) {
                                    v.layoutNotFound.setVisibility(View.VISIBLE);
                                    v.tvError.setText("Data tidak ditemukan");
                                }else {
                                    v.layoutNotFound.setVisibility(View.GONE);
                                }
                            } else {
                                Log.e("TAG", "onResponse: 200: Error");
                                v.layoutNotFound.setVisibility(View.VISIBLE);
                                v.tvError.setText(response.body().getMessage());
                            }
                        } else {
                            Log.e("TAG", "onResponse: Error");
                            v.layoutNotFound.setVisibility(View.VISIBLE);
                            v.tvError.setText("Terjadi kesalahan");
                        }
                    }

                    @Override
                    public void onFailure(Call<TransaksiResponseModel> call, Throwable t) {
                        v.sweepRefresh.setRefreshing(false);
                        Log.e("TAG", "onFailure: " + t.getMessage());
                        v.layoutNotFound.setVisibility(View.VISIBLE);
                        v.tvError.setText(t.getMessage());
                    }
                });
    }
}