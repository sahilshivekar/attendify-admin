package com.attendify_admin.common.domain

import android.util.Log
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

object RemoteUtils {

    const val NETWORK_IO_ERROR_MESSAGE: String =
        "Unable to reach server! check your internet connection."
    const val UNKNOWN_NETWORK_ERROR_MESSAGE: String = "Unknown error occurred!"
    const val UNAUTHORIZED_REQUEST_ERROR_MESSAGE: String =
        "Your login session is expired. Please login again."

    fun isKnownError(message: String?) =
        message in listOf(
            NETWORK_IO_ERROR_MESSAGE,
            UNKNOWN_NETWORK_ERROR_MESSAGE,
            UNAUTHORIZED_REQUEST_ERROR_MESSAGE
        )

    fun <T> getErrorMessage(response: Response<T>): String? {
        val errorBody = response.errorBody()?.string()
        errorBody?.let {
            val errorMessage = Gson().fromJson(errorBody, AttendifyApiResponse::class.java).message
            return errorMessage
        }
        return null
    }

    fun <T> responseFlow(
        apiCall: suspend () -> Response<AttendifyApiResponse<T>>,
    ): Flow<Resource<AttendifyApiResponse<T>>> = flow {
        try {
            emit(Resource.Loading())

            val response = apiCall()

            if (response.isSuccessful) {
                Log.d("responseFlow", response.body().toString())
                emit(Resource.Success(data = response.body()))
            } else {
                val errorMessage = getErrorMessage(response)
                emit(Resource.Error(message = errorMessage))
                errorMessage?.let { Log.d("responseFlow", it) }
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = NETWORK_IO_ERROR_MESSAGE))
        } catch (e: Exception) {
            Log.d("responseFlow", e.toString())
            emit(Resource.Error(message = UNKNOWN_NETWORK_ERROR_MESSAGE))
        }
    }
}