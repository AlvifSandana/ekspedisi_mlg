package com.app.ekspedisimlg.model


data class RegisterPengirimModel(
    val nama_pengirim: String,
    val alamat_pengirim: String,
    val nomor_telpon: String,
    val email: String,
    val password: String
)
