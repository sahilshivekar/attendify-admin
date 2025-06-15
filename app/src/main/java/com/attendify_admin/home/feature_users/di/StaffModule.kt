package com.attendify_admin.home.feature_users.di

import com.attendify_admin.home.feature_users.data.StaffApi
import com.attendify_admin.home.feature_users.data.StaffRepositoryImpl
import com.attendify_admin.home.feature_users.domain.repository.StaffRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StaffModule {

    @Provides
    @Singleton
    fun providesStaffApi(retrofit: Retrofit): StaffApi {
        return retrofit.create(StaffApi::class.java)
    }

    @Provides
    @Singleton
    fun providesStaffRepository(api: StaffApi): StaffRepository {
        return StaffRepositoryImpl(api)
    }

}