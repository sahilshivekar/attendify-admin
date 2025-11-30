package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.Course
import com.google.gson.annotations.SerializedName

data class CourseDto(
    val id: Int,
    val code: String,
    val name: String,
    val optionalSubject: String?,
    @SerializedName("BranchCourseSemesters")
    val branchCourseSemesters: List<BranchCourseSemestersDto>?,
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
        branchCourseSemesters = branchCourseSemesters?.map{
            it.toBranchCourseSemesters()
        },
        schemeId = schemeId,
        scheme = scheme?.toScheme()
    )
}
