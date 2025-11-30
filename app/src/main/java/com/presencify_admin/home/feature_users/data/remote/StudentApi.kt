package com.presencify_admin.home.feature_users.data.remote

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.DropoutDto
import com.presencify_admin.common.data.remote.dto.response.StudentBatchDto
import com.presencify_admin.common.data.remote.dto.response.StudentDivisionDto
import com.presencify_admin.common.data.remote.dto.response.StudentDto
import com.presencify_admin.common.data.remote.dto.response.StudentFCMTokenDto
import com.presencify_admin.common.data.remote.dto.response.StudentListWithTotalDto
import com.presencify_admin.common.data.remote.dto.response.StudentSemesterDto
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddDropoutRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentFcmTokenRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentToBatchRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentToDivisionRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentToSemesterRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.ChangeStudentBatchRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.ChangeStudentDivisionRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStudentDetailsRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStudentFcmTokenRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStudentPasswordRequest
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
    @GET("api/v1/student/admin/get-students")
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
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("dropoutAcademicStartYear") dropoutAcademicStartYear: String?,
        @Query("dropoutAcademicEndYear") dropoutAcademicEndYear: String?,
        @Query("getAll") getAll: Boolean,
        @Query("semesterId") semesterId: Int?,
    ): Response<PresencifyApiResponse<StudentListWithTotalDto>>


    @Multipart
    @POST("api/v1/student/admin/add")
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
        @Part("admissionYear") admissionYear: RequestBody,
        @Part("admissionType") admissionType: RequestBody,
        @Part("branchId") branchId: RequestBody,
        @Part studentImageFile: MultipartBody.Part?,
        @Part("parentEmail") parentEmail: RequestBody,
    ): Response<PresencifyApiResponse<StudentDto>>

    @PUT("api/v1/student/admin/update-details")
    suspend fun updateStudentDetails(@Body requestBody: UpdateStudentDetailsRequest): Response<PresencifyApiResponse<StudentDto>>

    @PUT("api/v1/student/admin/update-password")
    suspend fun updateStudentPassword(@Body requestBody: UpdateStudentPasswordRequest): Response<PresencifyApiResponse<StudentDto>>

    @Multipart
    @PUT("api/v1/student/admin/update-image")
    suspend fun updateStudentImage(
        @Part("id") studentId: RequestBody,
        @Part studentImageFile: MultipartBody.Part,
    ): Response<PresencifyApiResponse<StudentDto>>

    @DELETE("api/v1/student/admin/remove-image")
    suspend fun removeStudentImage(@Query("id") studentId: Int): Response<PresencifyApiResponse<StudentDto>>

    @DELETE("api/v1/student/admin/remove")
    suspend fun removeStudent(@Query("id") studentId: Int): Response<PresencifyApiResponse<Unit>>

    @GET("api/v1/student/admin/get-student-details-by-id")
    suspend fun getStudentDetailsById(@Query("studentId") studentId: Int): Response<PresencifyApiResponse<StudentDto>>

    @POST("api/v1/student/admin/add-to-semester")
    suspend fun addStudentToSemester(@Body requestBody: AddStudentToSemesterRequest): Response<PresencifyApiResponse<StudentSemesterDto>>

    @DELETE("api/v1/student/admin/remove-from-semester")
    suspend fun removeStudentFromSemester(@Query("studentSemesterId") studentSemesterId: Int): Response<PresencifyApiResponse<Unit>>

    @POST("api/v1/student/admin/add-to-division")
    suspend fun addStudentToDivision(@Body requestBody: AddStudentToDivisionRequest): Response<PresencifyApiResponse<StudentDivisionDto>>

    @PUT("api/v1/student/admin/change-division")
    suspend fun changeStudentDivision(@Body requestBody: ChangeStudentDivisionRequest): Response<PresencifyApiResponse<StudentDivisionDto>>

    @POST("api/v1/student/admin/add-to-batch")
    suspend fun addStudentToBatch(@Body requestBody: AddStudentToBatchRequest): Response<PresencifyApiResponse<StudentBatchDto>>

    @PUT("api/v1/student/admin/change-batch")
    suspend fun changeStudentBatch(@Body requestBody: ChangeStudentBatchRequest): Response<PresencifyApiResponse<StudentBatchDto>>

    @GET("api/v1/student/admin/get-student-semesters-by-id")
    suspend fun getStudentSemestersById(@Query("studentId") studentId: Int): Response<PresencifyApiResponse<List<StudentSemesterDto>?>>

    @GET("api/v1/student/admin/get-student-divisions-by-id")
    suspend fun getStudentDivisionsById(
        @Query("studentId") studentId: Int,
        @Query("semesterNumber") semesterNumber: Int?,
    ): Response<PresencifyApiResponse<List<StudentDivisionDto>?>>

    @GET("api/v1/student/admin/get-student-batches-by-id")
    suspend fun getStudentBatchesById(
        @Query("studentId") studentId: Int,
        @Query("semesterNumber") semesterNumber: Int?,
    ): Response<PresencifyApiResponse<List<StudentBatchDto>?>>

    @POST("api/v1/dropout/admin/add-student-to-dropout")
    suspend fun addStudentToDropout(@Body requestBody: AddDropoutRequest): Response<PresencifyApiResponse<DropoutDto?>>

    @DELETE("api/v1/dropout/admin/remove-student-from-dropout")
    suspend fun removeStudentFromDropout(
        @Query("studentId") studentId: Int,
        @Query("academicStartYear") academicStartYear: Int,
        @Query("academicEndYear") academicEndYear: Int,
    ): Response<PresencifyApiResponse<Unit>>

    @GET("api/v1/dropout/admin/get-dropout-by-id")
    suspend fun getDropoutById(@Query("dropoutId") dropoutId: Int): Response<PresencifyApiResponse<DropoutDto?>>

    @GET("api/v1/dropout/admin/get-dropout-details-of-student")
    suspend fun getDropoutDetailsOfStudent(@Query("studentId") studentId: Int): Response<PresencifyApiResponse<List<DropoutDto>?>>

    @POST("api/v1/student-fcm-token/admin/add-student-fcm-token")
    suspend fun addStudentFcmToken(@Body requestBody: AddStudentFcmTokenRequest): Response<PresencifyApiResponse<StudentFCMTokenDto?>>

    @PUT("api/v1/student-fcm-token/admin/update-student-fcm-token")
    suspend fun updateStudentFcmToken(@Body requestBody: UpdateStudentFcmTokenRequest): Response<PresencifyApiResponse<StudentFCMTokenDto?>>

    @DELETE("api/v1/student-fcm-token/admin/remove-student-fcm-token")
    suspend fun removeStudentFcmToken(@Query("studentId") studentId: Int): Response<PresencifyApiResponse<Unit>>
}

