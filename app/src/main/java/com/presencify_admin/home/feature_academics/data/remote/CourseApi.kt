package com.presencify_admin.home.feature_academics.data.remote


import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.CourseDto
import com.presencify_admin.common.data.remote.dto.response.CourseListWIthTotalCountDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddCourseRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateCourseRequest
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
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("getAll") getAll: Boolean
    ): Response<PresencifyApiResponse<CourseListWIthTotalCountDto>>


    @POST("api/v1/course/admin/add")
    suspend fun addCourse(@Body requestBody: AddCourseRequest): Response<PresencifyApiResponse<CourseDto?>>

    @PUT("api/v1/course/admin/update")
    suspend fun updateCourse(@Body requestBody: UpdateCourseRequest): Response<PresencifyApiResponse<CourseDto?>>

    @DELETE("api/v1/course/admin/remove")
    suspend fun removeCourse(@Query("id") courseId: Int): Response<PresencifyApiResponse<String?>>

    @POST("api/v1/course/admin/add-to-branch-with-semester-number")
    suspend fun addCourseToBranchWithSemesterNumber(@Body requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<PresencifyApiResponse<CourseDto?>>

    @DELETE("api/v1/course/admin/remove-from-branch-with-semester-number")
    suspend fun removeCourseFromBranchWithSemesterNumber(@Query("branchCourseSemesterId") branchCourseSemesterId: Int): Response<PresencifyApiResponse<String?>>

    @GET("api/v1/course/admin/get-course-by-id")
    suspend fun getCourseById(@Query("courseId") courseId: Int): Response<PresencifyApiResponse<CourseDto?>>
}