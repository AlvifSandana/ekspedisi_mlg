package com.app.ekspedisimlg.model

data class ListTarifModel(
    val status: String,
    val message: String,
    val data: ArrayList<Result>
) {
    data class Result(
        val idTarif: Int,
        val titik_pengiriman: String,
        val tujuan_pengiriman: String,
        val tarif: Int,
        val status: String,
        val keterangan: String
    )
}
