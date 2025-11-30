package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.Branch

data class BranchDto(
    val id: Int,
    val abbreviation: String,
    val name: String,
    val createdAt: String,
    val updatedAt: String
)

fun BranchDto.toBranch(): Branch {
    return Branch(
        id = id,
        abbreviation = abbreviation,
        name = name
    )
}
