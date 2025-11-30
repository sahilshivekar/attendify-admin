package com.presencify_admin.common.domain.model

data class CancelledClass(
    val id: Int,
    val classId: Int,
    val date: String,
    val reason: String?
)