package com.app.ekspedisimlg.model

data class PesananResponseModel (
    val status: String,
    val message: String,
    val data: List<Data>) {
    data class Data(
        val nama: String,
        val supir_cadangan: String?,
        val alamat: String,
        val nomor_telpon: String,
        val email: String,
        val api_token: String
    )
}