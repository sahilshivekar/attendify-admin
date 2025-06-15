package com.attendify_admin.common.data.remote

import android.util.Log
import com.attendify_admin.feature_admin_auth.data.remote.dto.request.GetAccessTokenRequest
import com.attendify_admin.feature_admin_auth.domain.use_case.ReadAccessTokenUseCase
import com.attendify_admin.feature_admin_auth.domain.use_case.ReadRefreshTokenUseCase
import com.attendify_admin.feature_admin_auth.domain.use_case.RemoveAuthTokensUseCase
import com.attendify_admin.feature_admin_auth.domain.use_case.SaveAccessTokenUseCase
import com.attendify_admin.feature_admin_auth.domain.use_case.SaveRefreshTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val readAccessTokenUseCase: ReadAccessTokenUseCase,
    private val readRefreshTokenUseCase: ReadRefreshTokenUseCase,
    private val removeAuthTokensUseCase: RemoveAuthTokensUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val resolveUnauthorized: ResolveUnauthorizedApi
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = runBlocking {
            readAccessTokenUseCase().first()
        }
        val request = chain.request().newBuilder().apply {
            accessToken?.let {
                header("Authorization", accessToken)
            }
        }.build()

        var response = chain.proceed(request)

        if (response.code == 401) {
            response.close()
            //following fun will get new access token with help of refresh token
            getNewTokens()
            val newAccessToken = runBlocking {
                readAccessTokenUseCase().first()
            }
            val newRequest = chain.request().newBuilder().apply {
                newAccessToken?.let {
                    header("Authorization", it)
                }
            }.build()
            response = chain.proceed(newRequest)
        }


        return response
    }

    private fun getNewTokens() {

        // case1 - if the access token get expired during request we will always have the refresh token as refresh token is 15d long
        // case2 - no refresh token at start directly redirected to login screen nothing to worry
        val refreshToken = runBlocking {
            readRefreshTokenUseCase().first()
        }

        refreshToken?.let {
            runBlocking {
                try {
                    val response = resolveUnauthorized.getAccessToken(
                        requestBody = GetAccessTokenRequest(refreshToken)
                    )

                    if (response.isSuccessful) {
                        Log.d("AuthInterceptor", "Got new tokens")
                        val accessTokenData = response.body()?.data
                        accessTokenData?.let {
                            saveAccessTokenUseCase(it.accessToken)
                            saveRefreshTokenUseCase(it.refreshToken)
                        }
                    } else {
                        Log.e("API Error", response.errorBody()?.string() ?: "Unknown error")
                        removeAuthTokensUseCase() // so that next time the user opens app he will be navigated to the login screen
                    }


                } catch (e: Exception) {
                    // Handle network or other exceptions
                    Log.e("Auth Interceptor Error", "Error: ${e.message}")
                }
            }
        }
    }

}