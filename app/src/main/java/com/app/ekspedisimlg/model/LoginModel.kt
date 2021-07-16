package com.app.ekspedisimlg.model

import retrofit2.http.Field

data class LoginModel(
    @Field("email") val email: String,
    @Field("password") val password: String
    )
