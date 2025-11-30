package com.presencify_admin.common.domain.model

data class Batch(
    val id: Int,
    val batchCode: String,
    val divisionId: Int,
    val division: Division?
)