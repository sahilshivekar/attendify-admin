package com.attendify_admin.admin_mgt.data.repository

import com.attendify_admin.admin_auth.data.remote.responses.AdminData
import com.attendify_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.attendify_admin.admin_mgt.data.remote.AdminMgtApi
import com.attendify_admin.admin_mgt.data.remote.AdminRequestBody
import com.attendify_admin.admin_mgt.data.remote.UpdateAdminDetailsRequestBody
import com.attendify_admin.admin_mgt.data.remote.UpdatePasswordRequestBody
import com.attendify_admin.admin_mgt.data.remote.VerifyPasswordRequestBody
import com.attendify_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import retrofit2.Response
import javax.inject.Inject

class AdminMgtRepositoryImpl @Inject constructor(
    private val adminMgtApi: AdminMgtApi
) : AdminMgtRepository {

    override suspend fun verifyPassword(requestBody: VerifyPasswordRequestBody): Response<AttendifyApiResponse<Unit?>> =
        adminMgtApi.verifyPassword(requestBody)

    override suspend fun updateAdminPassword(requestBody: UpdatePasswordRequestBody): Response<AttendifyApiResponse<Unit?>> =
        adminMgtApi.updateAdminPassword(requestBody)

    override suspend fun sendVerificationCodeToEmail(): Response<AttendifyApiResponse<VerificationCodeData?>> =
        adminMgtApi.sendVerificationCodeToEmail()

    override suspend fun addAdmin(requestBody: AdminRequestBody): Response<AttendifyApiResponse<AdminData?>> =
        adminMgtApi.addAdmin(requestBody)

    override suspend fun updateAdminDetails(requestBody: UpdateAdminDetailsRequestBody): Response<AttendifyApiResponse<AdminData?>> =
        adminMgtApi.updateAdminDetails(requestBody)

    override suspend fun removeAdmin(): Response<AttendifyApiResponse<String?>> =
        adminMgtApi.removeAdmin()

    override suspend fun getAdmins(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Response<AttendifyApiResponse<List<AdminData>?>> =
        adminMgtApi.getAdmins(searchQuery, sortBy, sortOrder, page, limit)

    override suspend fun getAdminDetails(): Response<AttendifyApiResponse<AdminData?>> =
        adminMgtApi.getAdminDetails()

}