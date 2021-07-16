package com.app.ekspedisimlg.model

data class LoginResponseModel(
    val status: String,
    val message: String,
    val data: List<Data>
) {
    data class Data(
        val nama: String,
        val alamat: String,
        val nomor_telpon: String,
        val email: String,
        val api_token: String
    )
}
