package com.github.redouane64.services

import android.util.Log
import com.github.redouane64.infrastructure.ApiClient
import com.github.redouane64.models.ApiError
import com.github.redouane64.models.ApiToken
import com.github.redouane64.models.LoginCredentials
import com.github.redouane64.models.RegisterForm
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountService(private val apiClient: ApiClient) {

    fun login(credentials: LoginCredentials,
              onSuccess: (ApiToken) -> Unit,
              onFailure: (ApiError) -> Unit) {
        apiClient.login(credentials).enqueue(LoginCallback(onSuccess, onFailure));
    }

    fun register(form: RegisterForm,
                 onFailure: (ApiError) -> Unit,
                 onSuccess: (RegisterForm) -> Unit) {
        apiClient.register(form).enqueue(RegisterCallback(onSuccess, onFailure));
    }

    private class RegisterCallback(
        private val onSuccess: (RegisterForm) -> Unit,
        private val onFailure: (ApiError) -> Unit
    ): Callback<RegisterForm> {

        override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
            onFailure(ApiError(t.message ?: "Something went wrong."));
        }

        override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
            if(response.code() == ApiClient.OkStatusCode)
            {
                if(response.body() != null) {
                    onSuccess(response.body()!!);
                } else {
                    Log.d(TasksService.TAG, "Missing data: body returned is empty.");
                    onFailure(ApiError("Something went wrong."))
                }
            } else {
                // TODO:
            }
        }

    }

    private class LoginCallback(
        private val onSuccess: (ApiToken) -> Unit,
        private val onFailure: (ApiError) -> Unit
    ) : Callback<ApiToken> {

        override fun onFailure(call: Call<ApiToken>, t: Throwable) {
            onFailure(ApiError("Unable to resolve host."));
        }

        override fun onResponse(call: Call<ApiToken>, response: Response<ApiToken>) {

            // Api return an error.
            if(response.code() == ApiClient.NotFound) {

                val rawResponse : String? = response.errorBody()?.string();
                val error = Gson().fromJson<ApiError>(rawResponse, ApiError::class.java);

                onFailure(error);
                return;
            }

            // Api call succeeded.
            if(response.code() == ApiClient.OkStatusCode) {
                onSuccess(response.body()!!);
                return;
            }

            // at this point something really weird coming from Api.
            // but i don't care for now.
        }
    }

    companion object {
        val TAG = "AccountService";
    }
}