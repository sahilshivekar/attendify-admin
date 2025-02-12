package com.edu.wiet_admin.common.data.remote

import com.edu.wiet_admin.admin_auth.data.remote.GetAccessTokenRequest
import com.edu.wiet_admin.admin_auth.data.remote.responses.GetAccessRefreshTokenData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ResolveUnauthorizedApi {

    @POST("api/v1/admin/get-access-token")
    suspend fun getAccessToken(@Body requestBody: GetAccessTokenRequest): Response<WietApiResponse<GetAccessRefreshTokenData?>>

}

