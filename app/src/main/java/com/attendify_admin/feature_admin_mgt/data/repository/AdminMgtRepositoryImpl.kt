package com.attendify_admin.feature_admin_mgt.data.repository

import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.AdminDto
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import com.attendify_admin.feature_admin_mgt.data.remote.AdminMgtApi
import com.attendify_admin.feature_admin_mgt.data.remote.dto.request.AdminRequestBody
import com.attendify_admin.feature_admin_mgt.data.remote.dto.request.UpdateAdminDetailsRequestBody
import com.attendify_admin.feature_admin_mgt.data.remote.dto.request.UpdatePasswordRequestBody
import com.attendify_admin.feature_admin_mgt.data.remote.dto.request.VerifyPasswordRequestBody
import com.attendify_admin.feature_admin_mgt.domain.repository.AdminMgtRepository
import retrofit2.Response
import javax.inject.Inject

class AdminMgtRepositoryImpl @Inject constructor(
    private val adminMgtApi: AdminMgtApi
) : AdminMgtRepository {

    override suspend fun verifyPassword(requestBody: VerifyPasswordRequestBody): Response<AttendifyApiResponse<Unit?>> =
        adminMgtApi.verifyPassword(requestBody)

    override suspend fun updateAdminPassword(requestBody: UpdatePasswordRequestBody): Response<AttendifyApiResponse<Unit?>> =
        adminMgtApi.updateAdminPassword(requestBody)

    override suspend fun sendVerificationCodeToEmail(): Response<AttendifyApiResponse<VerificationCodeDto?>> =
        adminMgtApi.sendVerificationCodeToEmail()

    override suspend fun addAdmin(requestBody: AdminRequestBody): Response<AttendifyApiResponse<AdminDto?>> =
        adminMgtApi.addAdmin(requestBody)

    override suspend fun updateAdminDetails(requestBody: UpdateAdminDetailsRequestBody): Response<AttendifyApiResponse<AdminDto?>> =
        adminMgtApi.updateAdminDetails(requestBody)

    override suspend fun removeAdmin(): Response<AttendifyApiResponse<String?>> =
        adminMgtApi.removeAdmin()

    override suspend fun getAdmins(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Response<AttendifyApiResponse<List<AdminDto>?>> =
        adminMgtApi.getAdmins(searchQuery, sortBy, sortOrder, page, limit)

    override suspend fun getAdminDetails(): Response<AttendifyApiResponse<AdminDto?>> =
        adminMgtApi.getAdminDetails()

}