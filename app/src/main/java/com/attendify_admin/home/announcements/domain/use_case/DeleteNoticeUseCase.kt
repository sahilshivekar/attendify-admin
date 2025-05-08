package com.attendify_admin.home.announcements.domain.use_case

import com.attendify_admin.home.announcements.domain.repository.NoticeRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteNoticeUseCase @Inject constructor(private val noticeRepository: NoticeRepository) {
    operator fun invoke(noticeId: Int): Flow<Resource<AttendifyApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { noticeRepository.deleteNotice(noticeId) }
    }
}