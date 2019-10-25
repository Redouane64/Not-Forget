package com.github.redouane64.services

import com.github.redouane64.models.ApiToken
import com.github.redouane64.models.LoginCredentials
import retrofit2.Call
import retrofit2.http.POST

interface AccountService {
    @POST("/login")
    fun login(credentials: LoginCredentials): Call<ApiToken>
}