package com.attendify_admin.home.academics.di

import com.attendify_admin.home.academics.data.UniversityApi
import com.attendify_admin.home.academics.data.UniversityRepositoryImpl
import com.attendify_admin.home.academics.domain.repository.UniversityRepository
import com.attendify_admin.home.academics.domain.use_case.AddUniversityUseCase
import com.attendify_admin.home.academics.domain.use_case.GetUniversitiesUseCase
import com.attendify_admin.home.academics.domain.use_case.GetUniversityByIdUseCase
import com.attendify_admin.home.academics.domain.use_case.RemoveUniversityUseCase
import com.attendify_admin.home.academics.domain.use_case.UpdateUniversityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class UniversityModule {

    @Provides
    fun providesUniversityApi(retrofit: Retrofit): UniversityApi {
        return retrofit.create(UniversityApi::class.java)
    }

    @Provides
    fun providesUniversityRepository(api: UniversityApi): UniversityRepository {
        return UniversityRepositoryImpl(api)
    }

    @Provides
    fun providesAddUniversityUseCase(repository: UniversityRepository): AddUniversityUseCase {
        return AddUniversityUseCase(repository)
    }

    @Provides
    fun providesGetUniversitiesUseCase(repository: UniversityRepository): GetUniversitiesUseCase {
        return GetUniversitiesUseCase(repository)
    }

    @Provides
    fun providesGetUniversityByIdUseCase(repository: UniversityRepository): GetUniversityByIdUseCase {
        return GetUniversityByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateUniversityUseCase(repository: UniversityRepository): UpdateUniversityUseCase {
        return UpdateUniversityUseCase(repository)
    }

    @Provides
    fun providesRemoveUniversityUseCase(repository: UniversityRepository): RemoveUniversityUseCase {
        return RemoveUniversityUseCase(repository)
    }
}