package com.attendify_admin.home.feature_schedule.di

import com.attendify_admin.home.feature_schedule.data.remote.ClassApi
import com.attendify_admin.home.feature_schedule.data.repository.ClassRepositoryImpl
import com.attendify_admin.home.feature_schedule.domain.repository.ClassRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ClassModule {

    @Provides
    @Singleton
    fun providesClassApi(retrofit: Retrofit): ClassApi {
        return retrofit.create(ClassApi::class.java)
    }

    @Provides
    @Singleton
    fun providesClassRepository(api: ClassApi): ClassRepository {
        return ClassRepositoryImpl(api)
    }

}