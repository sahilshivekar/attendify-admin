package com.attendify_admin.home.feature_academics.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.CourseDto
import com.attendify_admin.home.feature_academics.data.dto.request.AddCourseRequest
import com.attendify_admin.home.feature_academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.attendify_admin.home.feature_academics.data.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.attendify_admin.home.feature_academics.data.dto.request.UpdateCourseRequest
import retrofit2.Response


interface CourseRepository {
    suspend fun getCourses(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        schemeId: Int?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<CourseDto>?>>

    suspend fun addCourse(requestBody: AddCourseRequest): Response<AttendifyApiResponse<CourseDto?>>

    suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<AttendifyApiResponse<CourseDto?>>

    suspend fun removeCourse(courseId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<CourseDto?>>

    suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<String?>>

    suspend fun getCourseById(courseId: Int): Response<AttendifyApiResponse<CourseDto?>>
}