package com.attendify_admin.home.academics.di

import com.attendify_admin.home.academics.data.BranchApi
import com.attendify_admin.home.academics.data.BranchRepositoryImpl
import com.attendify_admin.home.academics.domain.repository.BranchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BranchModule {

    @Provides
    @Singleton
    fun providesBranchApi(retrofit: Retrofit): BranchApi {
        return retrofit.create(BranchApi::class.java)
    }

    @Provides
    @Singleton
    fun providesBranchRepository(api: BranchApi): BranchRepository {
        return BranchRepositoryImpl(api)
    }

}