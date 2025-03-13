package com.edu.wiet_admin.academics.data


import com.edu.wiet_admin.academics.data.dto.request.AddCourseRequest
import com.edu.wiet_admin.academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateCourseRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Course
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface CourseApi {

    @GET("api/v1/admin/get-courses")
    suspend fun getCourses(
        @Query("searchQuery") searchQuery: String?,
        @Query("branchId") branchId: String?,
        @Query("semesterNumber") semesterNumber: Int?,
        @Query("schemeId") schemeId: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<WietApiResponse<List<Course?>>>

    @POST("api/v1/admin/add")
    suspend fun addCourse(@Body requestBody: AddCourseRequest): Response<WietApiResponse<Course?>>

    @PUT("api/v1/admin/update")
    suspend fun updateCourse(@Body requestBody: UpdateCourseRequest): Response<WietApiResponse<Course?>>

    @DELETE("api/v1/admin/remove")
    suspend fun removeCourse(@Query("id") courseId: String): Response<WietApiResponse<String?>>

    @POST("api/v1/admin/add-to-branch-with-semester-number")
    suspend fun addCourseToBranchWithSemesterNumber(@Body requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<WietApiResponse<Course?>>

    @DELETE("api/v1/admin/remove-from-branch-with-semester-number")
    suspend fun removeCourseFromBranchWithSemesterNumber(@Query("branchCourseSemesterId") branchCourseSemesterId: String): Response<WietApiResponse<String?>>

    @GET("api/v1/admin/get-course-by-id")
    suspend fun getCourseById(@Query("courseId") courseId: String): Response<WietApiResponse<Course?>>
}