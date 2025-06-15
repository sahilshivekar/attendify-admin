package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.AttendanceStudent
import com.google.gson.annotations.SerializedName

data class AttendanceStudentDto(
    val id: Int,
    val attendanceStatus: Boolean,
    val studentId: Int,
    @SerializedName("Student")
    val student: StudentDto?,
    val attendanceId: Int,
    @SerializedName("Attendance")
    val attendance: AttendanceDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun AttendanceStudentDto.toAttendanceStudent(): AttendanceStudent {
    return AttendanceStudent(
        id = id,
        attendanceStatus = attendanceStatus,
        studentId = studentId,
        student = student?.toStudent(),
        attendanceId = attendanceId,
        attendance = attendance?.toAttendance()
    )
}
