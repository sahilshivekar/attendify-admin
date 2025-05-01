package com.attendify_admin.admin_mgt.domain.repository

import com.attendify_admin.admin_auth.data.remote.responses.AdminData
import com.attendify_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.attendify_admin.admin_mgt.data.remote.AdminRequestBody
import com.attendify_admin.admin_mgt.data.remote.UpdateAdminDetailsRequestBody
import com.attendify_admin.admin_mgt.data.remote.UpdatePasswordRequestBody
import com.attendify_admin.admin_mgt.data.remote.VerifyPasswordRequestBody
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import retrofit2.Response

interface AdminMgtRepository {

    suspend fun sendVerificationCodeToEmail(): Response<AttendifyApiResponse<VerificationCodeData?>>

    suspend fun updateAdminPassword(requestBody: UpdatePasswordRequestBody): Response<AttendifyApiResponse<Unit?>>

    suspend fun verifyPassword(requestBody: VerifyPasswordRequestBody): Response<AttendifyApiResponse<Unit?>>

    suspend fun addAdmin(requestBody: AdminRequestBody): Response<AttendifyApiResponse<AdminData?>>

    suspend fun updateAdminDetails(requestBody: UpdateAdminDetailsRequestBody): Response<AttendifyApiResponse<AdminData?>>

    suspend fun removeAdmin(): Response<AttendifyApiResponse<String?>>

    suspend fun getAdmins(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Response<AttendifyApiResponse<List<AdminData>?>>

    suspend fun getAdminDetails(): Response<AttendifyApiResponse<AdminData?>>

}