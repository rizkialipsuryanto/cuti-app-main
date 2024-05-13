package com.fame.cuti.activity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import androidx.appcompat.app.AlertDialog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.fame.cuti.activity.driver.MainDriverActivity;
import com.fame.cuti.core.Core;
import com.fame.cuti.databinding.ActivitySplashScreenBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Core {

    ActivitySplashScreenBinding v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(v.getRoot());
        settingComponent();
    }

    private void settingComponent() {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
            v.tvVersiAplikasi.setText("v" + pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cekPermission();
    }

    private void cekPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(final MultiplePermissionsReport report) {
                        if (!report.areAllPermissionsGranted()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                                    .setMessage("Izinkan Aplikasi untuk mengakses permission ?")
                                    .setCancelable(false)
                                    .setNegativeButton("Keluar", (dialogInterface, i) -> finish())
                                    .setPositiveButton("Silahkan", (dialogInterface, i) -> {
                                        if (report.isAnyPermissionPermanentlyDenied()) {
                                            Intent intent = new Intent();
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            intent.setData(uri);
                                            startActivity(intent);
                                        } else {
                                            cekPermission();
                                        }
                                    });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        } else {
                            final Timer timer = new Timer();
                            int DELAY = 3000;
                            timer.schedule(new Splash(), DELAY);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    public class Splash extends TimerTask {
        @Override
        public void run() {
//            if (preferences.getCredential() == null) {
                startActivity(new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            } else {
//                if(preferences.getCredential().getData().getLevel().equalsIgnoreCase("USER")) {
//                    startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP | FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
//                }else if(preferences.getCredential().getData().getLevel().equalsIgnoreCase("DRIVER")){
//                    startActivity(new Intent(context, MainDriverActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP | FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
//                }
//            }
            finish();
        }
    }
}