package com.fame.cuti.activity.atasanLangsung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fame.cuti.R;
import com.fame.cuti.adapter.ListApproveAtasanAdapter;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityListDisetujuiBinding;
import com.fame.cuti.model.ResponseListApproveAtasanCutiModel;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDisetujuiActivity extends Core {

    ActivityListDisetujuiBinding v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityListDisetujuiBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
    }

    protected void onResume() {
        super.onResume();
        getData();
//        getStatus();
    }

    private void getData() {

//        if (id_status.toString() == ""){
        v.layoutNotFound.setVisibility(View.VISIBLE);
        Map<String, String> query = new HashMap<>();
        query.put("uid", preferences.getCredential().getData().getUid());
//        query.put("uid", "3");
//        query.put("status", id_status.toString());
        query.put("page", "1");
        query.put("perpage", "100");

        Api.createService(Repo.class)
                .listApproveInstalasi(query)
                .enqueue(new Callback<ResponseListApproveAtasanCutiModel>() {
                    @Override
                    public void onResponse(Call<ResponseListApproveAtasanCutiModel> call, Response<ResponseListApproveAtasanCutiModel> response) {
                        v.sweepRefresh.setRefreshing(false);
                        if (response.isSuccessful()) {
                            if(response.body().getCode() == 200) {
                                ResponseListApproveAtasanCutiModel responseListRiwayatModel = response.body();
                                ListApproveAtasanAdapter bookingAdapter = new ListApproveAtasanAdapter(context, responseListRiwayatModel);
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
                    public void onFailure(Call<ResponseListApproveAtasanCutiModel> call, Throwable t) {
                        v.sweepRefresh.setRefreshing(false);
                        Log.e("TAG", "onFailure: " + t.getMessage());
                        v.layoutNotFound.setVisibility(View.VISIBLE);
                        v.tvError.setText(t.getMessage());
                    }
                });

    }
}