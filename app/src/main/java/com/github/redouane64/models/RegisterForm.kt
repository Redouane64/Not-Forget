package com.github.redouane64.models

import com.google.gson.annotations.SerializedName

class RegisterForm(val name: String,
                   val email: String,
                   val password: String,
                   @SerializedName("api_token")val token: String?) {
}