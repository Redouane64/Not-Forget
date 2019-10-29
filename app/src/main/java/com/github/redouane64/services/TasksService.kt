package com.github.redouane64.services

import android.util.Log
import com.github.redouane64.infrastructure.ApiClient
import com.github.redouane64.models.ApiError
import com.github.redouane64.models.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksService(private val apiClient: ApiClient) {

    fun getTasks(token: String,
                 onFailure: (ApiError) -> Unit,
                 onSuccess: (List<Task>) -> Unit) {

        // pass bearer authorization header in form of bearer <token>.
        apiClient.getTasks("${ApiClient.AuthenticationSchema} $token").enqueue(GetTasksListCallback(onFailure, onSuccess));
    }

    fun createTask(token: String,
                   onFailure: (ApiError) -> Unit,
                   onSuccess: () -> Unit) {

    }

    fun removeTask(token: String,
                   onFailure: (ApiError) -> Unit,
                   onSuccess: () -> Unit) {

    }

    private class GetTasksListCallback(
            private val onFailure: (ApiError) -> Unit,
            private val onSuccess: (List<Task>) -> Unit
        ): Callback<List<Task>> {

        override fun onFailure(call: Call<List<Task>>, t: Throwable) {
            onFailure(ApiError(t.message ?: "Unable to resolve host."))
        }

        override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
            if(response.code() == ApiClient.OkStatusCode) {
                if(response.body() != null)
                    onSuccess(response.body()!!);
                else {
                    Log.d(TAG, "Missing data: body returned is empty.");
                    onFailure(ApiError("Something went wrong."))
                }
            } else {
                // TODO: Handle other status code responses.
            }
        }

    }

    companion object {
        const val TAG = "TasksService";
    }
}