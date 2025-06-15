package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.StaffListWithTotal

data class StaffListWithTotalDto(
    val staff: List<StaffDto>,
    val totalStaff: Int
)

fun StaffListWithTotalDto.toStaffListWithTotal(): StaffListWithTotal {
    return StaffListWithTotal(
        staff = staff.map { it.toStaff() },
        totalStaff = totalStaff
    )
}
