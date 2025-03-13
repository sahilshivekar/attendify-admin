package com.edu.wiet_admin.shedule.domain.repository

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
import retrofit2.Response

interface AttendanceRepository {
    suspend fun createAttendance(requestBody: CreateAttendanceRequest): Response<WietApiResponse<Attendance?>>

    suspend fun addStudentsAttendance(requestBody: AddStudentsAttendanceRequest): Response<WietApiResponse<List<AttendanceStudent?>>>

    suspend fun updateStudentAttendance(requestBody: UpdateStudentAttendanceRequest): Response<WietApiResponse<AttendanceStudent?>>

    suspend fun removeAttendance(requestBody: RemoveAttendanceRequest): Response<WietApiResponse<String?>>

    suspend fun getAttendance(requestBody: GetAttendanceRequest): Response<WietApiResponse<List<AttendanceStudent?>>>

    suspend fun getAttendanceOfStudentForSpecificCourseInSemester(requestBody: GetAttendanceOfStudentForSpecificCourseInSemesterRequest): Response<WietApiResponse<List<AttendanceStudentCount?>>>

    suspend fun getAttendanceOfCourseOnDate(requestBody: GetAttendanceOfCourseOnDateRequest): Response<WietApiResponse<List<AttendanceStudentCount?>>>

    suspend fun getAttendanceOfCourseThroughoutSemester(requestBody: GetAttendanceOfCourseThroughoutSemesterRequest): Response<WietApiResponse<List<AttendanceStudentCount?>>>
}