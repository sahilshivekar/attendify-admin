package com.attendify_admin.announcements.domain.use_case

import com.attendify_admin.announcements.domain.repository.EventRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Event
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventByIdUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(eventId: Int): Flow<Resource<AttendifyApiResponse<Event>>> {
        return RemoteUtils.responseFlow { eventRepository.getEventById(eventId) }
    }
}