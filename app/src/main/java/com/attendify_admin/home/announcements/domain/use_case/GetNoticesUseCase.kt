package com.attendify_admin.home.announcements.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Notice
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.announcements.domain.repository.NoticeRepository
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
    ): Flow<Resource<AttendifyApiResponse<List<Notice>>>> {
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