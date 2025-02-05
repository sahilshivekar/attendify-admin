package com.edu.wiet_admin.admin_mgt.data.remote

import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface AdminMgtApi {
    @POST("api/v1/admin/verify-password")
    suspend fun verifyPassword(@Body requestBody: VerifyPasswordRequest): Response<WietApiResponse<Unit?>> // No data in success response

    @PUT("api/v1/admin/update-password")
    suspend fun updateAdminPassword(@Body requestBody: UpdatePasswordRequest): Response<WietApiResponse<Unit?>> // No data in success response

    @GET("api/v1/admin/email-verification")
    suspend fun sendVerificationCodeToEmail():Response<WietApiResponse<VerificationCodeData?>> // forgot password and email verification have same response on success

}


data class UpdatePasswordRequest(
    val password: String,
    val confirmPassword: String
)

data class VerifyPasswordRequest(
    val password: String
)