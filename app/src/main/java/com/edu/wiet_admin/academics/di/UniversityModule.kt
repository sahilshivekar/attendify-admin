package com.edu.wiet_admin.academics.di

import com.edu.wiet_admin.academics.data.UniversityApi
import com.edu.wiet_admin.academics.data.UniversityRepositoryImpl
import com.edu.wiet_admin.academics.domain.repository.UniversityRepository
import com.edu.wiet_admin.academics.domain.use_case.AddUniversityUseCase
import com.edu.wiet_admin.academics.domain.use_case.GetUniversityByIdUseCase
import com.edu.wiet_admin.academics.domain.use_case.GetUniversitiesUseCase
import com.edu.wiet_admin.academics.domain.use_case.RemoveUniversityUseCase
import com.edu.wiet_admin.academics.domain.use_case.UpdateUniversityUseCase
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