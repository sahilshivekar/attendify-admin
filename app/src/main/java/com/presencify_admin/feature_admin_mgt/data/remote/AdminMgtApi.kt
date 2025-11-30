package com.presencify_admin.feature_admin_mgt.data.remote

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.AdminDto
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.VerificationCodeDto
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.AdminRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.UpdateAdminDetailsRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.UpdatePasswordRequestBody
import com.presencify_admin.feature_admin_mgt.data.remote.dto.request.VerifyPasswordRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AdminMgtApi {
    @POST("api/v1/admin/admin/verify-password")
    suspend fun verifyPassword(@Body requestBody: VerifyPasswordRequestBody):
            Response<PresencifyApiResponse<Unit?>> // No data in success response

    @PUT("api/v1/admin/admin/update-password")
    suspend fun updateAdminPassword(@Body requestBody: UpdatePasswordRequestBody):
            Response<PresencifyApiResponse<Unit?>> // No data in success response

    @GET("api/v1/admin/admin/email-verification")
    suspend fun sendVerificationCodeToEmail():
            Response<PresencifyApiResponse<VerificationCodeDto?>> // forgot password and email verification have same response on success

    @POST("api/v1/admin/admin/add")
    suspend fun addAdmin(@Body requestBody: AdminRequestBody):
            Response<PresencifyApiResponse<AdminDto?>>

    @PUT("api/v1/admin/admin/update-details")
    suspend fun updateAdminDetails(@Body requestBody: UpdateAdminDetailsRequestBody):
            Response<PresencifyApiResponse<AdminDto?>>

    @DELETE("api/v1/admin/admin/remove-admin")
    suspend fun removeAdmin():
            Response<PresencifyApiResponse<String?>> // String message in response

    @GET("api/v1/admin/admin/get-admins")
    suspend fun getAdmins(
        @Query("searchQuery") searchQuery: String?,
        @Query("sortBy") sortBy: String?,
        @Query("sortOrder") sortOrder: String?,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
    ): Response<PresencifyApiResponse<List<AdminDto>?>>

    @GET("api/v1/admin/admin/me")
    suspend fun getAdminDetails():
            Response<PresencifyApiResponse<AdminDto?>>
}

