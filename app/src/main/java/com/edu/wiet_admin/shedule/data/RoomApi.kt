package com.edu.wiet_admin.shedule.data


import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Room
import com.edu.wiet_admin.shedule.data.dto.request.AddRoomRequest
import com.edu.wiet_admin.shedule.data.dto.request.UpdateRoomRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface RoomApi {

    @POST("api/v1/admin/add-room")
    suspend fun addRoom(@Body requestBody: AddRoomRequest): Response<WietApiResponse<Room?>>

    @GET("api/v1/admin/get-rooms")
    suspend fun getRooms(
        @Query("searchQuery") searchQuery: String?,
        @Query("sortBy") sortBy: String,
        @Query("sortOrder") sortOrder: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<WietApiResponse<List<Room?>>>

    @GET("api/v1/admin/get-room-by-id")
    suspend fun getRoomById(@Query("roomId") roomId: String): Response<WietApiResponse<Room?>>

    @PUT("api/v1/admin/update-room")
    suspend fun updateRoom(@Body requestBody: UpdateRoomRequest): Response<WietApiResponse<Room?>>

    @DELETE("api/v1/admin/remove-room")
    suspend fun removeRoom(@Query("roomId") roomId: String): Response<WietApiResponse<String?>>
}