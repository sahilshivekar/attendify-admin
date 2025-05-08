package com.attendify_admin.home.academics.data.dto.request

// Request DTO for adding a batch
data class AddBatchRequest(
    val batchCode: String,
    val semesterId: String
)

