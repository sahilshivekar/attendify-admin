package com.attendify_admin.admin_auth.domain.repository

import com.attendify_admin.admin_auth.data.remote.ForgotPasswordRequest
import com.attendify_admin.admin_auth.data.remote.GetAccessTokenRequest
import com.attendify_admin.admin_auth.data.remote.LoginRequest
import com.attendify_admin.admin_auth.data.remote.VerifyCodeRequest
import com.attendify_admin.admin_auth.data.remote.responses.GetAccessRefreshTokenData
import com.attendify_admin.admin_auth.data.remote.responses.LoginData
import com.attendify_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import retrofit2.Response

interface AuthRepository {

    suspend fun login(requestBody: LoginRequest): Response<AttendifyApiResponse<LoginData?>>

    suspend fun forgotPassword(requestBody: ForgotPasswordRequest): Response<AttendifyApiResponse<VerificationCodeData?>>

    suspend fun verifyCode(requestBody: VerifyCodeRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenData?>>

    suspend fun getAccessRefreshToken(requestBody: GetAccessTokenRequest): Response<AttendifyApiResponse<GetAccessRefreshTokenData?>>

    suspend fun logout(): Response<AttendifyApiResponse<String?>>

}

