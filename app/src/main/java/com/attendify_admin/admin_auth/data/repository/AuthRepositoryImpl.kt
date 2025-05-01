package com.attendify_admin.admin_auth.data.repository

import com.attendify_admin.admin_auth.data.remote.AuthApi
import com.attendify_admin.admin_auth.data.remote.ForgotPasswordRequest
import com.attendify_admin.admin_auth.data.remote.GetAccessTokenRequest
import com.attendify_admin.admin_auth.data.remote.LoginRequest
import com.attendify_admin.admin_auth.data.remote.VerifyCodeRequest
import com.attendify_admin.admin_auth.data.remote.responses.GetAccessRefreshTokenData
import com.attendify_admin.admin_auth.data.remote.responses.LoginData
import com.attendify_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.attendify_admin.admin_auth.domain.repository.AuthRepository
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import retrofit2.Response


class AuthRepositoryImpl (
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(requestBody: LoginRequest): Response<AttendifyApiResponse<LoginData?>> =
        authApi.login(requestBody)

    override suspend fun forgotPassword(requestBody: ForgotPasswordRequest): Response<AttendifyApiResponse<VerificationCodeData?>> =
        authApi.forgotPassword(requestBody)

    override suspend fun logout(): Response<AttendifyApiResponse<String?>> =
        authApi.logout()

    override suspend fun getAccessRefreshToken(requestBody: GetAccessTokenRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenData?>> =
        authApi.getAccessToken(requestBody)

    override suspend fun verifyCode(requestBody: VerifyCodeRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenData?>> =
        authApi.verifyCode(requestBody)
}
