package com.presencify_admin.feature_admin_mgt.domain.repository

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.AdminDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.AdminRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.UpdateAdminDetailsRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.UpdatePasswordRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.VerifyPasswordRequestBody
import retrofit2.Response

interface AdminMgtRepository {

    suspend fun sendVerificationCodeToEmail(): Response<PresencifyApiResponse<VerificationCodeDto?>>

    suspend fun updateAdminPassword(requestBody: UpdatePasswordRequestBody): Response<PresencifyApiResponse<Unit?>>

    suspend fun verifyPassword(requestBody: VerifyPasswordRequestBody): Response<PresencifyApiResponse<Unit?>>

    suspend fun addAdmin(requestBody: AdminRequestBody): Response<PresencifyApiResponse<AdminDto?>>

    suspend fun updateAdminDetails(requestBody: UpdateAdminDetailsRequestBody): Response<PresencifyApiResponse<AdminDto?>>

    suspend fun removeAdmin(): Response<PresencifyApiResponse<String?>>

    suspend fun getAdmins(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Response<PresencifyApiResponse<List<AdminDto>?>>

    suspend fun getAdminDetails(): Response<PresencifyApiResponse<AdminDto?>>

}