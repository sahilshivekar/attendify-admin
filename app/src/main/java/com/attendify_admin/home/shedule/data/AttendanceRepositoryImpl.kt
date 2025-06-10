package com.attendify_admin.home.shedule.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Attendance
import com.attendify_admin.common.data.remote.response_dto.AttendanceAllStudents
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudent
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudentAggregatedAndDetailedAttendance
import com.attendify_admin.common.data.remote.response_dto.NoParentEmailStudents
import com.attendify_admin.home.shedule.data.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.CreateAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.MarkAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.RemoveAttendanceRequest
import com.attendify_admin.home.shedule.data.dto.request.SendAttendanceReportRequest
import com.attendify_admin.home.shedule.data.dto.request.UpdateStudentAttendanceRequest
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import retrofit2.Response
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceApi: AttendanceApi,
) : AttendanceRepository {

    override suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<AttendifyApiResponse<Attendance?>> =
        attendanceApi.createAttendance(requestBody)

    override suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudent?>>> =
        attendanceApi.addStudentsAttendance(requestBody)

    override suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<AttendifyApiResponse<AttendanceStudent?>> =
        attendanceApi.updateStudentAttendance(requestBody)

    override suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<AttendifyApiResponse<String?>> =
        attendanceApi.removeAttendance(requestBody.attendanceId)

    override suspend fun getAttendance(
        date: String?,
        attendanceId: String?,
        classId: String?,
        studentId: String?,
        courseId: String?,
        semesterId: String?,
        divisionId: String?,
    ): Response<AttendifyApiResponse<List<AttendanceStudent?>>> =
        attendanceApi.getAttendance(
            date,
            attendanceId,
            classId,
            studentId,
            courseId,
            semesterId,
            divisionId
        )

    override suspend fun getAttendanceOfStudent(
        studentId: String,
        courseId: String,
        semesterId: String,
        divisionId: String,
        batchId: String,
        startDate: String,
        endDate: String,
    ): Response<AttendifyApiResponse<AttendanceStudentAggregatedAndDetailedAttendance>> =
        attendanceApi.getAttendanceOfStudent(
            studentId,
            courseId,
            semesterId,
            divisionId,
            batchId,
            startDate,
            endDate
        )

    override suspend fun getAttendanceOfAllForSemesterDivisionBatchCourse(
        courseId: String,
        semesterId: String,
        divisionId: String,
        batchId: String,
        startDate: String,
        endDate: String,
    ): Response<AttendifyApiResponse<AttendanceAllStudents>> =
        attendanceApi.getAttendanceOfAllForSemesterDivisionBatchCourse(
            courseId,
            semesterId,
            divisionId,
            batchId,
            startDate,
            endDate
        )

    override suspend fun markStudentAttendanceByBLEsessionUUID(
        bleSessionUUID: String,
        studentId: Int,
    ): Response<AttendifyApiResponse<String?>> =
        attendanceApi.markStudentAttendanceByBLEsessionUUID(
            MarkAttendanceRequest(
                bleSessionUUID,
                studentId
            )
        )

    override suspend fun sendAttendanceReport(
        startDate: String,
        endDate: String,
        studentIds: List<String>,
        courseIds: List<String>,
        semesterId: String,
    ): Response<AttendifyApiResponse<List<NoParentEmailStudents>?>> =
        attendanceApi.sendAttendanceReport(
            SendAttendanceReportRequest(
                startDate,
                endDate,
                studentIds,
                courseIds,
                semesterId
            )
        )

    override suspend fun getActiveAttendanceSheet(
        studentId: String,
        divisionId: String,
    ): Response<AttendifyApiResponse<List<Attendance>?>> =
        attendanceApi.getActiveAttendanceSheet(
            studentId,
            divisionId
        )
}