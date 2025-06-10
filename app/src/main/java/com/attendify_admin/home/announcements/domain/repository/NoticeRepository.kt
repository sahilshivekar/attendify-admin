package com.attendify_admin.home.announcements.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.Notice
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
    ): Response<AttendifyApiResponse<List<Notice>>>

    // Get notice by ID
    suspend fun getNoticeById(noticeId: Int): Response<AttendifyApiResponse<Notice>>

    // Add a notice
    suspend fun addNotice(
        title: String,
        description: String?,
        uploadedBy: Int,
        audiences: String,
        isPinned: Boolean,
        imageFile: File?
    ): Response<AttendifyApiResponse<Notice>>

    // Update a notice
    suspend fun updateNotice(
        noticeId: Int,
        title: String?,
        description: String?,
        uploadedBy: Int?,
        audiences: String?,
        isPinned: Boolean?,
        imageFile: File?
    ): Response<AttendifyApiResponse<Notice>>

    // Delete a notice
    suspend fun deleteNotice(noticeId: Int): Response<AttendifyApiResponse<Unit>>
}