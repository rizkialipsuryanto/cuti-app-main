package com.fame.cuti.helper;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.fame.cuti.R;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import naturalizer.separator.Separator;

public class Bantuan {
    private Context context;
    public Bantuan(Context context) {
        this.context = context;
    }

    public void toastShort(String pesan) {
        Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show();
    }

    public void toastLong(String pesan) {
        Toast.makeText(context, pesan, Toast.LENGTH_LONG).show();
    }

    public boolean isEmailnyaBenar(String email) {
        Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                        Pattern.CASE_INSENSITIVE)
                .matcher(email);
        return matcher.find();
    }

    public String formatHarga(String s) {
        return Separator.getInstance().doSeparate(s, Locale.GERMANY);
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public void swal_error(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Gagal")
                .setContentText(pesan)
                .show();
    }

    public void swal_error_finish(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Gagal")
                .setContentText(pesan)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity) context).finish();
                    }
                })
                .show();
    }

    public void swal_warning(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Peringatan")
                .setContentText(pesan)
                .show();
    }

    public void swal_information(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Informasi")
                .setContentText(pesan+"\n")
                .show();
    }

    public void swal_sukses(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Sukses")
                .setContentText(pesan)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity) context).finish();
                    }
                })
                .show();
    }

    public void swal_sukses_nofinish(String pesan) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Sukses")
                .setContentText(pesan)
                .show();
    }

    public SweetAlertDialog swal_loading(String pesan) {
        SweetAlertDialog SAD = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        SAD.getProgressHelper().setBarColor(Color.parseColor("#ef233c"));
        SAD.setTitleText("Loading...");
        SAD.setContentText(pesan);
        SAD.setCancelable(false);

        return SAD;
    }

    public void kirim_wa(String nomor, String text){
        String coba = nomor.substring(0,1);
        if (coba.equalsIgnoreCase("0")){
            String substr = nomor.substring(1);
            String url = "https://wa.me/+62" + substr + "?text=" + text;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }else if (coba.equalsIgnoreCase("+")) {
            String url = "https://wa.me/" + nomor + "?text=" + text;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }else {
            swal_error("Foramt nomor telepon tidak valid");
        }
    }

    public static String queryName(ContentResolver resolver, Uri uri) {
        Cursor returnCursor =
                resolver.query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

    public void cropImage(Activity activity, Uri sourceUri, int IMAGE_COMPRESSION) {
        Uri destinationUri = Uri.fromFile(new File(context.getCacheDir(), queryName(context.getContentResolver(), sourceUri)));
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(IMAGE_COMPRESSION);
        options.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setActiveControlsWidgetColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setFreeStyleCropEnabled(true);
//        if (lockAspectRatio)
//        options.withAspectRatio(1, 1);

//        if (setBitmapMaxWidthHeight)
//            options.withMaxResultSize(bitmapMaxWidth, bitmapMaxHeight);

        UCrop.of(sourceUri, destinationUri)
                .withOptions(options)
                .start(activity);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, System.currentTimeMillis() + ".jpg", null);
        return Uri.parse(path);
    }
}
