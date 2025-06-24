package com.attendify_admin.home.feature_schedule.data.remote


import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.RoomDto
import com.attendify_admin.common.data.remote.dto.response.RoomListWithTotalCountDto
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddRoomRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.UpdateRoomRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface RoomApi {

    @POST("api/v1/room/admin/add-room")
    suspend fun addRoom(@Body requestBody: AddRoomRequest): Response<AttendifyApiResponse<RoomDto?>>

    @GET("api/v1/room/admin/get-rooms")
    suspend fun getRooms(
        @Query("searchQuery") searchQuery: String?,
        @Query("sortBy") sortBy: String,
        @Query("sortOrder") sortOrder: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("getAll") getAll: Boolean,
    ): Response<AttendifyApiResponse<RoomListWithTotalCountDto>>


    @GET("api/v1/room/admin/get-room-by-id")
    suspend fun getRoomById(@Query("roomId") roomId: Int): Response<AttendifyApiResponse<RoomDto?>>

    @PUT("api/v1/room/admin/update-room")
    suspend fun updateRoom(@Body requestBody: UpdateRoomRequest): Response<AttendifyApiResponse<RoomDto?>>

    @DELETE("api/v1/room/admin/remove-room")
    suspend fun removeRoom(@Query("roomId") roomId: Int): Response<AttendifyApiResponse<String?>>
}