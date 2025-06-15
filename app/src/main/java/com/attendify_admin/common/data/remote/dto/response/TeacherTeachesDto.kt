package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.TeacherTeaches
import com.google.gson.annotations.SerializedName

data class TeacherTeachesDto(
    val id: Int,
    val teacherId: Int,
    @SerializedName("Staff")
    val staff: StaffDto?,
    val courseId: Int,
    @SerializedName("Course")
    val course: CourseDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun TeacherTeachesDto.toTeacherTeaches(): TeacherTeaches {
    return TeacherTeaches(
        id = id,
        teacherId = teacherId,
        staff = staff?.toStaff(),
        courseId = courseId,
        course = course?.toCourse()
    )
}
