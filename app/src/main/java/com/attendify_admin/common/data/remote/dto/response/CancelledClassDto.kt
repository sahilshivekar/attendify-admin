package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.CancelledClass

data class CancelledClassDto(
    val id: Int,
    val classId: Int,
    val date: String,
    val reason: String?,
    val createdAt: String,
    val updatedAt: String
)


fun CancelledClassDto.toCancelledClass(): CancelledClass {
    return CancelledClass(
        id = id,
        classId = classId,
        date = date,
        reason = reason
    )
}
