package com.edu.wiet_admin.admin_mgt.domain.repository

import com.edu.wiet_admin.admin_auth.data.remote.responses.AdminData
import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.admin_mgt.data.remote.AdminRequestBody
import com.edu.wiet_admin.admin_mgt.data.remote.UpdateAdminDetailsRequestBody
import com.edu.wiet_admin.admin_mgt.data.remote.UpdatePasswordRequestBody
import com.edu.wiet_admin.admin_mgt.data.remote.VerifyPasswordRequestBody
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response

interface AdminMgtRepository {

    suspend fun sendVerificationCodeToEmail(): Response<WietApiResponse<VerificationCodeData?>>

    suspend fun updateAdminPassword(requestBody: UpdatePasswordRequestBody): Response<WietApiResponse<Unit?>>

    suspend fun verifyPassword(requestBody: VerifyPasswordRequestBody): Response<WietApiResponse<Unit?>>

    suspend fun addAdmin(requestBody: AdminRequestBody): Response<WietApiResponse<AdminData?>>

    suspend fun updateAdminDetails(requestBody: UpdateAdminDetailsRequestBody): Response<WietApiResponse<AdminData?>>

    suspend fun removeAdmin(): Response<WietApiResponse<String?>>

    suspend fun getAdmins(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Response<WietApiResponse<List<AdminData>?>>

    suspend fun getAdminDetails(): Response<WietApiResponse<AdminData?>>

}