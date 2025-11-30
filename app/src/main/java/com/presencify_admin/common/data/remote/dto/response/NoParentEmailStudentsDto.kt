package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.NoParentEmailStudents

data class NoParentEmailStudentsDto(
    val studentId: Int,
    val firstName: String,
    val lastName: String
)

fun NoParentEmailStudentsDto.toNoParentEmailStudents(): NoParentEmailStudents {
    return NoParentEmailStudents(
        studentId = studentId,
        firstName = firstName,
        lastName = lastName
    )
}
