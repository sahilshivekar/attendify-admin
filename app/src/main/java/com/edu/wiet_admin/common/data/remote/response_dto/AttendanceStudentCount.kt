package com.edu.wiet_admin.common.data.remote.response_dto

data class AttendanceStudentCount(
    val courseName: String?,
    val studentName: String?,
    val attendedLectures: String,
    val totalLectures: String,
    val courseId: Int?,
    val Course: Course?,
)