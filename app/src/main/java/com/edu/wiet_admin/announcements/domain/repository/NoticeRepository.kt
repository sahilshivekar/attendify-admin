package com.edu.wiet_admin.announcements.domain.repository

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Notice
import retrofit2.Response
import java.io.File

interface NoticeRepository {

    // Get all notices
    suspend fun getNotices(
        searchQuery: String?,
        audiences: String?,
        addedAfter: String?,
        addedBefore: String?,
        uploadedBy: Int?,
        page: Int = 1,
        limit: Int = 10
    ): Response<WietApiResponse<List<Notice>>>

    // Get notice by ID
    suspend fun getNoticeById(noticeId: Int): Response<WietApiResponse<Notice>>

    // Add a notice
    suspend fun addNotice(
        title: String,
        description: String?,
        uploadedBy: Int,
        audiences: String,
        isPinned: Boolean,
        imageFile: File?
    ): Response<WietApiResponse<Notice>>

    // Update a notice
    suspend fun updateNotice(
        noticeId: Int,
        title: String?,
        description: String?,
        uploadedBy: Int?,
        audiences: String?,
        isPinned: Boolean?,
        imageFile: File?
    ): Response<WietApiResponse<Notice>>

    // Delete a notice
    suspend fun deleteNotice(noticeId: Int): Response<WietApiResponse<Unit>>
}