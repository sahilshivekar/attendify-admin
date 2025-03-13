package com.edu.wiet_admin.announcements.domain.use_case

import com.edu.wiet_admin.announcements.domain.repository.NoticeRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Notice
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// NoticeRepository Use Cases
class GetNoticesUseCase @Inject constructor(private val noticeRepository: NoticeRepository) {
    operator fun invoke(
        searchQuery: String?,
        audiences: String?,
        addedAfter: String?,
        addedBefore: String?,
        uploadedBy: Int?,
        page: Int = 1,
        limit: Int = 10
    ): Flow<Resource<WietApiResponse<List<Notice>>>> {
        return RemoteUtils.responseFlow {
            noticeRepository.getNotices(
                searchQuery,
                audiences,
                addedAfter,
                addedBefore,
                uploadedBy,
                page,
                limit
            )
        }
    }
}