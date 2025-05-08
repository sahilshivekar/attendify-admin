package com.attendify_admin.home.academics.data


import com.attendify_admin.home.academics.data.dto.request.AddCourseRequest
import com.attendify_admin.home.academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateCourseRequest
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Course
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface CourseApi {

    @GET("api/v1/course/get-courses")
    suspend fun getCourses(
        @Query("searchQuery") searchQuery: String?,
        @Query("branchId") branchId: String?,
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("schemeId") schemeId: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<List<Course?>>>

    @POST("api/v1/course/add")
    suspend fun addCourse(@Body requestBody: AddCourseRequest): Response<AttendifyApiResponse<Course?>>

    @PUT("api/v1/course/update")
    suspend fun updateCourse(@Body requestBody: UpdateCourseRequest): Response<AttendifyApiResponse<Course?>>

    @DELETE("api/v1/course/remove")
    suspend fun removeCourse(@Query("id") courseId: String): Response<AttendifyApiResponse<String?>>

    @POST("api/v1/course/add-to-branch-with-semester-number")
    suspend fun addCourseToBranchWithSemesterNumber(@Body requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<Course?>>

    @DELETE("api/v1/course/remove-from-branch-with-semester-number")
    suspend fun removeCourseFromBranchWithSemesterNumber(@Query("branchCourseSemesterId") branchCourseSemesterId: String): Response<AttendifyApiResponse<String?>>

    @GET("api/v1/course/get-course-by-id")
    suspend fun getCourseById(@Query("courseId") courseId: String): Response<AttendifyApiResponse<Course?>>
}