package com.attendify_admin.home.feature_academics.data.remote


import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.CourseDto
import com.attendify_admin.common.data.remote.dto.response.CourseListWIthTotalCountDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddCourseRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateCourseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface CourseApi {

    @GET("api/v1/course/admin/get-courses")
    suspend fun getCourses(
        @Query("searchQuery") searchQuery: String?,
        @Query("branchId") branchId: Int?,
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("schemeId") schemeId: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<AttendifyApiResponse<CourseListWIthTotalCountDto>>

    @POST("api/v1/course/admin/add")
    suspend fun addCourse(@Body requestBody: AddCourseRequest): Response<AttendifyApiResponse<CourseDto?>>

    @PUT("api/v1/course/admin/update")
    suspend fun updateCourse(@Body requestBody: UpdateCourseRequest): Response<AttendifyApiResponse<CourseDto?>>

    @DELETE("api/v1/course/admin/remove")
    suspend fun removeCourse(@Query("id") courseId: Int): Response<AttendifyApiResponse<String?>>

    @POST("api/v1/course/admin/add-to-branch-with-semester-number")
    suspend fun addCourseToBranchWithSemesterNumber(@Body requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<CourseDto?>>

    @DELETE("api/v1/course/admin/remove-from-branch-with-semester-number")
    suspend fun removeCourseFromBranchWithSemesterNumber(@Query("branchCourseSemesterId") branchCourseSemesterId: Int): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/course/admin/get-course-by-id")
    suspend fun getCourseById(@Query("courseId") courseId: Int): Response<AttendifyApiResponse<CourseDto?>>
}