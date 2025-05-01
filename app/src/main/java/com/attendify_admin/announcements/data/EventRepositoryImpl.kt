package com.attendify_admin.announcements.data

import com.attendify_admin.announcements.domain.repository.EventRepository
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Event
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class EventRepositoryImpl(
    private val eventApi: EventApi
) : EventRepository {

    override suspend fun getEvents(
        searchQuery: String?,
        startDatetime: String?,
        endDatetime: String?,
        uploadedBy: Int?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<Event>>> {
        return eventApi.getEvents(searchQuery, startDatetime, endDatetime, uploadedBy, page, limit)
    }

    override suspend fun getEventById(eventId: Int): Response<AttendifyApiResponse<Event>> {
        return eventApi.getEventById(eventId)
    }

    override suspend fun addEvent(
        title: String,
        description: String?,
        location: String?,
        startDatetime: String,
        endDatetime: String,
        registrationLink: String?,
        uploadedBy: Int,
        imageFile: File?
    ): Response<AttendifyApiResponse<Event>> {
        val titleBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody = description?.toRequestBody("text/plain".toMediaTypeOrNull())
        val locationBody = location?.toRequestBody("text/plain".toMediaTypeOrNull())
        val startDatetimeBody = startDatetime.toRequestBody("text/plain".toMediaTypeOrNull())
        val endDatetimeBody = endDatetime.toRequestBody("text/plain".toMediaTypeOrNull())
        val registrationLinkBody = registrationLink?.toRequestBody("text/plain".toMediaTypeOrNull())
        val uploadedByBody = uploadedBy.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart = if (imageFile != null) {
            val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("imageFile", imageFile.name, requestFile)
        } else {
            null
        }

        return eventApi.addEvent(
            titleBody,
            descriptionBody,
            locationBody,
            startDatetimeBody,
            endDatetimeBody,
            registrationLinkBody,
            uploadedByBody,
            imagePart
        )
    }

    override suspend fun updateEvent(
        eventId: Int,
        title: String?,
        description: String?,
        location: String?,
        startDatetime: String?,
        endDatetime: String?,
        registrationLink: String?,
        uploadedBy: Int?,
        imageFile: File?
    ): Response<AttendifyApiResponse<Event>> {
        val eventIdBody = eventId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val titleBody = title?.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody = description?.toRequestBody("text/plain".toMediaTypeOrNull())
        val locationBody = location?.toRequestBody("text/plain".toMediaTypeOrNull())
        val startDatetimeBody = startDatetime?.toRequestBody("text/plain".toMediaTypeOrNull())
        val endDatetimeBody = endDatetime?.toRequestBody("text/plain".toMediaTypeOrNull())
        val registrationLinkBody = registrationLink?.toRequestBody("text/plain".toMediaTypeOrNull())
        val uploadedByBody = uploadedBy?.toString()?.toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart = if (imageFile != null) {
            val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("imageFile", imageFile.name, requestFile)
        } else {
            null
        }

        return eventApi.updateEvent(
            eventIdBody,
            titleBody,
            descriptionBody,
            locationBody,
            startDatetimeBody,
            endDatetimeBody,
            registrationLinkBody,
            uploadedByBody,
            imagePart
        )
    }

    override suspend fun deleteEvent(eventId: Int): Response<AttendifyApiResponse<Unit>> {
        return eventApi.deleteEvent(eventId)
    }
}