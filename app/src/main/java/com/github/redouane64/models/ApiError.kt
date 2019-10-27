package com.github.redouane64.models

import com.google.gson.annotations.SerializedName

data class ApiError(@SerializedName("message")val message: String);