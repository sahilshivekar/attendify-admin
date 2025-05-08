package com.attendify_admin.home.announcements.domain.use_case

import com.attendify_admin.home.announcements.domain.repository.NoticeRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Notice
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

// UpdateNoticeUseCase
class UpdateNoticeUseCase @Inject constructor(private val noticeRepository: NoticeRepository) {
    operator fun invoke(
        noticeId: Int,
        title: String?,
        description: String?,
        uploadedBy: Int?,
        audiences: String?,
        isPinned: Boolean?,
        imageFile: File?
    ): Flow<Resource<AttendifyApiResponse<Notice>>> {
        return RemoteUtils.responseFlow {
            noticeRepository.updateNotice(
                noticeId,
                title,
                description,
                uploadedBy,
                audiences,
                isPinned,
                imageFile
            )
        }
    }
}