package com.edu.wiet_admin.announcements.domain.use_case

import com.edu.wiet_admin.announcements.domain.repository.NoticeRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Notice
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoticeByIdUseCase @Inject constructor(private val noticeRepository: NoticeRepository) {
    operator fun invoke(noticeId: Int): Flow<Resource<WietApiResponse<Notice>>> {
        return RemoteUtils.responseFlow { noticeRepository.getNoticeById(noticeId) }
    }
}


