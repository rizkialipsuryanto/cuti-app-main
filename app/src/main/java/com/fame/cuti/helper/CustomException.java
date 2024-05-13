package com.fame.cuti.helper;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.ResponseBody;

public class CustomException {
    private Context context;

    public CustomException(Context context) {
        this.context = context;
    }

    public void showError(ResponseBody responseBody) {
        try {
            assert responseBody != null;
            JSONObject object = new JSONObject(responseBody.string());
            new Bantuan(context).toastLong("Error : " + object.getString("response_message"));
//            Log.d(TAG, "Error : " + object.getString("response_message"));
        } catch (Exception e) {
            new Bantuan(context).toastLong("Terjadi Kesalahan, Silahkan Coba Lagi");
//            new Bantuan(context).toastLong(e.getMessage());
            Log.e("CustomException", "showError: " + e.getMessage());
        }
    }
}
