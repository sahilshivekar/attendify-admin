package com.presencify_admin.feature_admin_auth.data.repository

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.feature_admin_auth.data.remote.AuthApi
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.ForgotPasswordRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.LoginRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.VerifyCodeRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.GetAccessRefreshTokenDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.LoginDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import com.presencify_admin.feature_admin_auth.domain.repository.AuthRepository
import retrofit2.Response


class AuthRepositoryImpl (
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(requestBody: LoginRequest): Response<PresencifyApiResponse<LoginDto?>> =
        authApi.login(requestBody)

    override suspend fun forgotPassword(requestBody: ForgotPasswordRequest): Response<PresencifyApiResponse<VerificationCodeDto?>> =
        authApi.forgotPassword(requestBody)

    override suspend fun logout(): Response<PresencifyApiResponse<String?>> =
        authApi.logout()

    override suspend fun getAccessRefreshToken(requestBody: GetAccessTokenRequest): Response<PresencifyApiResponse<GetAccessRefreshTokenDto?>> =
        authApi.getAccessToken(requestBody)

    override suspend fun verifyCode(requestBody: VerifyCodeRequest): Response<PresencifyApiResponse<GetAccessRefreshTokenDto?>> =
        authApi.verifyCode(requestBody)
}
