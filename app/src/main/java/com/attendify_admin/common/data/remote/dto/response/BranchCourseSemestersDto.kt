package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.BranchCourseSemesters
import com.google.gson.annotations.SerializedName

data class BranchCourseSemestersDto(
    val id: Int,
    val semesterNumber: Int,
    val branchId: Int,
    @SerializedName("Branch")
    val branch: BranchDto?,
    val courseId: Int,
    @SerializedName("Course")
    val course: CourseDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun BranchCourseSemestersDto.toBranchCourseSemesters(): BranchCourseSemesters {
    return BranchCourseSemesters(
        id = id,
        semesterNumber = semesterNumber,
        branchId = branchId,
        branch = branch?.toBranch(),
        courseId = courseId,
        course = course?.toCourse()
    )
}
