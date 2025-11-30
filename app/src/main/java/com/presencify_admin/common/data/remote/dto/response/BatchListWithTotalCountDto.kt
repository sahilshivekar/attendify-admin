package com.presencify_admin.common.data.remote.dto.response

data class BatchListWithTotalCountDto(
    val batches: List<BatchDto>,
    val totalCount: Int
)

