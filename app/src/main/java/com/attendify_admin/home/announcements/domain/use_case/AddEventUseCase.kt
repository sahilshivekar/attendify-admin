package com.attendify_admin.home.announcements.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Event
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.announcements.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

// AddEventUseCase
class AddEventUseCase @Inject constructor(private val eventRepository: EventRepository) {
    operator fun invoke(
        title: String,
        description: String?,
        location: String?,
        startDatetime: String,
        endDatetime: String,
        registrationLink: String?,
        uploadedBy: Int,
        imageFile: File?
    ): Flow<Resource<AttendifyApiResponse<Event>>> {
        return RemoteUtils.responseFlow {
            eventRepository.addEvent(
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