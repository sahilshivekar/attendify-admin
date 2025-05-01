package com.attendify_admin.users.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Student
import com.attendify_admin.common.data.remote.response_dto.StudentBatch
import com.attendify_admin.common.data.remote.response_dto.StudentDivision
import com.attendify_admin.common.data.remote.response_dto.StudentListWithTotal
import com.attendify_admin.common.data.remote.response_dto.StudentSemester
import com.attendify_admin.users.data.dto.request.AddStudentToBatchRequest
import com.attendify_admin.users.data.dto.request.AddStudentToDivisionRequest
import com.attendify_admin.users.data.dto.request.AddStudentToSemesterRequest
import com.attendify_admin.users.data.dto.request.ChangeStudentBatchRequest
import com.attendify_admin.users.data.dto.request.ChangeStudentDivisionRequest
import com.attendify_admin.users.data.dto.request.RemoveStudentFromSemesterRequest
import com.attendify_admin.users.data.dto.request.RemoveStudentImageRequest
import com.attendify_admin.users.data.dto.request.RemoveStudentRequest
import com.attendify_admin.users.data.dto.request.UpdateStudentDetailsRequest
import com.attendify_admin.users.data.dto.request.UpdateStudentPasswordRequest
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

interface StudentApi {

    @GET("api/v1/student/get-students")
    suspend fun getStudents(
        @Query("searchQuery") searchQuery: String?,
        @Query("branchIds") branchIds: List<Int>?,
        @Query("semesterNumbers") semesterNumbers: List<Int>?,
        @Query("academicStartYearOfSemester") academicStartYearOfSemester: Int?,
        @Query("academicEndYearOfSemester") academicEndYearOfSemester: Int?,
        @Query("batchId") batchId: Int?,
        @Query("schemeId") schemeId: Int?,
        @Query("divisionId") divisionId: Int?,
        @Query("academicStatuses") academicStatuses: List<String>?,
        @Query("admissionTypes") admissionTypes: List<String>?,
        @Query("admissionYear") admissionYear: Int?,
        @Query("currentBatch") currentBatch: Boolean?,
        @Query("currentDivision") currentDivision: Boolean?,
        @Query("currentSemester") currentSemester: Boolean?,
        @Query("divisionCode") divisionCode: String?,
        @Query("batchCode") batchCode: String?,
        @Query("page") page: Int,
//        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<StudentListWithTotal>>

    @Multipart
    @POST("api/v1/student/add")
    suspend fun addStudent(
        @Part("prn") prn: RequestBody,
        @Part("firstName") firstName: RequestBody,
        @Part("middleName") middleName: RequestBody?,
        @Part("lastName") lastName: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phoneNumber") phoneNumber: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("dob") dob: RequestBody?,
        @Part("schemeId") schemeId: RequestBody,
        @Part("academicStatus") academicStatus: RequestBody,
        @Part("admissionYear") admissionYear: RequestBody,
        @Part("admissionType") admissionType: RequestBody,
        @Part("branchId") branchId: RequestBody,
        @Part studentImageFile: MultipartBody.Part?
    ): Response<AttendifyApiResponse<Student>>

    @PUT("api/v1/student/update-details")
    suspend fun updateStudentDetails(@Body requestBody: UpdateStudentDetailsRequest): Response<AttendifyApiResponse<Student>>

    @PUT("api/v1/student/update-password")
    suspend fun updateStudentPassword(@Body requestBody: UpdateStudentPasswordRequest): Response<AttendifyApiResponse<Student>>

    @Multipart
    @PUT("api/v1/student/update-image")
    suspend fun updateStudentImage(
        @Part("id") studentId: RequestBody,
        @Part studentImageFile: MultipartBody.Part
    ): Response<AttendifyApiResponse<Student>>

    @DELETE("api/v1/student/remove-image")
    suspend fun removeStudentImage(@Body requestBody: RemoveStudentImageRequest): Response<AttendifyApiResponse<Student>>

    @DELETE("api/v1/student/remove")
    suspend fun removeStudent(@Body requestBody: RemoveStudentRequest): Response<AttendifyApiResponse<Unit>>

    @GET("api/v1/student/get-student-details-by-id")
    suspend fun getStudentDetailsById(@Query("studentId") studentId: Int): Response<AttendifyApiResponse<Student>>

    @POST("api/v1/student/add-to-semester")
    suspend fun addStudentToSemester(@Body requestBody: AddStudentToSemesterRequest): Response<AttendifyApiResponse<StudentSemester>>

    @DELETE("api/v1/student/remove-from-semester")
    suspend fun removeStudentFromSemester(@Body requestBody: RemoveStudentFromSemesterRequest): Response<AttendifyApiResponse<Unit>>

    @POST("api/v1/student/add-to-division")
    suspend fun addStudentToDivision(@Body requestBody: AddStudentToDivisionRequest): Response<AttendifyApiResponse<StudentDivision>>

    @PUT("api/v1/student/change-division")
    suspend fun changeStudentDivision(@Body requestBody: ChangeStudentDivisionRequest): Response<AttendifyApiResponse<StudentDivision>>

    @POST("api/v1/student/add-to-batch")
    suspend fun addStudentToBatch(@Body requestBody: AddStudentToBatchRequest): Response<AttendifyApiResponse<StudentBatch>>

    @PUT("api/v1/student/change-batch")
    suspend fun changeStudentBatch(@Body requestBody: ChangeStudentBatchRequest): Response<AttendifyApiResponse<StudentBatch>>

    @GET("api/v1/student/get-student-semesters-by-id")
    suspend fun getStudentSemestersById(@Query("studentId") studentId: Int): Response<AttendifyApiResponse<List<StudentSemester>>>

    @GET("api/v1/student/get-student-divisions-by-id")
    suspend fun getStudentDivisionsById(
        @Query("studentId") studentId: Int,
        @Query("semesterNumber") semesterNumber: Int?
    ): Response<AttendifyApiResponse<List<StudentDivision>>>

    @GET("api/v1/student/get-student-batches-by-id")
    suspend fun getStudentBatchesById(
        @Query("studentId") studentId: Int,
        @Query("semesterNumber") semesterNumber: Int?
    ): Response<AttendifyApiResponse<List<StudentBatch>>>
}