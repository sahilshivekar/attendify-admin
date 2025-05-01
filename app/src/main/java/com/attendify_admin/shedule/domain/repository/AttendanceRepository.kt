package com.attendify_admin.shedule.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Attendance
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudent
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudentCount
import com.attendify_admin.shedule.data.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.shedule.data.dto.request.CreateAttendanceRequest
import com.attendify_admin.shedule.data.dto.request.GetAttendanceOfCourseOnDateRequest
import com.attendify_admin.shedule.data.dto.request.GetAttendanceOfCourseThroughoutSemesterRequest
import com.attendify_admin.shedule.data.dto.request.GetAttendanceOfStudentForSpecificCourseInSemesterRequest
import com.attendify_admin.shedule.data.dto.request.GetAttendanceRequest
import com.attendify_admin.shedule.data.dto.request.RemoveAttendanceRequest
import com.attendify_admin.shedule.data.dto.request.UpdateStudentAttendanceRequest
import retrofit2.Response

interface AttendanceRepository {
    suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<AttendifyApiResponse<Attendance?>>

    suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudent?>>>

    suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<AttendifyApiResponse<AttendanceStudent?>>

    suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<AttendifyApiResponse<String?>>

    suspend fun getAttendance(requestBody: GetAttendanceRequest): Response<AttendifyApiResponse<List<AttendanceStudent?>>>

    suspend fun getAttendanceOfStudentForSpecificCourseInSemester(requestBody: GetAttendanceOfStudentForSpecificCourseInSemesterRequest): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>>

    suspend fun getAttendanceOfCourseOnDate(requestBody: GetAttendanceOfCourseOnDateRequest): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>>

    suspend fun getAttendanceOfCourseThroughoutSemester(requestBody: GetAttendanceOfCourseThroughoutSemesterRequest): Response<AttendifyApiResponse<List<AttendanceStudentCount?>>>
}