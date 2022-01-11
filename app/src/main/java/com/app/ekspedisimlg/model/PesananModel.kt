package com.app.ekspedisimlg.model

data class PesananModel (
    val status: String,
    val message: String,
    val data: ArrayList<Result>){
    data class Result(
        val nama: String,
        val supir_cadangan: String?,
        val alamat: String,
        val nomor_telpon: String,
        val email: String,
        val api_token: String
    )
}