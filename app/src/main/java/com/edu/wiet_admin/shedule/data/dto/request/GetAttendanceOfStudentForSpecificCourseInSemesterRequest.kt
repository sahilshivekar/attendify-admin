package com.edu.wiet_admin.shedule.data.dto.request

// Request DTO for getting attendance of a student for a specific course in a semester
data class GetAttendanceOfStudentForSpecificCourseInSemesterRequest(
    val studentId: String,
    val courseId: String,
    val semesterId: String
)