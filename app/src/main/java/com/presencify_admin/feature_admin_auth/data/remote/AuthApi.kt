package com.presencify_admin.feature_admin_auth.data.remote

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.ForgotPasswordRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.LoginRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.VerifyCodeRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.GetAccessRefreshTokenDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.LoginDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("api/v1/admin/admin/login")
    suspend fun login(@Body requestBody: LoginRequest): Response<PresencifyApiResponse<LoginDto?>>

    @POST("api/v1/admin/admin/forgot-password")
    suspend fun forgotPassword(@Body requestBody: ForgotPasswordRequest): Response<PresencifyApiResponse<VerificationCodeDto?>> // forgot password and email verification have same response on success

    @POST("api/v1/admin/admin/logout")
    suspend fun logout(): Response<PresencifyApiResponse<String?>>

    // login required to attach the access token
    @POST("api/v1/admin/admin/get-access-token")
    suspend fun getAccessToken(@Body requestBody: GetAccessTokenRequest): Response<PresencifyApiResponse<GetAccessRefreshTokenDto?>>

    // if request made while admin is logged in no need to add email in request body
    @POST("api/v1/admin/admin/verify-code")
    suspend fun verifyCode(@Body requestBody: VerifyCodeRequest): Response<PresencifyApiResponse<GetAccessRefreshTokenDto?>>

}