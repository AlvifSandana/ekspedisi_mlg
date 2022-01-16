package com.app.ekspedisimlg.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ApiService
 *
 * Object retrofit untuk API service.
 * Panggil object ini untuk setiap koneksi menuju API.
 */
object ApiService {
    // url API, bisa pakai http://192.168.0.6/ekspedisi_web/public/api/
    // tanpa perlu run "php artisan server --host=alamat_ip"
    val BASE_URL: String = "http://192.168.0.6/ekspedisi_web/public/api/"
    val endpoint: ApiEndpoint
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiEndpoint::class.java)
        }
}