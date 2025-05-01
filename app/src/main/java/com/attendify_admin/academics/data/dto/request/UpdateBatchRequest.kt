package com.attendify_admin.academics.data.dto.request

// Request DTO for updating a batch
data class UpdateBatchRequest(
    val id: String,
    val batchCode: String
)