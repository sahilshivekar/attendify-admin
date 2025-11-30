package com.presencify_admin.home.feature_schedule.di

import com.presencify_admin.home.feature_schedule.data.remote.AttendanceApi
import com.presencify_admin.home.feature_schedule.data.repository.AttendanceRepositoryImpl
import com.presencify_admin.home.feature_schedule.domain.repository.AttendanceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AttendanceModule {

    @Provides
    @Singleton
    fun providesAttendanceApi(retrofit: Retrofit): AttendanceApi {
        return retrofit.create(AttendanceApi::class.java)
    }

    @Provides
    @Singleton
    fun providesAttendanceRepository(api: AttendanceApi): AttendanceRepository {
        return AttendanceRepositoryImpl(api)
    }

}