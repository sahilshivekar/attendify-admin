package com.edu.wiet_admin.academics.domain.repository

import com.edu.wiet_admin.academics.data.dto.request.AddCourseRequest
import com.edu.wiet_admin.academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.edu.wiet_admin.academics.data.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateCourseRequest
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Course
import retrofit2.Response


interface CourseRepository {
    suspend fun getCourses(
        searchQuery: String?,
        branchId: String?,
        semesterNumber: Int?,
        schemeId: String?,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Course?>>>

    suspend fun addCourse(requestBody: AddCourseRequest): Response<WietApiResponse<Course?>>

    suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<WietApiResponse<Course?>>

    suspend fun removeCourse(courseId: String): Response<WietApiResponse<String?>>

    suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<WietApiResponse<Course?>>

    suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<WietApiResponse<String?>>

    suspend fun getCourseById(courseId: String): Response<WietApiResponse<Course?>>
}