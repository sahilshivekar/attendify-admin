package com.attendify_admin.common.data.remote.dto.response

data class CancelledClassListWithTotalCountDto(
    val cancelledClasses: List<CancelledClassDto>,
    val totalCount: Int
)