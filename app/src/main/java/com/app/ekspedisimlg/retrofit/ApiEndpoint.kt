package com.app.ekspedisimlg.retrofit

import com.app.ekspedisimlg.model.*
import retrofit2.Call
import retrofit2.http.*

/**
 * API Endpoints
 *
 * seluruh endpoint yang digunakan oleh app ini dan
 * disediakan oleh web admin pengiriman.
 */
interface ApiEndpoint {
    @GET("tarif")
    fun getListTarif(
        @Header("api-token") api_token: String,
        @Header("role") role: String
    ): Call<ListTarifModel>

    @GET("transaksi")
    fun getPesanan(
        @Header("api-token") api_token: String,
        @Header("role") role: String
    ): Call<PesananModel>

    @POST("transaksi/create")
    fun createPesanan(
        @Field("jenis_muatan") jenis_muatan: String,
        @Field("berat_muatan") berat_muatan: String,
        @Field("tanggal_muat") tanggal: String,
        @Field("lokasi_kirim") lokasi_kirim: String,
        @Field("catatan") catatan: String,
    ): Call<PesananResponseModel>

    @GET("muatan")
    fun getMuatan(
        @Header("api-token") api_token: String,
        @Header("role") role: String
    ): Call<MuatanResponseModel>

    @FormUrlEncoded
    @POST("auth/login/pengirim")
    fun loginPengirim(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponseModel>

    @FormUrlEncoded
    @POST("auth/login/supir")
    fun loginSupir(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponseModel>

    @FormUrlEncoded
    @POST("auth/login/all")
    fun loginAll(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponseModel>

    @FormUrlEncoded
    @POST("auth/reg/pengirim")
    fun regPengirim(
        @Field("nama_pengirim") nama_pengirim: String,
        @Field("alamat_pengirim") alamat_pengirim: String,
        @Field("nomor_telpon") nomor_telpon: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponseModel>

    @FormUrlEncoded
    @POST("auth/reg/supir")
    fun regSupir(
        @Field("nama_supir") nama_supir: String,
        @Field("nama_supircadang") nama_supircadang: String,
        @Field("alamat_supir") alamat_supir: String,
        @Field("nomor_telpon") nomor_telpon: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterSupirResponseModel>
}