package com.presencify_admin.feature_admin_mgt.di

import com.presencify_admin.feature_admin_mgt.data.remote.AdminMgtApi
import com.presencify_admin.feature_admin_mgt.data.repository.AdminMgtRepositoryImpl
import com.presencify_admin.feature_admin_mgt.domain.repository.AdminMgtRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AdminModule {

    @Provides
    @Singleton
    fun provideAdminMgtApi(
        retrofit: Retrofit
    ): AdminMgtApi {
        return retrofit.create(AdminMgtApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAdminMgtRepository(
        adminMgtApi: AdminMgtApi
    ): AdminMgtRepository {
        return AdminMgtRepositoryImpl(adminMgtApi)
    }
}