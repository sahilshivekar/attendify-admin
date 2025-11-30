package com.presencify_admin.common.domain.model

data class StaffListWithTotal(
    val staff: List<Staff>,
    val totalStaff: Int
)