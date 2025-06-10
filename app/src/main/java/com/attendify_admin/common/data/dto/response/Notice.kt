package com.attendify_admin.common.data.dto.response

data class Notice(
    val id: Int,
    val title: String,
    val audiences: String,
    val description: String,
    val imageFilePublicId: Int,
    val imageFileUrl: String,
    val isPinned: Boolean,
    val uploadedBy: Int,
    val Staff: Staff?,
    val createdAt: String,
    val updatedAt: String,
)