package com.edu.wiet_admin.common.data.remote.reponseDto

data class Scheme(
    val id: Int,
    val name: String,
    val universityId: Int,
    val University : University?,
    val createdAt: String,
    val updatedAt: String,
)