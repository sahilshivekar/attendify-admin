package com.presencify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for adding a branch
data class AddBranchRequest(
    val name: String,
    val abbreviation: String
)