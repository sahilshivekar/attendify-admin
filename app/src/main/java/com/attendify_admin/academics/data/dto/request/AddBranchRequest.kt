package com.attendify_admin.academics.data.dto.request

// Request DTO for adding a branch
data class AddBranchRequest(
    val name: String,
    val abbreviation: String
)