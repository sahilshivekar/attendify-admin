package com.presencify_admin.common.domain.model

data class Attendance(
    val id: Int,
    val date: String,
    val classId: Int,
    val classObj: Class?,
    val bleSessionUUID: String?
)