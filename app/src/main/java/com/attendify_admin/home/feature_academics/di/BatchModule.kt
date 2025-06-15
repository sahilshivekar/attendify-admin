package com.attendify_admin.home.feature_academics.di

import com.attendify_admin.home.feature_academics.data.BatchApi
import com.attendify_admin.home.feature_academics.data.BatchRepositoryImpl
import com.attendify_admin.home.feature_academics.domain.repository.BatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BatchModule {

    @Provides
    @Singleton
    fun providesBatchApi(retrofit: Retrofit): BatchApi {
        return retrofit.create(BatchApi::class.java)
    }

    @Provides
    @Singleton
    fun providesBatchRepository(api: BatchApi): BatchRepository {
        return BatchRepositoryImpl(api)
    }
}