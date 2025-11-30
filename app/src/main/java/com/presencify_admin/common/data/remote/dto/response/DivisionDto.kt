package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.Division
import com.google.gson.annotations.SerializedName

data class DivisionDto(
    val id: Int,
    val divisionCode: String,
    val semesterId: Int,
    @SerializedName("Semester")
    val semester: SemesterDto?,
    @SerializedName("Batch")
    val batch: BatchDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun DivisionDto.toDivision(): Division {
    return Division(
        id = id,
        divisionCode = divisionCode,
        semesterId = semesterId,
        semester = semester?.toSemester(),
        batch = batch?.toBatch()
    )
}
