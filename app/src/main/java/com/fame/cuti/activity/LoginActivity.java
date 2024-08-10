package com.fame.cuti.activity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fame.cuti.R;
import com.fame.cuti.activity.driver.MainDriverActivity;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivityLoginBinding;
import com.fame.cuti.model.ResponseLoginModel;
import com.fame.cuti.service.Api;
import com.fame.cuti.service.Repo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Core {

    ActivityLoginBinding v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
        settingComponent();
    }

    private void settingComponent() {
        v.btnMasuk.setOnClickListener(x -> prosesMasuk());
//        v.tvDaftarDisini.setOnClickListener(x -> startActivity(new Intent(context, RegisterActivity.class)));
    }

    private void prosesMasuk() {
        ProgressDialog loading = new ProgressDialog(context);
        loading.setTitle("Proses Login");
        loading.setMessage("Mohon tunggu beberapa saat ...");
        loading.setCancelable(false);
        loading.show();

        Map<String, String> r = new HashMap<>();
        r.put("username", v.etUsername.getText().toString());
        r.put("password", v.etPassword.getText().toString());

        Log.e(TAG, "prosesMasuk: " + new Gson().toJson(r));
        Api.createService(context, Repo.class)
                .login(r)
                .enqueue(new Callback<ResponseLoginModel>() {
                    @Override
                    public void onResponse(Call<ResponseLoginModel> call, Response<ResponseLoginModel> response) {
                        loading.dismiss();
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            b.toastLong(response.body().getMessage());
                            if (response.body().getCode() == 200) {
                                preferences.setCredential(response.body());
                                    startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP | FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

                            }
                        } else {
                            ce.showError(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLoginModel> call, Throwable t) {
                        loading.dismiss();
                        b.swal_warning(t.getMessage());
                    }
                });
    }

}