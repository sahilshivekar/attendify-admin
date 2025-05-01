package com.attendify_admin.admin_auth.data.remote

import com.attendify_admin.admin_auth.data.remote.responses.GetAccessRefreshTokenData
import com.attendify_admin.admin_auth.data.remote.responses.LoginData
import com.attendify_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT


interface AuthApi {

    @POST("api/v1/admin/login")
    suspend fun login(@Body requestBody: LoginRequest): Response<AttendifyApiResponse<LoginData?>>

    @POST("api/v1/admin/forgot-password")
    suspend fun forgotPassword(@Body requestBody: ForgotPasswordRequest): Response<AttendifyApiResponse<VerificationCodeData?>> // forgot password and email verification have same response on success

    @POST("api/v1/admin/logout")
    suspend fun logout(): Response<AttendifyApiResponse<String?>>

    // login required to attach the access token
    @POST("api/v1/admin/get-access-token")
    suspend fun getAccessToken(@Body requestBody: GetAccessTokenRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenData?>>

    // if request made while admin is logged in no need to add email in request body
    @POST("api/v1/admin/verify-code")
    suspend fun verifyCode(@Body requestBody: VerifyCodeRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenData?>>

}


data class LoginRequest(
    val emailOrUsername: String,
    val password: String
)

data class ForgotPasswordRequest(
    val email: String
)

data class GetAccessTokenRequest(
    val refreshToken: String
)

data class VerifyCodeRequest(
    val code: String,
    val email: String?
)