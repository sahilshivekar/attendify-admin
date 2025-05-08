package com.attendify_admin.home.announcements.di

import com.attendify_admin.home.announcements.data.NoticeApi
import com.attendify_admin.home.announcements.data.NoticeRepositoryImpl
import com.attendify_admin.home.announcements.domain.repository.NoticeRepository
import com.attendify_admin.home.announcements.domain.use_case.AddNoticeUseCase
import com.attendify_admin.home.announcements.domain.use_case.DeleteNoticeUseCase
import com.attendify_admin.home.announcements.domain.use_case.GetNoticeByIdUseCase
import com.attendify_admin.home.announcements.domain.use_case.GetNoticesUseCase
import com.attendify_admin.home.announcements.domain.use_case.UpdateNoticeUseCase
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

    @Provides
    fun providesGetNoticesUseCase(repository: NoticeRepository): GetNoticesUseCase {
        return GetNoticesUseCase(repository)
    }

    @Provides
    fun providesGetNoticeByIdUseCase(repository: NoticeRepository): GetNoticeByIdUseCase {
        return GetNoticeByIdUseCase(repository)
    }

    @Provides
    fun providesAddNoticeUseCase(repository: NoticeRepository): AddNoticeUseCase {
        return AddNoticeUseCase(repository)
    }

    @Provides
    fun providesUpdateNoticeUseCase(repository: NoticeRepository): UpdateNoticeUseCase {
        return UpdateNoticeUseCase(repository)
    }

    @Provides
    fun providesDeleteNoticeUseCase(repository: NoticeRepository): DeleteNoticeUseCase {
        return DeleteNoticeUseCase(repository)
    }
}