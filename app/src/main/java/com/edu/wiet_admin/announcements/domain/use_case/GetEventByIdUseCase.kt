package com.edu.wiet_admin.announcements.domain.use_case

import com.edu.wiet_admin.announcements.domain.repository.EventRepository
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Event
import com.edu.wiet_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventByIdUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(eventId: Int): Flow<Resource<WietApiResponse<Event>>> {
        return RemoteUtils.responseFlow { eventRepository.getEventById(eventId) }
    }
}