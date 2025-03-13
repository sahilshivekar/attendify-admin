package com.edu.wiet_admin.academics.data

import com.edu.wiet_admin.academics.data.dto.request.AddCourseRequest
import com.edu.wiet_admin.academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.edu.wiet_admin.academics.data.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateCourseRequest
import com.edu.wiet_admin.academics.domain.repository.CourseRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Course
import retrofit2.Response
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseApi: CourseApi
) : CourseRepository {

    override suspend fun getCourses(
        searchQuery: String?,
        branchId: String?,
        semesterNumber: Int?,
        schemeId: String?,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Course?>>> =
        courseApi.getCourses(searchQuery, branchId, semesterNumber, schemeId, page, limit)

    override suspend fun addCourse(requestBody: AddCourseRequest): Response<WietApiResponse<Course?>> =
        courseApi.addCourse(requestBody)

    override suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<WietApiResponse<Course?>> =
        courseApi.updateCourse(requestBody)

    override suspend fun removeCourse(courseId: String): Response<WietApiResponse<String?>> =
        courseApi.removeCourse(courseId)

    override suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<WietApiResponse<Course?>> =
        courseApi.addCourseToBranchWithSemesterNumber(requestBody)

    override suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<WietApiResponse<String?>> =
        courseApi.removeCourseFromBranchWithSemesterNumber(requestBody.branchCourseSemesterId)

    override suspend fun getCourseById(courseId: String): Response<WietApiResponse<Course?>> =
        courseApi.getCourseById(courseId)
}