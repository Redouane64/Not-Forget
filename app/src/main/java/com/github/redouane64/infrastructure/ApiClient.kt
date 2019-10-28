package com.github.redouane64.infrastructure

import com.github.redouane64.models.ApiToken
import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.models.Task
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {
    @POST("login")
    fun login(@Body credentials: LoginCredentials): Call<ApiToken>

    @GET("tasks")
    fun getTasks(): Call<List<Task>>;

    @POST("tasks")
    fun createTask(@Body task: Task): Call<Void>;

    @DELETE("tasks")
    fun removeTask(@Path("{id}") id: Int);

    companion object {

        private var instance : ApiClient? = null;
        private fun getBuilder() = Retrofit.Builder()
            .baseUrl(Constants.ApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create());

        // factory of AccountService.
        fun create() : ApiClient = instance ?: getBuilder().build()
            .create(ApiClient::class.java)
            .also { instance = it };

        val OkStatusCode = 200;
        val NotFound = 404;
    }
}