package com.fame.cuti.activity.karyawan;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fame.cuti.R;
import com.fame.cuti.adapter.AtasanlangsungAdapter;
import com.fame.cuti.adapter.JeniscutiAdapter;
import com.fame.cuti.adapter.KepalainstalasiAdapter;
import com.fame.cuti.adapter.KoordinatorAdapter;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityBookingSekarangBinding;
import com.fame.cuti.model.AtasanlangsungResponseModel;
import com.fame.cuti.model.JeniscutiResponseModel;
import com.fame.cuti.model.KepalainstalasiResponseModel;
import com.fame.cuti.model.KoordinatorResponseModel;
import com.fame.cuti.model.ResponseUniversalModel;
import com.fame.cuti.model.StatusResponseModel;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingSekarangActivity extends Core {

    ActivityBookingSekarangBinding v;

    StatusResponseModel status;
    JeniscutiResponseModel jeniscuti;
    KoordinatorResponseModel koordinatorcuti;
    KepalainstalasiResponseModel kepalainstalasicuti;
    AtasanlangsungResponseModel atasanlangsungcuti;

    private List<String> selectedDates;
    private Calendar calendar;
    private TextView selectedDatesTextView;
    String id_jenis= "", id_koordinator="", id_kepalainstalasi="", id_atasanlangsung="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityBookingSekarangBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
        selectedDates = new ArrayList<>();
        calendar = Calendar.getInstance();
        selectedDatesTextView = findViewById(R.id.selectedDatesTextView);
        settingComponent();
    }

    private void settingComponent() {
//        v.tvNama.setText("Halo, "+preferences.getCredential().getData().getNama_emp());
        v.ivBack.setOnClickListener(x -> finish());
        v.sweepRefresh.setOnRefreshListener(this::getJenis);
        getJenis();
//        v.sweepRefresh.setOnRefreshListener(this::getKoordinator);
//        getKoordinator();
//        v.sweepRefresh.setOnRefreshListener(this::getKepalainstalasi);
//        getKepalainstalasi();
//        v.sweepRefresh.setOnRefreshListener(this::getAtasanlangsung);
//        getAtasanlangsung();

        v.etJeniscuti.setOnClickListener(x -> getPopUp("jeniscuti"));
//        v.etKoordinator.setOnClickListener(x -> getPopUpKoordinator("koordinatorcuti"));
//        v.etKepalaInstalasi.setOnClickListener(x -> getPopUpKepalainstalasi("kepalainstalasicuti"));
//        v.etAtasanLangsung.setOnClickListener(x -> getPopUpAtasanlangsung("atasanlangsungcuti"));
        v.etTanggalberangkat.setOnClickListener(x -> pilihTanggal());
        v.selectDateButton.setOnClickListener(x -> showDatePickerDialog());
        v.showSelectedDatesButton.setOnClickListener(x -> showSelectedDates());
        v.btnInsertCuti.setOnClickListener(x -> prosesDaftar());
//        v.btnProses.setOnClickListener(x -> {
//        });
    }

    private void prosesDaftar() {
//        if(id_jenis.isEmpty()){
//            v.etJeniscuti.setError("Silahkan pilih Jenis Cuti");
//            v.etJeniscuti.requestFocus();
//            return;
//        }
//        if(v.etTanggalberangkat.getText().toString().isEmpty()){
//            v.etTanggalberangkat.setError("Silahkan pilih tanggal cuti");
//            v.etTanggalberangkat.requestFocus();
//            return;
//        }
//        else{
            ProgressDialog loading = new ProgressDialog(context);
            loading.setTitle("Proses Pengajuan Cuti");
            loading.setMessage("Mohon tunggu beberapa saat ...");
            loading.setCancelable(false);
            loading.show();

            Map<String, String> r = new HashMap<>();
            r.put("uid", preferences.getCredential().getData().getUid().toString());
            r.put("jeniscuti", id_jenis.toString());
            r.put("tglambilcuti", v.selectedDatesTextView.getText().toString());
            r.put("keterangan", v.etKeteranganCuti.getText().toString());
            r.put("alamatcuti", v.etAlamat.getText().toString());

            Log.e(TAG, "prosesDaftar: " + new Gson().toJson(r));

            Api.createService(context, Repo.class)
                    .insertcuti(r)
                    .enqueue(new Callback<ResponseUniversalModel>() {
                        @Override
                        public void onResponse(Call<ResponseUniversalModel> call, Response<ResponseUniversalModel> response) {
                            loading.dismiss();
                            if (response.isSuccessful()) {
                                b.toastLong(response.body().getMessage());
                                if (response.body().getCode() == 200) {
                                    finish();
                                }
                            } else {
                                ce.showError(response.errorBody());
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseUniversalModel> call, Throwable t) {
                            loading.dismiss();
                            b.swal_warning(t.getMessage());
                        }
                    });
//        }
    }

    private void getJenis() {
        v.layoutNotFound.setVisibility(View.VISIBLE);
        v.formBooking.setVisibility(View.GONE);
        v.tvError.setText("Loading...");

        v.sweepRefresh.setRefreshing(true);
        Api.createService(context, Repo.class)
                .jeniscuti()
                .enqueue(new Callback<JeniscutiResponseModel>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<JeniscutiResponseModel> call, Response<JeniscutiResponseModel> response) {
                        v.sweepRefresh.setRefreshing(false);
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            if (response.body().getCode() == 200) {
                                jeniscuti = response.body();
                                v.layoutNotFound.setVisibility(View.GONE);
                                v.formBooking.setVisibility(View.VISIBLE);
                                v.tvError.setText("Loading...");
                            } else {
                                b.swal_error(response.body().getMessage());
                                v.tvError.setText(response.body().getMessage());
                            }
                        } else {
                            ce.showError(response.errorBody());
                            v.tvError.setText("Terjadi kesalahan");
                        }
                    }

                    @Override
                    public void onFailure(Call<JeniscutiResponseModel> call, Throwable t) {
                        v.sweepRefresh.setRefreshing(false);
                        v.layoutNotFound.setVisibility(View.VISIBLE);
                        v.tvError.setText(t.getMessage());
                    }
                });
    }
