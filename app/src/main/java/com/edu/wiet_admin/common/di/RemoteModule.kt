package com.edu.wiet_admin.common.di

import com.edu.wiet_admin.admin_auth.data.remote.AuthApi
import com.edu.wiet_admin.admin_auth.data.repository.AuthRepositoryImpl
import com.edu.wiet_admin.admin_auth.domain.repository.AuthRepository
import com.edu.wiet_admin.admin_auth.domain.use_case.GetAccessRefreshTokenUseCase
import com.edu.wiet_admin.common.Constants.BASE_URL
import com.edu.wiet_admin.common.data.remote.AuthInterceptor
import com.edu.wiet_admin.common.data.remote.ResolveUnauthorized
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS) // Connection timeout (handshake)
            .readTimeout(120, TimeUnit.SECONDS)    // Socket timeout (data transfer)
            .writeTimeout(120, TimeUnit.SECONDS)   // Write timeout (sending request body)
            .callTimeout(120, TimeUnit.SECONDS)    // Call timeout (total request time)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideResolveUnauthorized(): ResolveUnauthorized {
        val retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ResolveUnauthorized::class.java)
    }




}