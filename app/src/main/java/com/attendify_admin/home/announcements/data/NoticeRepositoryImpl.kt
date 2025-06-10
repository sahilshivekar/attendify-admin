package com.attendify_admin.home.announcements.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Notice
import com.attendify_admin.home.announcements.domain.repository.NoticeRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class NoticeRepositoryImpl(
    private val noticeApi: NoticeApi
) : NoticeRepository {

    override suspend fun getNotices(
        searchQuery: String?,
        audiences: String?,
        addedAfter: String?,
        addedBefore: String?,
        uploadedBy: Int?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<Notice>>> {
        return noticeApi.getNotices(
            searchQuery,
            audiences,
            addedAfter,
            addedBefore,
            uploadedBy,
            page,
            limit
        )
    }

    override suspend fun getNoticeById(noticeId: Int): Response<AttendifyApiResponse<Notice>> {
        return noticeApi.getNoticeById(noticeId)
    }

    override suspend fun addNotice(
        title: String,
        description: String?,
        uploadedBy: Int,
        audiences: String,
        isPinned: Boolean,
        imageFile: File?
    ): Response<AttendifyApiResponse<Notice>> {
        val titleBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody = description?.toRequestBody("text/plain".toMediaTypeOrNull())
        val uploadedByBody = uploadedBy.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val audiencesBody = audiences.toRequestBody("text/plain".toMediaTypeOrNull())
        val isPinnedBody = isPinned.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart = if (imageFile != null) {
            val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("imageFile", imageFile.name, requestFile)
        } else {
            null
        }

        return noticeApi.addNotice(
            titleBody,
            descriptionBody,
            uploadedByBody,
            audiencesBody,
            isPinnedBody,
            imagePart
        )
    }

    override suspend fun updateNotice(
        noticeId: Int,
        title: String?,
        description: String?,
        uploadedBy: Int?,
        audiences: String?,
        isPinned: Boolean?,
        imageFile: File?
    ): Response<AttendifyApiResponse<Notice>> {
        val noticeIdBody = noticeId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val titleBody = title?.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody = description?.toRequestBody("text/plain".toMediaTypeOrNull())
        val uploadedByBody = uploadedBy?.toString()?.toRequestBody("text/plain".toMediaTypeOrNull())
        val audiencesBody = audiences?.toRequestBody("text/plain".toMediaTypeOrNull())
        val isPinnedBody = isPinned?.toString()?.toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart = if (imageFile != null) {
            val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("imageFile", imageFile.name, requestFile)
        } else {
            null
        }

        return noticeApi.updateNotice(
            noticeIdBody,
            titleBody,
            descriptionBody,
            uploadedByBody,
            audiencesBody,
            isPinnedBody,
            imagePart
        )
    }

    override suspend fun deleteNotice(noticeId: Int): Response<AttendifyApiResponse<Unit>> {
        return noticeApi.deleteNotice(noticeId)
    }
}