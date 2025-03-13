package com.edu.wiet_admin.shedule.data


import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Attendance
import com.edu.wiet_admin.common.data.remote.response_dto.AttendanceStudent
import com.edu.wiet_admin.common.data.remote.response_dto.AttendanceStudentCount
import com.edu.wiet_admin.shedule.data.dto.request.AddStudentsAttendanceRequest
import com.edu.wiet_admin.shedule.data.dto.request.CreateAttendanceRequest
import com.edu.wiet_admin.shedule.data.dto.request.GetAttendanceOfCourseOnDateRequest
import com.edu.wiet_admin.shedule.data.dto.request.GetAttendanceOfCourseThroughoutSemesterRequest
import com.edu.wiet_admin.shedule.data.dto.request.GetAttendanceOfStudentForSpecificCourseInSemesterRequest
import com.edu.wiet_admin.shedule.data.dto.request.GetAttendanceRequest
import com.edu.wiet_admin.shedule.data.dto.request.RemoveAttendanceRequest
import com.edu.wiet_admin.shedule.data.dto.request.UpdateStudentAttendanceRequest
import com.edu.wiet_admin.shedule.domain.repository.AttendanceRepository
import retrofit2.Response
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceApi: AttendanceApi
) : AttendanceRepository {

    override suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<WietApiResponse<Attendance?>> =
        attendanceApi.createAttendance(requestBody)

    override suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<WietApiResponse<List<AttendanceStudent?>>> =
        attendanceApi.addStudentsAttendance(requestBody)

    override suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<WietApiResponse<AttendanceStudent?>> =
        attendanceApi.updateStudentAttendance(requestBody)

    override suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<WietApiResponse<String?>> =
        attendanceApi.removeAttendance(requestBody.attendanceId)

    override suspend fun getAttendance(requestBody: GetAttendanceRequest): Response<WietApiResponse<List<AttendanceStudent?>>> =
        attendanceApi.getAttendance(
            requestBody.date,
            requestBody.attendanceId,
            requestBody.classId,
            requestBody.studentId,
            requestBody.courseId,
            requestBody.semesterId,
            requestBody.divisionId
        )

    override suspend fun getAttendanceOfStudentForSpecificCourseInSemester(requestBody: GetAttendanceOfStudentForSpecificCourseInSemesterRequest): Response<WietApiResponse<List<AttendanceStudentCount?>>> =
        attendanceApi.getAttendanceOfStudentForSpecificCourseInSemester(
            requestBody.studentId,
            requestBody.courseId,
            requestBody.semesterId
        )

    override suspend fun getAttendanceOfCourseOnDate(requestBody: GetAttendanceOfCourseOnDateRequest): Response<WietApiResponse<List<AttendanceStudentCount?>>> =
        attendanceApi.getAttendanceOfCourseOnDate(
            requestBody.date,
            requestBody.courseId,
            requestBody.divisionId
        )

    override suspend fun getAttendanceOfCourseThroughoutSemester(requestBody: GetAttendanceOfCourseThroughoutSemesterRequest): Response<WietApiResponse<List<AttendanceStudentCount?>>> =
        attendanceApi.getAttendanceOfCourseThroughoutSemester(
            requestBody.courseId,
            requestBody.divisionId
        )
}