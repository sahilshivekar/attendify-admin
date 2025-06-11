package com.attendify_admin.home.shedule.di

import com.attendify_admin.home.shedule.data.AttendanceApi
import com.attendify_admin.home.shedule.data.AttendanceRepositoryImpl
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
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