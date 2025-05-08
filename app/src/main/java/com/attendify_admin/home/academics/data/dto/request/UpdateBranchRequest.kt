package com.attendify_admin.home.academics.data.dto.request

// Request DTO for updating a branch
data class UpdateBranchRequest(
    val id: String,
    val name: String?,
    val abbreviation: String?
)