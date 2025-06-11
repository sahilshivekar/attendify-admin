package com.attendify_admin.home.academics.di

import com.attendify_admin.home.academics.data.UniversityApi
import com.attendify_admin.home.academics.data.UniversityRepositoryImpl
import com.attendify_admin.home.academics.domain.repository.UniversityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UniversityModule {

    @Provides
    @Singleton
    fun providesUniversityApi(retrofit: Retrofit): UniversityApi {
        return retrofit.create(UniversityApi::class.java)
    }

    @Provides
    @Singleton
    fun providesUniversityRepository(api: UniversityApi): UniversityRepository {
        return UniversityRepositoryImpl(api)
    }

}