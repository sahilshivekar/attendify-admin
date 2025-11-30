package com.presencify_admin.common.domain.model

data class AttendanceStudent(
    val id: Int,
    val attendanceStatus: Boolean,
    val studentId: Int,
    val student: Student?,
    val attendanceId: Int,
    val attendance: Attendance?
)