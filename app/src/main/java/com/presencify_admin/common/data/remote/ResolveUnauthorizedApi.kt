package com.presencify_admin.common.data.remote

import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.presencify_admin.feature_admin_auth.data.remote.dto.responses.GetAccessRefreshTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ResolveUnauthorizedApi {

    @POST("api/v1/admin/admin/get-access-token")
    suspend fun getAccessToken(@Body requestBody: GetAccessTokenRequest): Response<PresencifyApiResponse<GetAccessRefreshTokenDto?>>

}

