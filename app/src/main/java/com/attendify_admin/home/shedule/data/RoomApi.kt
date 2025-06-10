package com.attendify_admin.home.shedule.data


import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Room
import com.attendify_admin.home.shedule.data.dto.request.AddRoomRequest
import com.attendify_admin.home.shedule.data.dto.request.UpdateRoomRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface RoomApi {

    @POST("api/v1/room/admin/add-room")
    suspend fun addRoom(@Body requestBody: AddRoomRequest): Response<AttendifyApiResponse<Room?>>

    @GET("api/v1/room/admin/get-rooms")
    suspend fun getRooms(
        @Query("searchQuery") searchQuery: String?,
        @Query("sortBy") sortBy: String,
        @Query("sortOrder") sortOrder: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<List<Room?>>>

    @GET("api/v1/room/admin/get-room-by-id")
    suspend fun getRoomById(@Query("roomId") roomId: Int): Response<AttendifyApiResponse<Room?>>

    @PUT("api/v1/room/admin/update-room")
    suspend fun updateRoom(@Body requestBody: UpdateRoomRequest): Response<AttendifyApiResponse<Room?>>

    @DELETE("api/v1/room/admin/remove-room")
    suspend fun removeRoom(@Query("roomId") roomId: Int): Response<AttendifyApiResponse<String?>>
}