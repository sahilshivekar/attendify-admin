package com.attendify_admin.academics.domain.repository

import com.attendify_admin.academics.data.dto.request.AddCourseRequest
import com.attendify_admin.academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.attendify_admin.academics.data.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.attendify_admin.academics.data.dto.request.UpdateCourseRequest
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Course
import retrofit2.Response


interface CourseRepository {
    suspend fun getCourses(
        searchQuery: String?,
        branchId: String?,
        semesterNumber: Int?,
        schemeId: String?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<Course?>>>

    suspend fun addCourse(requestBody: AddCourseRequest): Response<AttendifyApiResponse<Course?>>

    suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<AttendifyApiResponse<Course?>>

    suspend fun removeCourse(courseId: String): Response<AttendifyApiResponse<String?>>

    suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<Course?>>

    suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<String?>>

    suspend fun getCourseById(courseId: String): Response<AttendifyApiResponse<Course?>>
}