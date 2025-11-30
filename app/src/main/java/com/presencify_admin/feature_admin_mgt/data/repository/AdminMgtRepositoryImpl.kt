package com.presencify_admin.feature_admin_mgt.data.repository

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.AdminDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import com.presencify_admin.feature_admin_mgt.data.remote.AdminMgtApi
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.AdminRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.UpdateAdminDetailsRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.UpdatePasswordRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.VerifyPasswordRequestBody
import com.presencify_admin.feature_admin_mgt.domain.repository.AdminMgtRepository
import retrofit2.Response
import javax.inject.Inject

class AdminMgtRepositoryImpl @Inject constructor(
    private val adminMgtApi: AdminMgtApi
) : AdminMgtRepository {

    override suspend fun verifyPassword(requestBody: VerifyPasswordRequestBody): Response<PresencifyApiResponse<Unit?>> =
        adminMgtApi.verifyPassword(requestBody)

    override suspend fun updateAdminPassword(requestBody: UpdatePasswordRequestBody): Response<PresencifyApiResponse<Unit?>> =
        adminMgtApi.updateAdminPassword(requestBody)

    override suspend fun sendVerificationCodeToEmail(): Response<PresencifyApiResponse<VerificationCodeDto?>> =
        adminMgtApi.sendVerificationCodeToEmail()

    override suspend fun addAdmin(requestBody: AdminRequestBody): Response<PresencifyApiResponse<AdminDto?>> =
        adminMgtApi.addAdmin(requestBody)

    override suspend fun updateAdminDetails(requestBody: UpdateAdminDetailsRequestBody): Response<PresencifyApiResponse<AdminDto?>> =
        adminMgtApi.updateAdminDetails(requestBody)

    override suspend fun removeAdmin(): Response<PresencifyApiResponse<String?>> =
        adminMgtApi.removeAdmin()

    override suspend fun getAdmins(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Response<PresencifyApiResponse<List<AdminDto>?>> =
        adminMgtApi.getAdmins(searchQuery, sortBy, sortOrder, page, limit)

    override suspend fun getAdminDetails(): Response<PresencifyApiResponse<AdminDto?>> =
        adminMgtApi.getAdminDetails()

}