package com.edu.wiet_admin.admin_auth.data.repository

import com.edu.wiet_admin.admin_auth.data.remote.AuthApi
import com.edu.wiet_admin.admin_auth.data.remote.ForgotPasswordRequest
import com.edu.wiet_admin.admin_auth.data.remote.GetAccessTokenRequest
import com.edu.wiet_admin.admin_auth.data.remote.LoginRequest
import com.edu.wiet_admin.admin_auth.data.remote.VerifyCodeRequest
import com.edu.wiet_admin.admin_auth.data.remote.responses.GetAccessRefreshTokenData
import com.edu.wiet_admin.admin_auth.data.remote.responses.LoginData
import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.admin_auth.domain.repository.AuthRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response


class AuthRepositoryImpl (
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(requestBody: LoginRequest): Response<WietApiResponse<LoginData?>> =
        authApi.login(requestBody)

    override suspend fun forgotPassword(requestBody: ForgotPasswordRequest): Response<WietApiResponse<VerificationCodeData?>> =
        authApi.forgotPassword(requestBody)

    override suspend fun logout(): Response<WietApiResponse<String?>> =
        authApi.logout()

    override suspend fun getAccessRefreshToken(requestBody: GetAccessTokenRequest): Response<WietApiResponse<GetAccessRefreshTokenData?>> =
        authApi.getAccessToken(requestBody)

    override suspend fun verifyCode(requestBody: VerifyCodeRequest): Response<WietApiResponse<GetAccessRefreshTokenData?>> =
        authApi.verifyCode(requestBody)
}
