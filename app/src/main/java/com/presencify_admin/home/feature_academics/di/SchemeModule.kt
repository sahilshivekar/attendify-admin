package com.presencify_admin.home.feature_academics.di

import com.presencify_admin.home.feature_academics.data.remote.SchemeApi
import com.presencify_admin.home.feature_academics.data.repository.SchemeRepositoryImpl
import com.presencify_admin.home.feature_academics.domain.repository.SchemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SchemeModule {

    @Provides
    @Singleton
    fun providesSchemeApi(retrofit: Retrofit): SchemeApi {
        return retrofit.create(SchemeApi::class.java)
    }

    @Provides
    @Singleton
    fun providesSchemeRepository(api: SchemeApi): SchemeRepository {
        return SchemeRepositoryImpl(api)
    }

}