package com.attendify_admin.home.shedule.di

import com.attendify_admin.home.shedule.data.TimetableApi
import com.attendify_admin.home.shedule.data.TimetableRepositoryImpl
import com.attendify_admin.home.shedule.domain.repository.TimetableRepository
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