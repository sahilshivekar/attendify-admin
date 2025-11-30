package com.presencify_admin.feature_admin_auth.domain.repository


import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.ForgotPasswordRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.LoginRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.VerifyCodeRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.GetAccessRefreshTokenDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.LoginDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import retrofit2.Response

interface AuthRepository {

    suspend fun login(requestBody: LoginRequest): Response<PresencifyApiResponse<LoginDto?>>

    suspend fun forgotPassword(requestBody: ForgotPasswordRequest): Response<PresencifyApiResponse<VerificationCodeDto?>>

    suspend fun verifyCode(requestBody: VerifyCodeRequest): Response<PresencifyApiResponse<GetAccessRefreshTokenDto?>>

    suspend fun getAccessRefreshToken(requestBody: GetAccessTokenRequest): Response<PresencifyApiResponse<GetAccessRefreshTokenDto?>>

    suspend fun logout(): Response<PresencifyApiResponse<String?>>

}

