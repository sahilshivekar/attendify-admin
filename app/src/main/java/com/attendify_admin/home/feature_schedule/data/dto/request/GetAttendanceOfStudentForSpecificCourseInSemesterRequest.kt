package com.attendify_admin.home.feature_schedule.data.dto.request

// Request DTO for getting attendance of a student for a specific course in a semester
data class GetAttendanceOfStudentForSpecificCourseInSemesterRequest(
    val studentId: Int,
    val courseId: Int,
    val semesterId: Int
)