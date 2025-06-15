package com.attendify_admin.feature_admin_auth.domain.repository


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.ForgotPasswordRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.LoginRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.VerifyCodeRequest
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.GetAccessRefreshTokenDto
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.LoginDto
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import retrofit2.Response

interface AuthRepository {

    suspend fun login(requestBody: LoginRequest): Response<AttendifyApiResponse<LoginDto?>>

    suspend fun forgotPassword(requestBody: ForgotPasswordRequest): Response<AttendifyApiResponse<VerificationCodeDto?>>

    suspend fun verifyCode(requestBody: VerifyCodeRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenDto?>>

    suspend fun getAccessRefreshToken(requestBody: GetAccessTokenRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenDto?>>

    suspend fun logout(): Response<AttendifyApiResponse<String?>>

}

