package com.attendify_admin.home.feature_academics.domain.repository

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.CourseDto
import com.attendify_admin.common.data.remote.dto.response.CourseListWIthTotalCountDto
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddCourseRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateCourseRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface CourseRepository {
    fun getCourses(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        schemeId: Int?,
    ): Flow<PagingData<CourseDto>>

    suspend fun getAllCourses(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        schemeId: Int?
    ): Response<AttendifyApiResponse<CourseListWIthTotalCountDto>>


    suspend fun addCourse(requestBody: AddCourseRequest): Response<AttendifyApiResponse<CourseDto?>>

    suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<AttendifyApiResponse<CourseDto?>>

    suspend fun removeCourse(courseId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<CourseDto?>>

    suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<String?>>

    suspend fun getCourseById(courseId: Int): Response<AttendifyApiResponse<CourseDto?>>
}