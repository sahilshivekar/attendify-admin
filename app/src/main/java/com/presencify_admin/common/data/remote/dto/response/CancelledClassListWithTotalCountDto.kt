package com.presencify_admin.common.data.remote.dto.response

data class CancelledClassListWithTotalCountDto(
    val cancelledClasses: List<CancelledClassDto>,
    val totalCount: Int
)