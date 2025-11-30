package com.presencify_admin.common.data.remote.dto.response

data class ClassListWithTotalCountDto(
    val classes: List<ClassDto>,
    val totalCount: Int
)