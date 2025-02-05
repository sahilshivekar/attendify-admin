package com.edu.wiet_admin.admin_mgt.data.repository

import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.admin_mgt.data.remote.AdminMgtApi
import com.edu.wiet_admin.admin_mgt.data.remote.UpdatePasswordRequest
import com.edu.wiet_admin.admin_mgt.data.remote.VerifyPasswordRequest
import com.edu.wiet_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response
import javax.inject.Inject

class AdminMgtRepositoryImpl @Inject constructor(
    private val adminMgtApi: AdminMgtApi
) : AdminMgtRepository {

    override suspend fun verifyPassword(requestBody: VerifyPasswordRequest): Response<WietApiResponse<Unit?>> =
        adminMgtApi.verifyPassword(requestBody)

    override suspend fun updateAdminPassword(requestBody: UpdatePasswordRequest): Response<WietApiResponse<Unit?>> =
        adminMgtApi.updateAdminPassword(requestBody)

    override suspend fun sendVerificationCodeToEmail(): Response<WietApiResponse<VerificationCodeData?>> =
        adminMgtApi.sendVerificationCodeToEmail()

}