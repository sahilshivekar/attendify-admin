package com.edu.wiet_admin.admin_mgt.data.remote

import com.edu.wiet_admin.admin_auth.data.remote.responses.AdminData
import com.edu.wiet_admin.admin_auth.data.remote.responses.VerificationCodeData
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AdminMgtApi {
    @POST("api/v1/admin/verify-password")
    suspend fun verifyPassword(@Body requestBody: VerifyPasswordRequestBody): Response<WietApiResponse<Unit?>> // No data in success response

    @PUT("api/v1/admin/update-password")
    suspend fun updateAdminPassword(@Body requestBody: UpdatePasswordRequestBody): Response<WietApiResponse<Unit?>> // No data in success response

    @GET("api/v1/admin/email-verification")
    suspend fun sendVerificationCodeToEmail(): Response<WietApiResponse<VerificationCodeData?>> // forgot password and email verification have same response on success

    @POST("api/v1/admin/add")
    suspend fun addAdmin(@Body requestBody: AdminRequestBody): Response<WietApiResponse<AdminData?>>

    @PUT("api/v1/admin/update-details")
    suspend fun updateAdminDetails(@Body requestBody: UpdateAdminDetailsRequestBody): Response<WietApiResponse<AdminData?>>

    @DELETE("api/v1/admin/remove-admin")
    suspend fun removeAdmin(): Response<WietApiResponse<String?>> // String message in response

    @GET("api/v1/admin/get-admins")
    suspend fun getAdmins(
        @Query("searchQuery") searchQuery: String?,
        @Query("sortBy") sortBy: String?,
        @Query("sortOrder") sortOrder: String?,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Response<WietApiResponse<List<AdminData>?>>

    @GET("api/v1/admin/me")
    suspend fun getAdminDetails(): Response<WietApiResponse<AdminData?>>
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