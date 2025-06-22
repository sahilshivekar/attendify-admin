package com.attendify_admin.home.feature_academics.di

import com.attendify_admin.home.feature_academics.data.remote.CourseApi
import com.attendify_admin.home.feature_academics.data.repository.CourseRepositoryImpl
import com.attendify_admin.home.feature_academics.domain.repository.CourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CourseModule {

    @Provides
    @Singleton
    fun providesCourseApi(retrofit: Retrofit): CourseApi {
        return retrofit.create(CourseApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCourseRepository(api: CourseApi): CourseRepository {
        return CourseRepositoryImpl(api)
    }

}