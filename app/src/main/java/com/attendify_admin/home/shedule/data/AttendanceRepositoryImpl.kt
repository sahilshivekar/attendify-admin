package com.attendify_admin.home.shedule.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Attendance
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudent
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudentCount
import com.attendify_admin.home.shedule.data.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.CreateAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.GetAttendanceOfCourseOnDateRequest
import com.attendify_admin.home.shedule.data.dto.request.GetAttendanceOfCourseThroughoutSemesterRequest
import com.attendify_admin.home.shedule.data.dto.request.GetAttendanceOfStudentForSpecificCourseInSemesterRequest
import com.attendify_admin.home.shedule.data.dto.request.GetAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.RemoveAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.UpdateStudentAttendanceRequest
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import retrofit2.Response
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceApi: AttendanceApi
) : AttendanceRepository {

    override suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<AttendifyApiResponse<Attendance?>> =
        attendanceApi.createAttendance(requestBody)

    override suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudent?>>> =
        attendanceApi.addStudentsAttendance(requestBody)

    override suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<AttendifyApiResponse<AttendanceStudent?>> =
        attendanceApi.updateStudentAttendance(requestBody)

    override suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<AttendifyApiResponse<String?>> =
        attendanceApi.removeAttendance(requestBody.attendanceId)

    override suspend fun getAttendance(requestBody: GetAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudent?>>> =
        attendanceApi.getAttendance(
            requestBody.date,
            requestBody.attendanceId,
            requestBody.classId,
            requestBody.studentId,
            requestBody.courseId,
            requestBody.semesterId,
            requestBody.divisionId
        )

    override suspend fun getAttendanceOfStudentForSpecificCourseInSemester(requestBody: GetAttendanceOfStudentForSpecificCourseInSemesterRequest): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>> =
        attendanceApi.getAttendanceOfStudentForSpecificCourseInSemester(
            requestBody.studentId,
            requestBody.courseId,
            requestBody.semesterId
        )

    override suspend fun getAttendanceOfCourseOnDate(requestBody: GetAttendanceOfCourseOnDateRequest): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>> =
        attendanceApi.getAttendanceOfCourseOnDate(
            requestBody.date,
            requestBody.courseId,
            requestBody.divisionId
        )

    override suspend fun getAttendanceOfCourseThroughoutSemester(requestBody: GetAttendanceOfCourseThroughoutSemesterRequest): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>> =
        attendanceApi.getAttendanceOfCourseThroughoutSemester(
            requestBody.courseId,
            requestBody.divisionId
        )
}