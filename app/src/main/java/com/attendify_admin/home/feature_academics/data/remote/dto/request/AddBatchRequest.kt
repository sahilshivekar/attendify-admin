package com.attendify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for adding a batch
data class AddBatchRequest(
    val batchCode: String,
    val semesterId: Int
)

