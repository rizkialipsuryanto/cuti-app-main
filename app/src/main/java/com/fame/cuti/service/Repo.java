package com.fame.cuti.service;

import com.fame.cuti.model.AtasanlangsungResponseModel;
import com.fame.cuti.model.JeniscutiResponseModel;
import com.fame.cuti.model.KepalainstalasiResponseModel;
import com.fame.cuti.model.KoordinatorResponseModel;
import com.fame.cuti.model.ResponseListRiwayatCutiModel;
import com.fame.cuti.model.ResponseLoginModel;
import com.fame.cuti.model.StatusResponseModel;
import com.fame.cuti.model.TransaksiResponseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Repo {
//    @POST("akun/auth/login")
    @POST("auth/login")
    @FormUrlEncoded
    Call<ResponseLoginModel> login(@FieldMap Map<String, String> field);

//    @POST("akun/auth/register")
//    @FormUrlEncoded
//    Call<ResponseUniversalModel> register(@FieldMap Map<String, String> field);
//
//    @POST("transaksi/booking")
//    @FormUrlEncoded
//    Call<ResponseBookingModel> booking(@FieldMap Map<String, String> field);
//
//    @POST("transaksi/booking/cancel")
//    @FormUrlEncoded
//    Call<ResponseBookingModel> cancelBooking(@FieldMap Map<String, String> field);

    @POST("driver/transaksi/jemput")
    @FormUrlEncoded
    Call<TransaksiResponseModel> prosesJemput(@FieldMap Map<String, String> field);

    @POST("driver/transaksi/selesai")
    @FormUrlEncoded
    Call<TransaksiResponseModel> prosesSelesai(@FieldMap Map<String, String> field);

//    @GET("transaksi/jadwal/kota")
//    Call<KotaResponseModel> kota();

    @GET("cuti/statuscuti")
    Call<StatusResponseModel> status();

    @GET("cuti/jeniscuti")
    Call<JeniscutiResponseModel> jeniscuti();

    @GET("cuti/koordinator")
    Call<KoordinatorResponseModel> koordinatorcuti();

    @GET("cuti/kepalainstalasi")
    Call<KepalainstalasiResponseModel> kepalainstalasicuti();
    @GET("cuti/atasanlangsung")
    Call<AtasanlangsungResponseModel> atasanlangsungcuti();

//    @GET("transaksi/jadwal/cari")
//    Call<ResponseCariJadwalModel> jadwal(@QueryMap Map<String, String> query);
//
//    @GET("transaksi/booking/list")
//    Call<ResponseListBookingModel> listBooking(@QueryMap Map<String, String> query);

    @GET("driver/transaksi/list")
    Call<TransaksiResponseModel> listTransaksi(@QueryMap Map<String, String> query);

    @GET("cuti/list")
    Call<ResponseListRiwayatCutiModel> listRiwayat(@QueryMap Map<String, String> query);
}
