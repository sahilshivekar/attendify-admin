package com.attendify_admin.academics.di

import com.attendify_admin.academics.data.SemesterApi
import com.attendify_admin.academics.data.SemesterRepositoryImpl
import com.attendify_admin.academics.domain.repository.SemesterRepository
import com.attendify_admin.academics.domain.use_case.AddSemesterUseCase
import com.attendify_admin.academics.domain.use_case.GetCoursesOfSemesterUseCase
import com.attendify_admin.academics.domain.use_case.GetSemesterByIdUseCase
import com.attendify_admin.academics.domain.use_case.GetSemestersUseCase
import com.attendify_admin.academics.domain.use_case.RemoveSemesterUseCase
import com.attendify_admin.academics.domain.use_case.UpdateSemesterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SemesterModule {

    @Provides
    fun providesSemesterApi(retrofit: Retrofit): SemesterApi {
        return retrofit.create(SemesterApi::class.java)
    }

    @Provides
    fun providesSemesterRepository(api: SemesterApi): SemesterRepository {
        return SemesterRepositoryImpl(api)
    }

    @Provides
    fun providesAddSemesterUseCase(repository: SemesterRepository): AddSemesterUseCase {
        return AddSemesterUseCase(repository)
    }

    @Provides
    fun providesGetSemestersUseCase(repository: SemesterRepository): GetSemestersUseCase {
        return GetSemestersUseCase(repository)
    }

    @Provides
    fun providesGetSemesterByIdUseCase(repository: SemesterRepository): GetSemesterByIdUseCase {
        return GetSemesterByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateSemesterUseCase(repository: SemesterRepository): UpdateSemesterUseCase {
        return UpdateSemesterUseCase(repository)
    }

    @Provides
    fun providesRemoveSemesterUseCase(repository: SemesterRepository): RemoveSemesterUseCase {
        return RemoveSemesterUseCase(repository)
    }

    @Provides
    fun providesGetCoursesOfSemesterUseCase(repository: SemesterRepository): GetCoursesOfSemesterUseCase {
        return GetCoursesOfSemesterUseCase(repository)
    }
}