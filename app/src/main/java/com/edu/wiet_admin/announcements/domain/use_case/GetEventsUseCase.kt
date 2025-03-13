package com.edu.wiet_admin.announcements.domain.use_case

import com.edu.wiet_admin.announcements.domain.repository.EventRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Event
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// EventRepository Use Cases
class GetEventsUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(
        searchQuery: String?,
        startDatetime: String?,
        endDatetime: String?,
        uploadedBy: Int?,
        page: Int = 1,
        limit: Int = 10
    ): Flow<Resource<WietApiResponse<List<Event>>>> {
        return RemoteUtils.responseFlow {
            eventRepository.getEvents(
                searchQuery,
                startDatetime,
                endDatetime,
                uploadedBy,
                page,
                limit
            )
        }
    }
}

