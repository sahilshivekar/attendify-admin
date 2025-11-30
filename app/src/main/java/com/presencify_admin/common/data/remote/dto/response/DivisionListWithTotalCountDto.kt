package com.presencify_admin.common.data.remote.dto.response

data class DivisionListWithTotalCountDto(
    val divisions: List<DivisionDto>,
    val totalCount: Int
)