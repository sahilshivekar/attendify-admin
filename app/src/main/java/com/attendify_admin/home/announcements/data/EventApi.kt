package com.attendify_admin.home.announcements.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Event
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface EventApi {

    @GET("api/v1/event/admin/get-events")
    suspend fun getEvents(
        @Query("searchQuery") searchQuery: String?,
        @Query("startDatetime") startDatetime: String?,
        @Query("endDatetime") endDatetime: String?,
        @Query("uploadedBy") uploadedBy: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<List<Event>>>

    @GET("api/v1/event/admin/get-event-by-id")
    suspend fun getEventById(@Query("eventId") eventId: Int): Response<AttendifyApiResponse<Event>>

    @Multipart
    @POST("api/v1/event/admin/add-event")
    suspend fun addEvent(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody?,
        @Part("location") location: RequestBody?,
        @Part("startDatetime") startDatetime: RequestBody,
        @Part("endDatetime") endDatetime: RequestBody,
        @Part("registrationLink") registrationLink: RequestBody?,
        @Part("uploadedBy") uploadedBy: RequestBody,
        @Part imageFile: MultipartBody.Part?
    ): Response<AttendifyApiResponse<Event>>

    @Multipart
    @PUT("api/v1/event/admin/update-event")
    suspend fun updateEvent(
        @Part("eventId") eventId: RequestBody,
        @Part("title") title: RequestBody?,
        @Part("description") description: RequestBody?,
        @Part("location") location: RequestBody?,
        @Part("startDatetime") startDatetime: RequestBody?,
        @Part("endDatetime") endDatetime: RequestBody?,
        @Part("registrationLink") registrationLink: RequestBody?,
        @Part("uploadedBy") uploadedBy: RequestBody?,
        @Part imageFile: MultipartBody.Part?
    ): Response<AttendifyApiResponse<Event>>

    @DELETE("api/v1/event/admin/delete-event")
    suspend fun deleteEvent(@Query("eventId") eventId: Int): Response<AttendifyApiResponse<Unit>>
}