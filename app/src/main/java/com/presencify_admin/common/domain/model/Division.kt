package com.presencify_admin.common.domain.model

data class Division(
    val id: Int,
    val divisionCode: String,
    val semesterId: Int,
    val semester: Semester?,
    val batch: Batch?
)