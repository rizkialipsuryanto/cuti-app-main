package com.fame.cuti.helper;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.fame.cuti.activity.SplashScreenActivity;
import com.fame.cuti.model.ResponseLoginModel;
import com.google.gson.Gson;

public class Preferences {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Context context;
    private final Bantuan b;

    private static final String CREDENTIAL_NAME = "credential";
    private static final String PREFERENCE_NAME = "nama";

    @SuppressLint("CommitPrefEdits")
    public Preferences(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        b = new Bantuan(context);
    }

    public void setCredential(ResponseLoginModel credential) {
        editor.remove(CREDENTIAL_NAME).commit();
        editor.putString(CREDENTIAL_NAME, new Gson().toJson(credential));
        editor.commit();
    }

    public ResponseLoginModel getCredential() {
        return new Gson().fromJson(sharedPreferences.getString(CREDENTIAL_NAME, null), ResponseLoginModel.class);
    }

    public void removeCredentialAndActivedUserData() {
        editor.remove(CREDENTIAL_NAME).commit();
    }

    public void logout() {
        removeCredentialAndActivedUserData();
        context.startActivity(new Intent(context, SplashScreenActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP | FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        ((Activity) context).finish();
    }
}
