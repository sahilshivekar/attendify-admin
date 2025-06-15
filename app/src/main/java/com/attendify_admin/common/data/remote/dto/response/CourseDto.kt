package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.Course
import com.google.gson.annotations.SerializedName

data class CourseDto(
    val id: Int,
    val code: String,
    val name: String,
    val optionalSubject: Any,
    @SerializedName("BranchCourseSemesters")
    val branchCourseSemesters: BranchCourseSemestersDto?,
    val schemeId: Int,
    @SerializedName("Scheme")
    val scheme: SchemeDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun CourseDto.toCourse(): Course {
    return Course(
        id = id,
        code = code,
        name = name,
        optionalSubject = optionalSubject,
        branchCourseSemesters = branchCourseSemesters?.toBranchCourseSemesters(),
        schemeId = schemeId,
        scheme = scheme?.toScheme()
    )
}
