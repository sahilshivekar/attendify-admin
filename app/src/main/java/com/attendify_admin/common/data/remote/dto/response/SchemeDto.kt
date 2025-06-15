package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.Scheme
import com.google.gson.annotations.SerializedName

data class SchemeDto(
    val id: Int,
    val name: String,
    val universityId: Int,
    @SerializedName("University")
    val university : UniversityDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun SchemeDto.toScheme(): Scheme {
    return Scheme(
        id = id,
        name = name,
        universityId = universityId,
        university = university?.toUniversity()
    )
}
