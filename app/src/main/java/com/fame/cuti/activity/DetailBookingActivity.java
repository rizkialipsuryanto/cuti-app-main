package com.fame.cuti.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.fame.cuti.R;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityDetailBookingBinding;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBookingActivity extends Core {

    ActivityDetailBookingBinding v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityDetailBookingBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
        settingComponent();
    }

    private void settingComponent() {
        v.ivBack.setOnClickListener(x -> finish());
//        Bundle bundle = getIntent().getBundleExtra("bundle");
//        ResponseListBookingModel.data.items items = (ResponseListBookingModel.data.items) bundle.getSerializable("data");
        v.tvKodeBooking.setText(getIntent().getStringExtra("nama_emp"));
        v.etNoCuti.setText(getIntent().getStringExtra("no_cuti"));
        v.etNama.setText(getIntent().getStringExtra("nama_emp"));
        v.etTanggalCuti.setText(getIntent().getStringExtra("tglambilcuti"));
        v.etStatusDetail.setText(getIntent().getStringExtra("status"));

        v.etNik.setText(getIntent().getStringExtra("nik"));
        v.etProfesiDetail.setText(getIntent().getStringExtra("profesi"));
        v.etAlamatCuti.setText(getIntent().getStringExtra("alamat"));
        v.etKeteranganCuti.setText(getIntent().getStringExtra("keterangan"));
//        v.btnCancelBooking.setOnClickListener(x -> cancelBooking(getIntent().getStringExtra("kode_booking")));
        v.btnCetak.setOnClickListener(x -> wa());
    }


//    private void cancelBooking(String kode_booking) {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//        LayoutInflater inflater = getLayoutInflater();
//        @SuppressLint("InflateParams")
//        View v2 = inflater.inflate(R.layout.layout_input, null);
//        dialog.setView(v2);
//        dialog.setCancelable(true);
//        dialog.setIcon(R.drawable.travel);
//        dialog.setTitle("Cancel Booking");
//
//        EditText etText = v2.findViewById(R.id.etText);
//
//        dialog.setPositiveButton("Proses", (dialog12, which) ->  {
//            ProgressDialog loading = new ProgressDialog(context);
//            loading.setTitle("Proses Booking");
//            loading.setMessage("Mohon tunggu beberapa saat ...");
//            loading.setCancelable(false);
//            loading.show();
//            Map<String, String> r = new HashMap<>();
//            r.put("id_user", preferences.getCredential().getData().getUid());
//            r.put("kode_booking", kode_booking);
//            r.put("alasan", etText.getText().toString());
//
//            Api.createService(context, Repo.class)
//                    .cancelBooking(r)
//                    .enqueue(new Callback<ResponseBookingModel>() {
//                        @Override
//                        public void onResponse(Call<ResponseBookingModel> call, Response<ResponseBookingModel> response) {
//                            loading.dismiss();
//                            if (response.isSuccessful()) {;
//                                if (response.body().getCode() == 200) {
//                                    b.swal_sukses(response.body().getMessage());
//                                }else {
//                                    b.swal_error(response.body().getMessage());
//                                }
//                            } else {
//                                ce.showError(response.errorBody());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBookingModel> call, Throwable t) {
//                            loading.dismiss();
//                            b.swal_warning(t.getMessage());
//                        }
//                    });
//
//        });
//        dialog.setNegativeButton("Batal", (dialog1, which) -> dialog1.dismiss());
//        dialog.show();
//    }

    private void wa(){
        try {
            String headerReceiver = "";// Replace with your message.
            String bodyMessageFormal = "";// Replace with your message.
            String whatsappContain = headerReceiver + bodyMessageFormal;
            String trimToNumner = "+6285640769886"; //10 digit number
            Intent intent = new Intent ( Intent.ACTION_VIEW );
            intent.setData ( Uri.parse ( "http://103.180.59.149:8080/admin_cuti/cetak/lembarcuti/" + v.etNoCuti.getText().toString() +"" ) );
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}