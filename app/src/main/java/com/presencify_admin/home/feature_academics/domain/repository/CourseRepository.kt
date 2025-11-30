package com.presencify_admin.home.feature_academics.domain.repository

import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.CourseDto
import com.presencify_admin.common.data.remote.dto.response.CourseListWIthTotalCountDto
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddCourseRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateCourseRequest
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
    ): Response<PresencifyApiResponse<CourseListWIthTotalCountDto>>


    suspend fun addCourse(requestBody: AddCourseRequest): Response<PresencifyApiResponse<CourseDto?>>

    suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<PresencifyApiResponse<CourseDto?>>

    suspend fun removeCourse(courseId: Int): Response<PresencifyApiResponse<String?>>

    suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<PresencifyApiResponse<CourseDto?>>

    suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<PresencifyApiResponse<String?>>

    suspend fun getCourseById(courseId: Int): Response<PresencifyApiResponse<CourseDto?>>
}