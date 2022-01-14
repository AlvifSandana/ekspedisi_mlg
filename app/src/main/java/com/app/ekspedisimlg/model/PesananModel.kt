package com.app.ekspedisimlg.model

data class PesananModel (
    val status: String,
    val message: String,
    val data: ArrayList<Result>){
    data class Result(
        val berat_muatan: String,
        val jenis_muatan: String?,
        val tanggal_muat: String,
        val lokasi_tujuan: String,
    )
}