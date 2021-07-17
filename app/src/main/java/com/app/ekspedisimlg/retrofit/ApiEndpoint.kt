package com.app.ekspedisimlg.retrofit

import com.app.ekspedisimlg.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @GET("tarif")
    fun getListTarif(
        @Header("api_token") api_token: String
    ): Call<ListTarifModel>

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
    fun regPengirim(regdata: List<RegisterPengirimModel>): Call<RegisterResponseModel>

    @FormUrlEncoded
    @POST("auth/reg/supir")
    fun regSupir(regdata: List<RegisterSupirModel>): Call<RegisterSupirResponseModel>
}