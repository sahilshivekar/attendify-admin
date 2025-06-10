package com.attendify_admin.home.academics.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.Course
import com.attendify_admin.home.academics.data.dto.request.AddCourseRequest
import com.attendify_admin.home.academics.data.dto.request.AddCourseToBranchWithSemesterNumberRequest
import com.attendify_admin.home.academics.data.dto.request.RemoveCourseFromBranchWithSemesterNumberRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateCourseRequest
import com.attendify_admin.home.academics.domain.repository.CourseRepository
import retrofit2.Response
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseApi: CourseApi
) : CourseRepository {

    override suspend fun getCourses(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        schemeId: Int?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<Course?>>> =
        courseApi.getCourses(searchQuery, branchId, semesterNumber, schemeId, page, limit)

    override suspend fun addCourse(requestBody: AddCourseRequest): Response<AttendifyApiResponse<Course?>> =
        courseApi.addCourse(requestBody)

    override suspend fun updateCourse(requestBody: UpdateCourseRequest): Response<AttendifyApiResponse<Course?>> =
        courseApi.updateCourse(requestBody)

    override suspend fun removeCourse(courseId: Int): Response<AttendifyApiResponse<String?>> =
        courseApi.removeCourse(courseId)

    override suspend fun addCourseToBranchWithSemesterNumber(requestBody: AddCourseToBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<Course?>> =
        courseApi.addCourseToBranchWithSemesterNumber(requestBody)

    override suspend fun removeCourseFromBranchWithSemesterNumber(requestBody: RemoveCourseFromBranchWithSemesterNumberRequest): Response<AttendifyApiResponse<String?>> =
        courseApi.removeCourseFromBranchWithSemesterNumber(requestBody.branchCourseSemesterId)

    override suspend fun getCourseById(courseId: Int): Response<AttendifyApiResponse<Course?>> =
        courseApi.getCourseById(courseId)
}