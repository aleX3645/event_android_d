package com.alex3645.event_d.model

import retrofit2.http.Field

data class LoginRequestBody(val login: String, val password_hash: String)
