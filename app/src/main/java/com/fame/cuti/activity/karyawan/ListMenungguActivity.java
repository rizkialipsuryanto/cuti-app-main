package com.fame.cuti.activity.karyawan;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.R;
import com.fame.cuti.adapter.RiwayatAdapter;
import com.fame.cuti.adapter.StatusAdapter;
import com.fame.cuti.core.Core;
//import com.fame.cuti.databinding.ActivityListBookingBinding;
import com.fame.cuti.databinding.ActivityListMenungguBinding;
import com.fame.cuti.model.ResponseListRiwayatCutiModel;
import com.fame.cuti.model.StatusResponseModel;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMenungguActivity extends Core {

    ActivityListMenungguBinding v;

    String id_status= "";
    StatusResponseModel status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityListMenungguBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());

        v.etStatus.setOnClickListener(x -> getPopUp("status"));
        v.ivBack.setOnClickListener(x -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getData();
        getStatus();
    }

    private void getData() {

//        if (id_status.toString() == ""){
            v.layoutNotFound.setVisibility(View.VISIBLE);
            Map<String, String> query = new HashMap<>();
//        query.put("uid", preferences.getCredential().getData().getId());
            query.put("uid", "1");
            query.put("status", id_status.toString());
            query.put("page", "1");
            query.put("perpage", "100");

            Api.createService(Repo.class)
                    .listRiwayat(query)
                    .enqueue(new Callback<ResponseListRiwayatCutiModel>() {
                        @Override
                        public void onResponse(Call<ResponseListRiwayatCutiModel> call, Response<ResponseListRiwayatCutiModel> response) {
                            v.sweepRefresh.setRefreshing(false);
                            if (response.isSuccessful()) {
                                if(response.body().getCode() == 200) {
                                    ResponseListRiwayatCutiModel responseListRiwayatModel = response.body();
                                    RiwayatAdapter bookingAdapter = new RiwayatAdapter(context, responseListRiwayatModel);
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
//        }
//        else {
//
//        }

    }

//    @SuppressLint("SetTextI18n")
    private void getStatus() {

        v.sweepRefresh.setRefreshing(true);
        Api.createService(context, Repo.class)
                .status()
                .enqueue(new Callback<StatusResponseModel>() {
//                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<StatusResponseModel> call, Response<StatusResponseModel> response) {
                        v.sweepRefresh.setRefreshing(false);
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            if (response.body().getCode() == 200) {
                                status = response.body();
                                Log.d("OBJEK", response.body().getMessage().toString());
                            } else {
                                Log.d("OBJEK", response.body().getCode().toString());
                            }
                        } else {
                            ce.showError(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusResponseModel> call, Throwable t) {
                        v.sweepRefresh.setRefreshing(false);
                        v.layoutNotFound.setVisibility(View.VISIBLE);
                        v.tvError.setText(t.getMessage());
                    }
                });
    }

    private void getPopUp(String tujuan) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        RecyclerView recyclerView = dialog.findViewById(R.id.shimmerList);
        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Pilih Status");
        Log.d(TAG, "getPopUp: " + new Gson().toJson(status));
        StatusAdapter statusAdapter = new StatusAdapter(context, status);
        statusAdapter.setHasStableIds(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(statusAdapter);
        statusAdapter.setOnItemClickListener((view, obj, position) -> {
            dialog.hide();
            if(tujuan.equalsIgnoreCase("status")) {
                id_status = obj.getId_status();
                v.etStatus.setText(obj.getStatus());
                getData();
            }
//            else if(tujuan.equalsIgnoreCase("ke")) {
//                id_ke = obj.getId();
//                v.etKoordinator.setText(obj.getNama());
//            }
            else {
                b.swal_error("terjadi kesalahan");
            }
        });
        dialog.show();
    }
}
