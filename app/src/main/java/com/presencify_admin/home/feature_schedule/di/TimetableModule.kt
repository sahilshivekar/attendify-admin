package com.presencify_admin.home.feature_schedule.di

import com.presencify_admin.home.feature_schedule.data.remote.TimetableApi
import com.presencify_admin.home.feature_schedule.data.repository.TimetableRepositoryImpl
import com.presencify_admin.home.feature_schedule.domain.repository.TimetableRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TimetableModule {

    @Provides
    @Singleton
    fun providesTimetableApi(retrofit: Retrofit): TimetableApi {
        return retrofit.create(TimetableApi::class.java)
    }

    @Provides
    @Singleton
    fun providesTimetableRepository(api: TimetableApi): TimetableRepository {
        return TimetableRepositoryImpl(api)
    }

}