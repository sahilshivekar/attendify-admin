package com.attendify_admin.home.announcements.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.Notice
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

interface NoticeApi {

    @GET("api/v1/notice/admin/get-notices")
    suspend fun getNotices(
        @Query("searchQuery") searchQuery: String?,
        @Query("audiences") audiences: String?,
        @Query("addedAfter") addedAfter: String?,
        @Query("addedBefore") addedBefore: String?,
        @Query("uploadedBy") uploadedBy: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AttendifyApiResponse<List<Notice>>>

    @GET("api/v1/notice/admin/get-notice-by-id")
    suspend fun getNoticeById(@Query("noticeId") noticeId: Int): Response<AttendifyApiResponse<Notice>>

    @Multipart
    @POST("api/v1/notice/admin/add-notice")
    suspend fun addNotice(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody?,
        @Part("uploadedBy") uploadedBy: RequestBody,
        @Part("audiences") audiences: RequestBody,
        @Part("isPinned") isPinned: RequestBody,
        @Part imageFile: MultipartBody.Part?
    ): Response<AttendifyApiResponse<Notice>>

    @Multipart
    @PUT("api/v1/notice/admin/update-notice")
    suspend fun updateNotice(
        @Part("noticeId") noticeId: RequestBody,
        @Part("title") title: RequestBody?,
        @Part("description") description: RequestBody?,
        @Part("uploadedBy") uploadedBy: RequestBody?,
        @Part("audiences") audiences: RequestBody?,
        @Part("isPinned") isPinned: RequestBody?,
        @Part imageFile: MultipartBody.Part?
    ): Response<AttendifyApiResponse<Notice>>

    @DELETE("api/v1/notice/admin/delete-notice")
    suspend fun deleteNotice(@Query("noticeId") noticeId: Int): Response<AttendifyApiResponse<Unit>>
}