package com.edu.wiet_admin.admin_mgt.domain.repository

import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.admin_mgt.data.remote.UpdatePasswordRequest
import com.edu.wiet_admin.admin_mgt.data.remote.VerifyPasswordRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response

interface AdminMgtRepository {

    suspend fun sendVerificationCodeToEmail(): Response<WietApiResponse<VerificationCodeData?>>

    suspend fun updateAdminPassword(requestBody: UpdatePasswordRequest): Response<WietApiResponse<Unit?>>

    suspend fun verifyPassword(requestBody: VerifyPasswordRequest): Response<WietApiResponse<Unit?>>

}