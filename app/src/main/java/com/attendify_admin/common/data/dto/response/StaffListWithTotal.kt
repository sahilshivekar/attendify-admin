package com.attendify_admin.common.data.dto.response

data class StaffListWithTotal(
    val staff: List<Staff>,
    val totalStaff: Int
)