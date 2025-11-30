package com.presencify_admin.home.feature_users.di

import com.presencify_admin.home.feature_users.data.remote.StudentApi
import com.presencify_admin.home.feature_users.data.repository.StudentRepositoryImpl
import com.presencify_admin.home.feature_users.domain.repository.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StudentModule {

    @Provides
    @Singleton
    fun providesStudentApi(retrofit: Retrofit): StudentApi {
        return retrofit.create(StudentApi::class.java)
    }

    @Provides
    @Singleton
    fun providesStudentRepository(api: StudentApi): StudentRepository {
        return StudentRepositoryImpl(api)
    }

}