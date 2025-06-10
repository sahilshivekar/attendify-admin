package com.attendify_admin.admin_mgt.data.remote

import com.attendify_admin.admin_auth.data.remote.responses.AdminData
import com.attendify_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AdminMgtApi {
    @POST("api/v1/admin/admin/verify-password")
    suspend fun verifyPassword(@Body requestBody: VerifyPasswordRequestBody): Response<AttendifyApiResponse<Unit?>> // No data in success response

    @PUT("api/v1/admin/admin/update-password")
    suspend fun updateAdminPassword(@Body requestBody: UpdatePasswordRequestBody): Response<AttendifyApiResponse<Unit?>> // No data in success response

    @GET("api/v1/admin/admin/email-verification")
    suspend fun sendVerificationCodeToEmail(): Response<AttendifyApiResponse<VerificationCodeData?>> // forgot password and email verification have same response on success

    @POST("api/v1/admin/admin/add")
    suspend fun addAdmin(@Body requestBody: AdminRequestBody): Response<AttendifyApiResponse<AdminData?>>

    @PUT("api/v1/admin/admin/update-details")
    suspend fun updateAdminDetails(@Body requestBody: UpdateAdminDetailsRequestBody): Response<AttendifyApiResponse<AdminData?>>

    @DELETE("api/v1/admin/admin/remove-admin")
    suspend fun removeAdmin(): Response<AttendifyApiResponse<String?>> // String message in response

    @GET("api/v1/admin/admin/get-admins")
    suspend fun getAdmins(
        @Query("searchQuery") searchQuery: String?,
        @Query("sortBy") sortBy: String?,
        @Query("sortOrder") sortOrder: String?,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Response<AttendifyApiResponse<List<AdminData>?>>

    @GET("api/v1/admin/admin/me")
    suspend fun getAdminDetails(): Response<AttendifyApiResponse<AdminData?>>
}


data class UpdatePasswordRequestBody(
    val password: String,
    val confirmPassword: String
)

data class VerifyPasswordRequestBody(
    val password: String
)

data class UpdateAdminDetailsRequestBody(
    val email: String,
    val username: String
)

data class AdminRequestBody(
    val email: String,
    val username: String,
    val password: String
)