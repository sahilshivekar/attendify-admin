package com.attendify_admin.common.data.remote.dto.response

data class CourseListWIthTotalCountDto(
    val courses: List<CourseDto>,
    val totalCount: Int
)