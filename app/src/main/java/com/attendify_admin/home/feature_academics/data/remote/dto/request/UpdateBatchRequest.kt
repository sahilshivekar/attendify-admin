package com.attendify_admin.home.feature_academics.data.remote.dto.request

// Request DTO for updating a batch
data class UpdateBatchRequest(
    val id: String,
    val batchCode: String
)