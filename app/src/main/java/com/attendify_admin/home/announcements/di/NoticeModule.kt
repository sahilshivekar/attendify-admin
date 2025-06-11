package com.attendify_admin.home.announcements.di

import com.attendify_admin.home.announcements.data.NoticeApi
import com.attendify_admin.home.announcements.data.NoticeRepositoryImpl
import com.attendify_admin.home.announcements.domain.repository.NoticeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NoticeModule {

    @Provides
    fun providesNoticeApi(retrofit: Retrofit): NoticeApi {
        return retrofit.create(NoticeApi::class.java)
    }

    @Provides
    fun providesNoticeRepository(api: NoticeApi): NoticeRepository {
        return NoticeRepositoryImpl(api)
    }

}