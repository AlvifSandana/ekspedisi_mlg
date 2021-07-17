package com.app.ekspedisimlg.model

data class RegisterSupirResponseModel(
    val status: String,
    val message: String,
    val data: List<Data>
) {
    data class Data(
        val nama: String,
        val supir_cadang: String,
        val alamat: String,
        val nomor_telpon: String,
        val email: String
    )
}
