package com.app.ekspedisimlg.retrofit

import com.app.ekspedisimlg.model.ListTarifModel
import com.app.ekspedisimlg.model.LoginModel
import com.app.ekspedisimlg.model.LoginResponseModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @GET("tarif")
    fun getListTarif(
        @Header("api_token") api_token: String
    ): Call<ListTarifModel>

    @FormUrlEncoded
    @POST("auth/login/pengirim")
    fun loginPengirim(logindata: List<LoginModel>): Call<LoginResponseModel>

    @FormUrlEncoded
    @POST("auth/login/supir")
    fun loginSupir(logindata: List<LoginModel>): Call<LoginResponseModel>
}