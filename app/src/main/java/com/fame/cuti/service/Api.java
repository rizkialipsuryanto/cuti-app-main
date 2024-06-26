package com.fame.cuti.service;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Api {
    //ini di kopi saja

    //set default base url
//    public static String BASE_URL_API = "http://203.194.112.45:90/api/v1/";
//    public static String BASE_URL_API = "http://10.0.2.2/travel-api-main/api/v1/";
//    public static String BASE_URL_API = "http://192.168.20.144/cuti-api-main/app/api/v1/";
//    public static String BASE_URL_API = "http://192.168.20.114/cuti-api/v1/api/";
    public static String BASE_URL_API = "http://192.168.20.62/cuti-api-skripsi/api/V1/";
    public static final String DEFAULT_USERNAME = "admin";
    public static final String DEFAULT_PASSWORD = "1234";

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static Gson gson=new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit.Builder builder =  new Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson));

    private  static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private  static  Retrofit retrofit = builder.build();

    public static <S> S createService(Context context, Class<S> serviceClass) {
        return createService(context, serviceClass, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    private static <S> S createService(Context context, Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(context, serviceClass, authToken);
        }
        return createService(context, serviceClass, null);
    }

    private static <S> S createService(Context context, Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(context, authToken);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }

    //TODO : GA PAKE CONTEXT
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    private static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }
        return createService(serviceClass, null);
    }

    private static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }
}

class AuthenticationInterceptor implements Interceptor {
    private Context context;
    private String authToken;

    AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    AuthenticationInterceptor(Context context, String authToken) {
        this.context = context;
        this.authToken = authToken;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken);
//                .header(App.NAME_API_KEY, App.DEFAULT_API_KEY);
        Request request = builder.build();
        return chain.proceed(request);
    }
}
