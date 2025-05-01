package com.attendify_admin.academics.data.dto.request

// Request DTO for updating a branch
data class UpdateBranchRequest(
    val id: String,
    val name: String?,
    val abbreviation: String?
)