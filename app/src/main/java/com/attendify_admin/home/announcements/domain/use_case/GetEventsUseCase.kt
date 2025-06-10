package com.attendify_admin.home.announcements.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Event
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.announcements.domain.repository.EventRepository
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
    ): Flow<Resource<AttendifyApiResponse<List<Event>>>> {
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

