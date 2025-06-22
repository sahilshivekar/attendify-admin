package com.attendify_admin.home.feature_academics.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.CourseDto
import com.attendify_admin.home.feature_academics.data.remote.CourseApi
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddCourseRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateCourseRequest
import com.attendify_admin.home.feature_academics.domain.repository.CourseRepository
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

    override suspend fun addCourse(requestBody: AddCourseRequest): Response<AttendifyApiResponse<CourseDto?>> =
        courseApi.addCourse(requestBody)

    override suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<AttendifyApiResponse<CourseDto?>> =
        courseApi.updateCourse(requestBody)

    override suspend fun removeCourse(courseId: Int): Response<AttendifyApiResponse<String?>> =
        courseApi.removeCourse(courseId)

    override suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<CourseDto?>> =
        courseApi.addCourseToBranchWithSemesterNumber(requestBody)

    override suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<String?>> =
        courseApi.removeCourseFromBranchWithSemesterNumber(requestBody.branchCourseSemesterId)

    override suspend fun getCourseById(courseId: Int): Response<AttendifyApiResponse<CourseDto?>> =
        courseApi.getCourseById(courseId)
}