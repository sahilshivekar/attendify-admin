package com.attendify_admin.common.data.remote.response_dto

data class Attendance(
    val id: Int,
    val date: String,
    val classId: Int,
    val Class: Class?,
    val BLEsessionUUID: String?,
    val createdAt: String,
    val updatedAt: String,
)

// get Attendance Of All For Specific Course In Semester, Division, Batch
data class AttendanceSummaryDto(
    val courseId: Int,
    val attendanceSummary: List<AttendanceRecord>
)
data class AttendanceRecord(
    val attendanceDate: String,
    val totalStudents: Int,
    val presentStudents: Int,
    val attendanceId: Int
)


// get Attendance Of Student For Specific Course In Semester
data class AggregatedAttendanceDto(
    val courseId: Int,
    val courseName: String,
    val totalLectures: Int,
    val attendedLectures: Int
)
data class DetailedAttendanceRecordDto(
    val attendanceId: Int,
    val date: String,
    val attendanceStatus: Boolean
)
