package com.presencify_admin.home.feature_academics.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.CourseDto
import com.presencify_admin.common.data.remote.dto.response.CourseListWIthTotalCountDto
import com.presencify_admin.home.feature_academics.data.remote.CourseApi
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddCourseRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateCourseRequest
import com.presencify_admin.home.feature_academics.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseApi: CourseApi
) : CourseRepository {

    override fun getCourses(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        schemeId: Int?
    ): Flow<PagingData<CourseDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetCoursesPagingSource(
                    courseApi = courseApi,
                    searchQuery = searchQuery,
                    branchId = branchId,
                    semesterNumber = semesterNumber,
                    schemeId = schemeId
                )
            }
        ).flow
    }

    override suspend fun getAllCourses(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        schemeId: Int?
    ): Response<PresencifyApiResponse<CourseListWIthTotalCountDto>> {
        return courseApi.getCourses(
            searchQuery = searchQuery,
            branchId = branchId,
            semesterNumber = semesterNumber,
            schemeId = schemeId,
            page = 1,
            limit = 10,
            getAll = true
        )
    }


    override suspend fun addCourse(requestBody: AddCourseRequest): Response<PresencifyApiResponse<CourseDto?>> =
        courseApi.addCourse(requestBody)

    override suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<PresencifyApiResponse<CourseDto?>> =
        courseApi.updateCourse(requestBody)

    override suspend fun removeCourse(courseId: Int): Response<PresencifyApiResponse<String?>> =
        courseApi.removeCourse(courseId)

    override suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<PresencifyApiResponse<CourseDto?>> =
        courseApi.addCourseToBranchWithSemesterNumber(requestBody)

    override suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<PresencifyApiResponse<String?>> =
        courseApi.removeCourseFromBranchWithSemesterNumber(requestBody.branchCourseSemesterId)

    override suspend fun getCourseById(courseId: Int): Response<PresencifyApiResponse<CourseDto?>> =
        courseApi.getCourseById(courseId)
}