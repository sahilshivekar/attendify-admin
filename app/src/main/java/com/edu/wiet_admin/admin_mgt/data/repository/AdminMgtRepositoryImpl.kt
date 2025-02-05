package com.edu.wiet_admin.admin_mgt.data.repository

import com.edu.wiet_admin.admin_auth.data.remote.responses.AdminData
import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.admin_mgt.data.remote.AdminMgtApi
import com.edu.wiet_admin.admin_mgt.data.remote.AdminRequestBody
import com.edu.wiet_admin.admin_mgt.data.remote.UpdateAdminDetailsRequestBody
import com.edu.wiet_admin.admin_mgt.data.remote.UpdatePasswordRequestBody
import com.edu.wiet_admin.admin_mgt.data.remote.VerifyPasswordRequestBody
import com.edu.wiet_admin.admin_mgt.domain.repository.AdminMgtRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response
import javax.inject.Inject

class AdminMgtRepositoryImpl @Inject constructor(
    private val adminMgtApi: AdminMgtApi
) : AdminMgtRepository {

    override suspend fun verifyPassword(requestBody: VerifyPasswordRequestBody): Response<WietApiResponse<Unit?>> =
        adminMgtApi.verifyPassword(requestBody)

    override suspend fun updateAdminPassword(requestBody: UpdatePasswordRequestBody): Response<WietApiResponse<Unit?>> =
        adminMgtApi.updateAdminPassword(requestBody)

    override suspend fun sendVerificationCodeToEmail(): Response<WietApiResponse<VerificationCodeData?>> =
        adminMgtApi.sendVerificationCodeToEmail()

    override suspend fun addAdmin(requestBody: AdminRequestBody): Response<WietApiResponse<AdminData?>> =
        adminMgtApi.addAdmin(requestBody)

    override suspend fun updateAdminDetails(requestBody: UpdateAdminDetailsRequestBody): Response<WietApiResponse<AdminData?>> =
        adminMgtApi.updateAdminDetails(requestBody)

    override suspend fun removeAdmin(): Response<WietApiResponse<String?>> =
        adminMgtApi.removeAdmin()

    override suspend fun getAdmins(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Response<WietApiResponse<List<AdminData>?>> =
        adminMgtApi.getAdmins(searchQuery, sortBy, sortOrder, page, limit)

    override suspend fun getAdminDetails(): Response<WietApiResponse<AdminData?>> =
        adminMgtApi.getAdminDetails()

}