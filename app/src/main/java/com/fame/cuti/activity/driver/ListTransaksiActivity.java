package com.fame.cuti.activity.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.fame.cuti.R;
import com.fame.cuti.adapter.TransaksiAdapter;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityListTransaksiBinding;
import com.fame.cuti.model.TransaksiResponseModel;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTransaksiActivity extends Core {

    ActivityListTransaksiBinding v;
    TransaksiResponseModel model;
    TransaksiAdapter adapter;
    String status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityListTransaksiBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());

        settingComponent();
    }

    private void settingComponent() {
        v.tvJudul.setText("TRANSAKSI");
        v.sweepRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(0);
            }
        });
        v.btnSort.setOnClickListener(x -> {
            if(v.lyFilter.getVisibility() == View.GONE){
                v.lyFilter.setVisibility(View.VISIBLE);
            }else {
                v.lyFilter.setVisibility(View.GONE);
                getData(0);
            }
        });
        Sprite sprite = new ThreeBounce();
        sprite.setColor(context.getResources().getColor(R.color.colorPrimary));
        v.progressBar.setIndeterminateDrawable(sprite);
        settingShimmer();
        getData(0);

        v.btnCari.setOnClickListener(x -> getData(0));
        v.etTanggal.setOnClickListener(x -> getTanggal());
        v.etStatus.setOnClickListener(x -> getStatus());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData(0);
    }

    private void settingShimmer() {
        model = new TransaksiResponseModel();
        adapter = new TransaksiAdapter(context, model);

        v.shimmer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) v.shimmer.getLayoutManager();
                if (model.getData() != null) {
                    if (layoutManager != null
                            && layoutManager.findLastCompletelyVisibleItemPosition() == (model.getData().getItems().size() - 1)
                            && model.getData().getItems().size() < model.getData().getPaging().getTotal_data()) {
                        Log.e("TAG", "onScrolled: OTW AMBIL DATA");
                        getData(layoutManager.findLastCompletelyVisibleItemPosition());
                    }
                }
            }
        });
    }

    private void getData(int lastItem) {
        int currentPage = 1;
        int nextPage = currentPage;
        if (lastItem == 0) { //BELUM LOADMORE
            v.sweepRefresh.setRefreshing(true);
            v.layoutNotFound.setVisibility(View.VISIBLE);
            v.tvError.setText("Loading...");
            v.layoutShimmer.setVisibility(View.GONE);
        } else {
            v.progressBar.setVisibility(View.VISIBLE);
            currentPage = lastItem / model.getData().getPaging().getPer_page();
            if (lastItem % model.getData().getPaging().getPer_page() != 0) {
                currentPage += 1;
            }
            nextPage = currentPage + 1;
        }

        Map<String, String> query = new HashMap<>();
        query.put("id_driver", preferences.getCredential().getData().getUid());
        query.put("page", String.valueOf(nextPage));
        query.put("perpage", "10");

        if(!v.etStatus.getText().toString().equalsIgnoreCase("SEMUA")) {
            query.put("status_jemput", status);
        }

        if(!v.etTanggal.getText().toString().equalsIgnoreCase("SEMUA")) {
            query.put("tanggal", v.etTanggal.getText().toString());
        }

        Api.createService(context, Repo.class)
                .listTransaksi(query)
                .enqueue(new Callback<TransaksiResponseModel>() {
                    @Override
                    public void onResponse(Call<TransaksiResponseModel> call, Response<TransaksiResponseModel> response) {
                        v.sweepRefresh.setRefreshing(false);
                        v.progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            if (response.body().getCode() == 200) {
                                v.layoutNotFound.setVisibility(View.GONE);
                                v.layoutShimmer.setVisibility(View.VISIBLE);
                                if (model.getData() == null || lastItem == 0) {
                                    model = response.body();
                                    adapter = new TransaksiAdapter(context, model);
                                    adapter.setHasStableIds(true);
                                    LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                                    v.shimmer.setLayoutManager(layoutManager);
                                    v.shimmer.setAdapter(adapter);
                                    if(response.body().getData().getItems().size() < 1) {
                                        v.layoutNotFound.setVisibility(View.VISIBLE);
                                        v.tvError.setText("Data tidak ditemukan");
                                    }else {
                                        v.layoutNotFound.setVisibility(View.GONE);
                                    }
                                } else {
                                    model.getData().getItems().add(null);
                                    adapter.notifyItemInserted(model.getData().getItems().size() - 1);
                                    model.getData().getItems().remove(model.getData().getItems().size() - 1);
                                    adapter.notifyItemRemoved(model.getData().getItems().size());

                                    model.getData().getItems().addAll(response.body().getData().getItems());
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                v.layoutNotFound.setVisibility(View.VISIBLE);
                                v.layoutShimmer.setVisibility(View.GONE);
                                v.tvError.setText(response.body().getMessage());
                            }
                        } else {
                            ce.showError(response.errorBody());
                            v.layoutNotFound.setVisibility(View.VISIBLE);
                            v.layoutShimmer.setVisibility(View.GONE);
                            v.tvError.setText("Terjadi kesalahan");
                        }
                    }

                    @Override
                    public void onFailure(Call<TransaksiResponseModel> call, Throwable t) {
                        v.sweepRefresh.setRefreshing(false);
                        Log.e("TAG", "onFailure: " + t.getMessage());
                        b.swal_error(t.getMessage());
                        v.layoutNotFound.setVisibility(View.VISIBLE);
                        v.layoutShimmer.setVisibility(View.GONE);
                        v.tvError.setText(t.getMessage());
                    }
                });

    }

    private void getTanggal() {
        String[] pilihan = {"SEMUA", "PILIH TANGGAL"};
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Pilih Filter Tanggal")
                .setItems(pilihan, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            v.etTanggal.setText(pilihan[0]);
                            break;
                        case 1:
                            Calendar newCalendar = Calendar.getInstance();

                            DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    Calendar newDate = Calendar.getInstance();
                                    newDate.set(year, monthOfYear, dayOfMonth);
                                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                    v.etTanggal.setText(dateFormatter.format(newDate.getTime()));
                                }

                            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                            datePickerDialog.show();
                            break;
                        default:
                            b.toastLong("Tidak diketahui");
                            break;
                    }
                });
        alert.show();
    }

    private void getStatus() {
        String[] pilihan = {"SEMUA", "MENUNGGU", "DIJEMPUT", "SELESAI"};
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Pilih Filter Tanggal")
                .setItems(pilihan, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            v.etStatus.setText(pilihan[0]);
                            break;
                        case 1:
                            v.etStatus.setText(pilihan[1]);
                            status = "1";
                            break;
                        case 2:
                            v.etStatus.setText(pilihan[2]);
                            status = "2";
                            break;
                        case 3:
                            v.etStatus.setText(pilihan[3]);
                            status = "3";
                            break;
                        default:
                            b.toastLong("Tidak diketahui");
                            break;
                    }
                });
        alert.show();
    }
}