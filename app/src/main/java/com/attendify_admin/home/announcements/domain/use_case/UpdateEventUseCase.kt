package com.attendify_admin.home.announcements.domain.use_case

import com.attendify_admin.home.announcements.domain.repository.EventRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Event
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

// UpdateEventUseCase
class UpdateEventUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(
        eventId: Int,
        title: String?,
        description: String?,
        location: String?,
        startDatetime: String?,
        endDatetime: String?,
        registrationLink: String?,
        uploadedBy: Int?,
        imageFile: File?
    ): Flow<Resource<AttendifyApiResponse<Event>>> {
        return RemoteUtils.responseFlow {
            eventRepository.updateEvent(
                eventId,
                title,
                description,
                location,
                startDatetime,
                endDatetime,
                registrationLink,
                uploadedBy,
                imageFile
            )
        }
    }
}