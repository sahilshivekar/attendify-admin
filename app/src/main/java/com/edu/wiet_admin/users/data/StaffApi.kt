package com.edu.wiet_admin.users.data

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Staff
import com.edu.wiet_admin.common.data.remote.response_dto.TeacherTeaches
import com.edu.wiet_admin.users.data.dto.request.AddTeachingSubjectRequest
import com.edu.wiet_admin.users.data.dto.request.RemoveImageRequest
import com.edu.wiet_admin.users.data.dto.request.RemoveStaffRequest
import com.edu.wiet_admin.users.data.dto.request.UpdateStaffDetailsRequest
import com.edu.wiet_admin.users.data.dto.request.UpdateStaffPasswordRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface StaffApi {

    @GET("api/v1/admin/get-staff")
    suspend fun getStaff(
        @Query("searchQuery") searchQuery: String?,
        @Query("courseId") courseId: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<WietApiResponse<List<Staff>>>

    @GET("api/v1/admin/get-staff-by-id")
    suspend fun getStaffById(@Query("staffId") staffId: Int): Response<WietApiResponse<Staff>>

    @Multipart
    @POST("api/v1/admin/add")
    suspend fun addStaff(
        @Part("firstName") firstName: RequestBody,
        @Part("middleName") middleName: RequestBody?,
        @Part("lastName") lastName: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phoneNumber") phoneNumber: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("highestQualification") highestQualification: RequestBody?,
        @Part("role") role: RequestBody,
        @Part("password") password: RequestBody,
        @Part("confirmPassword") confirmPassword: RequestBody,
        @Part("isActive") isActive: RequestBody,
        @Part("staffImageFile") staffImageFile: MultipartBody.Part?
    ): Response<WietApiResponse<Staff>>

    @PUT("api/v1/admin/update-details")
    suspend fun updateStaffDetails(@Body requestBody: UpdateStaffDetailsRequest): Response<WietApiResponse<Staff>>

    @PUT("api/v1/admin/update-password")
    suspend fun updateStaffPassword(@Body requestBody: UpdateStaffPasswordRequest): Response<WietApiResponse<Staff>>

    @Multipart
    @PUT("api/v1/admin/update-image")
    suspend fun updateStaffImage(
        @Part("id") id: RequestBody,
        @Part("staffImageFile") staffImageFile: MultipartBody.Part
    ): Response<WietApiResponse<Staff>>

    @DELETE("api/v1/admin/remove")
    suspend fun removeStaff(@Body requestBody: RemoveStaffRequest): Response<WietApiResponse<Unit>>

    @DELETE("api/v1/admin/remove-image")
    suspend fun removeImage(@Body requestBody: RemoveImageRequest): Response<WietApiResponse<Staff>>

    @GET("api/v1/admin/get-teaching-subjects")
    suspend fun getTeachingSubjects(@Query("staffId") staffId: Int): Response<WietApiResponse<List<TeacherTeaches>>>

    @POST("api/v1/admin/add-teaching-subject")
    suspend fun addTeachingSubject(@Body requestBody: AddTeachingSubjectRequest): Response<WietApiResponse<TeacherTeaches>>

    @DELETE("api/v1/admin/remove-teaching-subject")
    suspend fun removeTeachingSubject(@Query("teacherSubjectId") teacherSubjectId: Int): Response<WietApiResponse<Unit>>
}