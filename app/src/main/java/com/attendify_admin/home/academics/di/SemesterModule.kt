package com.attendify_admin.home.academics.di

import com.attendify_admin.home.academics.data.SemesterApi
import com.attendify_admin.home.academics.data.SemesterRepositoryImpl
import com.attendify_admin.home.academics.domain.repository.SemesterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SemesterModule {

    @Provides
    @Singleton
    fun providesSemesterApi(retrofit: Retrofit): SemesterApi {
        return retrofit.create(SemesterApi::class.java)
    }

    @Provides
    @Singleton
    fun providesSemesterRepository(api: SemesterApi): SemesterRepository {
        return SemesterRepositoryImpl(api)
    }

}