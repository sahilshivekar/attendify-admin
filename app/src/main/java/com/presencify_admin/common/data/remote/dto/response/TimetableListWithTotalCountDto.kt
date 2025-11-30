package com.presencify_admin.common.data.remote.dto.response

data class TimetableListWithTotalCountDto(
    val timetables: List<TimetableDto>,
    val totalCount: Int
)