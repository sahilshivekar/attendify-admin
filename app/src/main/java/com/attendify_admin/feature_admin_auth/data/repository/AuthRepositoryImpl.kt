package com.attendify_admin.feature_admin_auth.data.repository

import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.feature_admin_auth.data.remote.AuthApi
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.ForgotPasswordRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.LoginRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.VerifyCodeRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.GetAccessRefreshTokenDto
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.LoginDto
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import com.attendify_admin.feature_admin_auth.domain.repository.AuthRepository
import retrofit2.Response


class AuthRepositoryImpl (
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(requestBody: LoginRequest): Response<AttendifyApiResponse<LoginDto?>> =
        authApi.login(requestBody)

    override suspend fun forgotPassword(requestBody: ForgotPasswordRequest): Response<AttendifyApiResponse<VerificationCodeDto?>> =
        authApi.forgotPassword(requestBody)

    override suspend fun logout(): Response<AttendifyApiResponse<String?>> =
        authApi.logout()

    override suspend fun getAccessRefreshToken(requestBody: GetAccessTokenRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenDto?>> =
        authApi.getAccessToken(requestBody)

    override suspend fun verifyCode(requestBody: VerifyCodeRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenDto?>> =
        authApi.verifyCode(requestBody)
}
