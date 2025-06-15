package com.attendify_admin.common.domain.model

data class Scheme(
    val id: Int,
    val name: String,
    val universityId: Int,
    val university: University?
)