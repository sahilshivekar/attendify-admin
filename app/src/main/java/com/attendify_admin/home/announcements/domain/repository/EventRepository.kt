package com.attendify_admin.home.announcements.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.Event
import retrofit2.Response
import java.io.File

interface EventRepository {

    // Get all events
    suspend fun getEvents(
        searchQuery: String?,
        startDatetime: String?,
        endDatetime: String?,
        uploadedBy: Int?,
        page: Int = 1,
        limit: Int = 10
    ): Response<AttendifyApiResponse<List<Event>>>

    // Get event by ID
    suspend fun getEventById(eventId: Int): Response<AttendifyApiResponse<Event>>

    // Add an event
    suspend fun addEvent(
        title: String,
        description: String?,
        location: String?,
        startDatetime: String,
        endDatetime: String,
        registrationLink: String?,
        uploadedBy: Int,
        imageFile: File?
    ): Response<AttendifyApiResponse<Event>>


    // Update an event
    suspend fun updateEvent(
        eventId: Int,
        title: String?,
        description: String?,
        location: String?,
        startDatetime: String?,
        endDatetime: String?,
        registrationLink: String?,
        uploadedBy: Int?,
        imageFile: File?
    ): Response<AttendifyApiResponse<Event>>

    // Delete an event
    suspend fun deleteEvent(eventId: Int): Response<AttendifyApiResponse<Unit>>
}