package com.github.redouane64.infrastructure

import com.github.redouane64.models.ApiToken
import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.models.RegisterForm
import com.github.redouane64.models.Task
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {

    @Headers("Accept: application/json")
    @POST("login")
    fun login(@Body credentials: LoginCredentials): Call<ApiToken>

    @Headers("Accept: application/json")
    @POST("register")
    fun register(@Body form: RegisterForm): Call<RegisterForm>

    @GET("tasks")
    fun getTasks(@Header("Authorization") token: String): Call<List<Task>>;

    @POST("tasks")
    fun createTask(@Header("Authorization") token: String, @Body task: Task): Call<Void>;

    @DELETE("tasks")
    fun removeTask(@Header("Authorization") token: String, @Path("{id}") id: Int);

    @PATCH("tasks/{id}")
    fun updateTask(@Header("Authorization") token: String, @Path("id") id: Int, @Body task: Task): Call<Task>;

    companion object {

        private var instance : ApiClient? = null;
        private fun getBuilder() = Retrofit.Builder()
            .baseUrl(Constants.ApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create());

        // factory of AccountService.
        fun create() : ApiClient = instance ?: getBuilder().build()
            .create(ApiClient::class.java)
            .also { instance = it };

        const val OkStatusCode = 200;
        const val NotFound = 404;
        // by default our Api auth schema is Bearer.
        const val AuthenticationSchema = "Bearer"
    }
}