//    private void getKoordinator() {
//        v.layoutNotFound.setVisibility(View.VISIBLE);
//        v.formBooking.setVisibility(View.GONE);
//        v.tvError.setText("Loading...");
//
//        v.sweepRefresh.setRefreshing(true);
//        Api.createService(context, Repo.class)
//                .koordinatorcuti()
//                .enqueue(new Callback<KoordinatorResponseModel>() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onResponse(Call<KoordinatorResponseModel> call, Response<KoordinatorResponseModel> response) {
//                        v.sweepRefresh.setRefreshing(false);
//                        if (response.isSuccessful()) {
//                            assert response.body() != null;
//                            if (response.body().getCode() == 200) {
//                                koordinatorcuti = response.body();
//                                v.layoutNotFound.setVisibility(View.GONE);
//                                v.formBooking.setVisibility(View.VISIBLE);
//                                v.tvError.setText("Loading...");
//                            } else {
//                                b.swal_error(response.body().getMessage());
//                                v.tvError.setText(response.body().getMessage());
//                            }
//                        } else {
//                            ce.showError(response.errorBody());
//                            v.tvError.setText("Terjadi kesalahan");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<KoordinatorResponseModel> call, Throwable t) {
//                        v.sweepRefresh.setRefreshing(false);
//                        v.layoutNotFound.setVisibility(View.VISIBLE);
//                        v.tvError.setText(t.getMessage());
//                    }
//                });
//    }
//    private void getKepalainstalasi() {
//        v.layoutNotFound.setVisibility(View.VISIBLE);
//        v.formBooking.setVisibility(View.GONE);
//        v.tvError.setText("Loading...");
//
//        v.sweepRefresh.setRefreshing(true);
//        Api.createService(context, Repo.class)
//                .kepalainstalasicuti()
//                .enqueue(new Callback<KepalainstalasiResponseModel>() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onResponse(Call<KepalainstalasiResponseModel> call, Response<KepalainstalasiResponseModel> response) {
//                        v.sweepRefresh.setRefreshing(false);
//                        if (response.isSuccessful()) {
//                            assert response.body() != null;
//                            if (response.body().getCode() == 200) {
//                                kepalainstalasicuti = response.body();
//                                v.layoutNotFound.setVisibility(View.GONE);
//                                v.formBooking.setVisibility(View.VISIBLE);
//                                v.tvError.setText("Loading...");
//                            } else {
//                                b.swal_error(response.body().getMessage());
//                                v.tvError.setText(response.body().getMessage());
//                            }
//                        } else {
//                            ce.showError(response.errorBody());
//                            v.tvError.setText("Terjadi kesalahan");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<KepalainstalasiResponseModel> call, Throwable t) {
//                        v.sweepRefresh.setRefreshing(false);
//                        v.layoutNotFound.setVisibility(View.VISIBLE);
//                        v.tvError.setText(t.getMessage());
//                    }
//                });
//    }
//    private void getAtasanlangsung() {
//        v.layoutNotFound.setVisibility(View.VISIBLE);
//        v.formBooking.setVisibility(View.GONE);
//        v.tvError.setText("Loading...");
//
//        v.sweepRefresh.setRefreshing(true);
//        Api.createService(context, Repo.class)
//                .atasanlangsungcuti()
//                .enqueue(new Callback<AtasanlangsungResponseModel>() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onResponse(Call<AtasanlangsungResponseModel> call, Response<AtasanlangsungResponseModel> response) {
//                        v.sweepRefresh.setRefreshing(false);
//                        if (response.isSuccessful()) {
//                            assert response.body() != null;
//                            if (response.body().getCode() == 200) {
//                                atasanlangsungcuti = response.body();
//                                v.layoutNotFound.setVisibility(View.GONE);
//                                v.formBooking.setVisibility(View.VISIBLE);
//                                v.tvError.setText("Loading...");
//                            } else {
//                                b.swal_error(response.body().getMessage());
//                                v.tvError.setText(response.body().getMessage());
//                            }
//                        } else {
//                            ce.showError(response.errorBody());
//                            v.tvError.setText("Terjadi kesalahan");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AtasanlangsungResponseModel> call, Throwable t) {
//                        v.sweepRefresh.setRefreshing(false);
//                        v.layoutNotFound.setVisibility(View.VISIBLE);
//                        v.tvError.setText(t.getMessage());
//                    }
//                });
//    }
    private void getPopUp(String jenis) {
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
        dialogTitle.setText("Pilih Jenis Cuti");
        Log.d(TAG, "getPopUp: " + new Gson().toJson(jeniscuti));
        JeniscutiAdapter jeniscutiAdapter = new JeniscutiAdapter(context, jeniscuti);
        jeniscutiAdapter.setHasStableIds(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(jeniscutiAdapter);
        jeniscutiAdapter.setOnItemClickListener((view, obj, position) -> {
            dialog.hide();
            if(jenis.equalsIgnoreCase("jeniscuti")) {
                id_jenis = obj.getId_jenis_cuti();
                v.etJeniscuti.setText(obj.getJenis_cuti());
            }
            else {
                b.swal_error("terjadi kesalahan");
            }
        });
        dialog.show();
    }
//    private void getPopUpKoordinator(String koordinator) {
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
//        dialog.setContentView(R.layout.dialog_layout);
//        dialog.setCancelable(true);
//
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        RecyclerView recyclerView = dialog.findViewById(R.id.shimmerList);
//        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
//        dialogTitle.setText("Pilih Koordinator");
//        Log.d(TAG, "getPopUp: " + new Gson().toJson(koordinatorcuti));
//        KoordinatorAdapter koordinatorAdapter = new KoordinatorAdapter(context, koordinatorcuti);
//        koordinatorAdapter.setHasStableIds(false);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(koordinatorAdapter);
//        koordinatorAdapter.setOnItemClickListener((view, obj, position) -> {
//            dialog.hide();
//            if(koordinator.equalsIgnoreCase("koordinatorcuti")) {
//                id_koordinator = obj.getUid();
//                v.etKoordinator.setText(obj.getNama());
//            }
//            else {
//                b.swal_error("terjadi kesalahan");
//            }
//        });
//        dialog.show();
//    }
//    private void getPopUpKepalainstalasi(String kepalainstalasi) {
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
//        dialog.setContentView(R.layout.dialog_layout);
//        dialog.setCancelable(true);
//
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        RecyclerView recyclerView = dialog.findViewById(R.id.shimmerList);
//        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
//        dialogTitle.setText("Pilih Kepala Instalasi");
//        Log.d(TAG, "getPopUp: " + new Gson().toJson(kepalainstalasicuti));
//        KepalainstalasiAdapter KepalainstalasiAdapter = new KepalainstalasiAdapter(context, kepalainstalasicuti);
//        KepalainstalasiAdapter.setHasStableIds(false);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(KepalainstalasiAdapter);
//        KepalainstalasiAdapter.setOnItemClickListener((view, obj, position) -> {
//            dialog.hide();
//            if(kepalainstalasi.equalsIgnoreCase("kepalainstalasicuti")) {
//                id_kepalainstalasi = obj.getUid();
//                v.etKepalaInstalasi.setText(obj.getNama());
//            }
//            else {
//                b.swal_error("terjadi kesalahan");
//            }
//        });
//        dialog.show();
//    }
//    private void getPopUpAtasanlangsung(String atasanlangsung) {
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
//        dialog.setContentView(R.layout.dialog_layout);
//        dialog.setCancelable(true);
//
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        RecyclerView recyclerView = dialog.findViewById(R.id.shimmerList);
//        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
//        dialogTitle.setText("Pilih");
//        Log.d(TAG, "getPopUp: " + new Gson().toJson(atasanlangsungcuti));
//        AtasanlangsungAdapter AtasanlangsungAdapter = new AtasanlangsungAdapter(context, atasanlangsungcuti);
//        AtasanlangsungAdapter.setHasStableIds(false);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(AtasanlangsungAdapter);
//        AtasanlangsungAdapter.setOnItemClickListener((view, obj, position) -> {
//            dialog.hide();
//            if(atasanlangsung.equalsIgnoreCase("atasanlangsungcuti")) {
//                id_atasanlangsung = obj.getUid();
//                v.etAtasanLangsung.setText(obj.getNama());
//            }
//            else {
//                b.swal_error("terjadi kesalahan");
//            }
//        });
//        dialog.show();
//    }
    private void pilihTanggal(){
        Calendar newCalendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                v.etTanggalberangkat.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        int displayBulan = month + 1;
                        String formattedMonth = String.format("%02d", displayBulan);

                        String selectedDate = year + "-" + formattedMonth + "-" + dayOfMonth;
                        selectedDates.add(selectedDate);
                        Log.d(TAG, selectedDate);
                        Log.d(TAG, selectedDates.toString());
                        showSelectedDates();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showSelectedDates() {
        StringBuilder datesString = new StringBuilder();
        for (String date : selectedDates) {
            datesString.append(date).append(",");
        }

        if (datesString.length() > 0) {
            datesString.setLength(datesString.length() - 1);
        }

        selectedDatesTextView.setText(datesString.toString());
    }
}