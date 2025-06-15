package com.attendify_admin.home.feature_users.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.StaffDto
import com.attendify_admin.common.data.remote.dto.response.StaffListWithTotalDto
import com.attendify_admin.common.data.remote.dto.response.TeacherTeachesDto
import com.attendify_admin.home.feature_users.data.dto.request.AddTeachingSubjectRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveImageRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveStaffRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStaffDetailsRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStaffPasswordRequest
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

    @GET("api/v1/staff/admin/get-staff")
    suspend fun getStaff(
        @Query("searchQuery") searchQuery: String?,
        @Query("courseId") courseId: Int?,
        @Query("page") page: Int
    ): Response<AttendifyApiResponse<StaffListWithTotalDto>>

    @GET("api/v1/staff/admin/get-staff-by-id")
    suspend fun getStaffById(@Query("staffId") staffId: Int): Response<AttendifyApiResponse<StaffDto>>

    @Multipart
    @POST("api/v1/staff/admin/add")
    suspend fun addStaff(
        @Part("firstName") firstName: RequestBody,
        @Part("middleName") middleName: RequestBody?,
        @Part("lastName") lastName: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phoneNumber") phoneNumber: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("highestQualification") highestQualification: RequestBody?,
        @Part("role") role: RequestBody,
        @Part("isActive") isActive: RequestBody,
        @Part staffImageFile: MultipartBody.Part?
    ): Response<AttendifyApiResponse<StaffDto>>

    @PUT("api/v1/staff/admin/update-details")
    suspend fun updateStaffDetails(@Body requestBody: UpdateStaffDetailsRequest): Response<AttendifyApiResponse<StaffDto>>

    @PUT("api/v1/staff/admin/update-password")
    suspend fun updateStaffPassword(@Body requestBody: UpdateStaffPasswordRequest): Response<AttendifyApiResponse<StaffDto>>

    @Multipart
    @PUT("api/v1/staff/admin/update-image")
    suspend fun updateStaffImage(
        @Part("id") id: RequestBody,
        @Part("staffImageFile") staffImageFile: MultipartBody.Part
    ): Response<AttendifyApiResponse<StaffDto>>

    @DELETE("api/v1/staff/admin/remove")
    suspend fun removeStaff(@Body requestBody: RemoveStaffRequest): Response<AttendifyApiResponse<Unit>>

    @DELETE("api/v1/staff/admin/remove-image")
    suspend fun removeImage(@Body requestBody: RemoveImageRequest): Response<AttendifyApiResponse<StaffDto>>

    @GET("api/v1/staff/admin/get-teaching-subjects")
    suspend fun getTeachingSubjects(@Query("staffId") staffId: Int): Response<AttendifyApiResponse<List<TeacherTeachesDto>?>>

    @POST("api/v1/staff/admin/add-teaching-subject")
    suspend fun addTeachingSubject(@Body requestBody: AddTeachingSubjectRequest): Response<AttendifyApiResponse<TeacherTeachesDto>>

    @DELETE("api/v1/staff/admin/remove-teaching-subject")
    suspend fun removeTeachingSubject(@Query("teacherSubjectId") teacherSubjectId: Int): Response<AttendifyApiResponse<Unit>>
}