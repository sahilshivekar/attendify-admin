package com.attendify_admin.common.data.remote.dto.response

data class SemesterListWithTotalCountDto(
    val semesters: List<SemesterDto>,
    val totalCount: Int
)