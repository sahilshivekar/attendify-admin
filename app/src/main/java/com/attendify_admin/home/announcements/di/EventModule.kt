package com.attendify_admin.home.announcements.di

import com.attendify_admin.home.announcements.data.EventApi
import com.attendify_admin.home.announcements.data.EventRepositoryImpl
import com.attendify_admin.home.announcements.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EventModule {

    @Provides
    @Singleton
    fun providesEventApi(retrofit: Retrofit): EventApi {
        return retrofit.create(EventApi::class.java)
    }

    @Provides
    @Singleton
    fun providesEventRepository(api: EventApi): EventRepository {
        return EventRepositoryImpl(api)
    }

}