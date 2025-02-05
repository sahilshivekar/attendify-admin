package com.edu.wiet_admin.admin_auth.domain.repository

import com.edu.wiet_admin.admin_auth.data.remote.ForgotPasswordRequest
import com.edu.wiet_admin.admin_auth.data.remote.GetAccessTokenRequest
import com.edu.wiet_admin.admin_auth.data.remote.LoginRequest
import com.edu.wiet_admin.admin_auth.data.remote.VerifyCodeRequest
import com.edu.wiet_admin.admin_auth.data.remote.responses.GetAccessRefreshTokenData
import com.edu.wiet_admin.admin_auth.data.remote.responses.LoginData
import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response

interface AuthRepository {

    suspend fun login(requestBody: LoginRequest): Response<WietApiResponse<LoginData?>>

    suspend fun forgotPassword(requestBody: ForgotPasswordRequest): Response<WietApiResponse<VerificationCodeData?>>

    suspend fun verifyCode(requestBody: VerifyCodeRequest): Response<WietApiResponse<GetAccessRefreshTokenData?>>

    suspend fun getAccessRefreshToken(requestBody: GetAccessTokenRequest): Response<WietApiResponse<GetAccessRefreshTokenData?>>

    suspend fun logout(): Response<WietApiResponse<String?>>

}

