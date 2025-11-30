package com.presencify_admin.home.feature_academics.di

import com.presencify_admin.home.feature_academics.data.remote.DivisionApi
import com.presencify_admin.home.feature_academics.data.repository.DivisionRepositoryImpl
import com.presencify_admin.home.feature_academics.domain.repository.DivisionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DivisionModule {

    @Provides
    @Singleton
    fun providesDivisionApi(retrofit: Retrofit): DivisionApi {
        return retrofit.create(DivisionApi::class.java)
    }

    @Provides
    @Singleton
    fun providesDivisionRepository(api: DivisionApi): DivisionRepository {
        return DivisionRepositoryImpl(api)
    }

}