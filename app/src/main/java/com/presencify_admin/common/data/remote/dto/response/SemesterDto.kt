package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.Semester
import com.google.gson.annotations.SerializedName

data class SemesterDto(
    val id: Int,
    val academicEndYear: Int,
    val academicStartYear: Int,
    val semesterNumber: Int,
    val endDate: String,
    val startDate: String,
    val branchId: Int,
    @SerializedName("Branch")
    val branch: BranchDto?,
    val schemeId: Int,
    @SerializedName("Scheme")
    val scheme: SchemeDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun SemesterDto.toSemester(): Semester {
    return Semester(
        id = id,
        academicEndYear = academicEndYear,
        academicStartYear = academicStartYear,
        semesterNumber = semesterNumber,
        endDate = endDate,
        startDate = startDate,
        branchId = branchId,
        branch = branch?.toBranch(),
        schemeId = schemeId,
        scheme = scheme?.toScheme()
    )
}

