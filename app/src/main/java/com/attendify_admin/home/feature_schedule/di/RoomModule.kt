package com.attendify_admin.home.feature_schedule.di

import com.attendify_admin.home.feature_schedule.data.RoomApi
import com.attendify_admin.home.feature_schedule.data.RoomRepositoryImpl
import com.attendify_admin.home.feature_schedule.domain.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun providesRoomApi(retrofit: Retrofit): RoomApi {
        return retrofit.create(RoomApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRoomRepository(api: RoomApi): RoomRepository {
        return RoomRepositoryImpl(api)
    }

}