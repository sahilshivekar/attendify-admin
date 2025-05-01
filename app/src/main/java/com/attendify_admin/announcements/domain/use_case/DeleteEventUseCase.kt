package com.attendify_admin.announcements.domain.use_case

import com.attendify_admin.announcements.domain.repository.EventRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteEventUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(eventId: Int): Flow<Resource<AttendifyApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { eventRepository.deleteEvent(eventId) }
    }
}