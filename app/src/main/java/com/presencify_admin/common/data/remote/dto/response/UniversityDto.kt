package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.University

data class UniversityDto(
    val id: Int,
    val name: String,
    val abbreviation: String,
    val createdAt: String,
    val updatedAt: String
)

fun UniversityDto.toUniversity(): University {
    return University(
        id = id,
        name = name,
        abbreviation = abbreviation
    )
}
