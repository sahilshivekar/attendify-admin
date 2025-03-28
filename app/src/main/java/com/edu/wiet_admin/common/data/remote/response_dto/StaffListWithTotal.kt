package com.edu.wiet_admin.common.data.remote.response_dto

data class StaffListWithTotal(
    val staff: List<Staff>,
    val totalStaff: Int
)