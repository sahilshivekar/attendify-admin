package com.attendify_admin.feature_admin_mgt.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.AdminDto
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import com.attendify_admin.feature_admin_mgt.data.remote.dto.request.AdminRequestBody
import com.attendify_admin.feature_admin_mgt.data.remote.dto.request.UpdateAdminDetailsRequestBody
import com.attendify_admin.feature_admin_mgt.data.remote.dto.request.UpdatePasswordRequestBody
import com.attendify_admin.feature_admin_mgt.data.remote.dto.request.VerifyPasswordRequestBody
import retrofit2.Response

interface AdminMgtRepository {

    suspend fun sendVerificationCodeToEmail(): Response<AttendifyApiResponse<VerificationCodeDto?>>

    suspend fun updateAdminPassword(requestBody: UpdatePasswordRequestBody): Response<AttendifyApiResponse<Unit?>>

    suspend fun verifyPassword(requestBody: VerifyPasswordRequestBody): Response<AttendifyApiResponse<Unit?>>

    suspend fun addAdmin(requestBody: AdminRequestBody): Response<AttendifyApiResponse<AdminDto?>>

    suspend fun updateAdminDetails(requestBody: UpdateAdminDetailsRequestBody): Response<AttendifyApiResponse<AdminDto?>>

    suspend fun removeAdmin(): Response<AttendifyApiResponse<String?>>

    suspend fun getAdmins(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Response<AttendifyApiResponse<List<AdminDto>?>>

    suspend fun getAdminDetails(): Response<AttendifyApiResponse<AdminDto?>>

}