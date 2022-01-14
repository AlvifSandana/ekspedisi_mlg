package com.app.ekspedisimlg.model

data class LoginResponseModel(
    val status: String,
    val message: String,
    val data: List<Data>
) {
    data class Data(
        val idUser: String,
        val nama: String,
        val supir_cadangan: String?,
        val alamat: String,
        val nomor_telpon: String,
        val email: String,
        val api_token: String
    )
}
