package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.Dropout


data class DropoutDto(
    val id: Int,
    val studentId: Int,
    val academicStartYear: Int,
    val academicEndYear: Int,
    val createdAt: String,
    val updatedAt: String
)

fun DropoutDto.toDropout(): Dropout {
    return Dropout(
        id = id,
        studentId = studentId,
        academicStartYear = academicStartYear,
        academicEndYear = academicEndYear
    )
}
