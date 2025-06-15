package com.attendify_admin.feature_admin_auth.data.remote

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.ForgotPasswordRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.LoginRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.VerifyCodeRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.GetAccessRefreshTokenDto
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.LoginDto
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("api/v1/admin/admin/login")
    suspend fun login(@Body requestBody: LoginRequest): Response<AttendifyApiResponse<LoginDto?>>

    @POST("api/v1/admin/admin/forgot-password")
    suspend fun forgotPassword(@Body requestBody: ForgotPasswordRequest): Response<AttendifyApiResponse<VerificationCodeDto?>> // forgot password and email verification have same response on success

    @POST("api/v1/admin/admin/logout")
    suspend fun logout(): Response<AttendifyApiResponse<String?>>

    // login required to attach the access token
    @POST("api/v1/admin/admin/get-access-token")
    suspend fun getAccessToken(@Body requestBody: GetAccessTokenRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenDto?>>

    // if request made while admin is logged in no need to add email in request body
    @POST("api/v1/admin/admin/verify-code")
    suspend fun verifyCode(@Body requestBody: VerifyCodeRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenDto?>>

}