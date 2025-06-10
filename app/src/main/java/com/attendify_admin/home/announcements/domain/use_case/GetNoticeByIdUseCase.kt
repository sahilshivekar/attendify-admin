package com.attendify_admin.home.announcements.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Notice
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.announcements.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticeByIdUseCase @Inject constructor(private val noticeRepository: NoticeRepository) {
    operator fun invoke(noticeId: Int): Flow<Resource<AttendifyApiResponse<Notice>>> {
        return RemoteUtils.responseFlow { noticeRepository.getNoticeById(noticeId) }
    }
}


