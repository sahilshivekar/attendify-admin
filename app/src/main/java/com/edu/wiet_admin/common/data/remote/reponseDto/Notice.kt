package com.edu.wiet_admin.common.data.remote.reponseDto

data class Notice(
    val id: Int,
    val title: String,
    val audiences: String,
    val description: String,
    val imageFilePublicId: String,
    val imageFileUrl: String,
    val isPinned: Boolean,
    val uploadedBy: Int,
    val Staff: Staff?,
    val createdAt: String,
    val updatedAt: String,
)