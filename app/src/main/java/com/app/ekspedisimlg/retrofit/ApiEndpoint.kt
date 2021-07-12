package com.app.ekspedisimlg.retrofit

import com.app.ekspedisimlg.model.ListTarifModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("tarif")
    fun getListTarif(): Call<ListTarifModel>
}