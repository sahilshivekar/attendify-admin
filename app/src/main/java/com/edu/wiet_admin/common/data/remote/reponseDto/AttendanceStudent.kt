package com.edu.wiet_admin.common.data.remote.reponseDto

data class AttendanceStudent(
    val id: Int,
    val attendanceStatus: Boolean,
    val studentId: Int,
    val Student: Student?,
    val attendanceId: Int,
    val Attendance: Attendance?,
    val createdAt: String,
    val updatedAt: String,
)