package com.edu.wiet_admin.admin_auth.di

import android.content.Context
import com.edu.wiet_admin.admin_auth.data.remote.AuthApi
import com.edu.wiet_admin.admin_auth.data.repository.AuthRepositoryImpl
import com.edu.wiet_admin.admin_auth.data.repository.TokenRepositoryImpl
import com.edu.wiet_admin.admin_auth.domain.repository.AuthRepository
import com.edu.wiet_admin.admin_auth.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi = retrofit.create(AuthApi::class.java)


    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi
    ): AuthRepository = AuthRepositoryImpl(authApi)


    @Provides
    @Singleton
    fun provideTokenRepository(
        @ApplicationContext context: Context
    ): TokenRepository = TokenRepositoryImpl(context)

}