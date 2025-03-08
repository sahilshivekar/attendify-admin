package com.edu.wiet_admin.common.data.remote.reponseDto

data class Division(
    val id: Int,
    val divisionCode: String,
    val semesterId: Int,
    val Semester: Semester?,
    val Batch: Batch?,
    val createdAt: String,
    val updatedAt: String,
)