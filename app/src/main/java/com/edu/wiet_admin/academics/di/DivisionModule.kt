package com.edu.wiet_admin.academics.di

import com.edu.wiet_admin.academics.data.DivisionApi
import com.edu.wiet_admin.academics.data.DivisionRepositoryImpl
import com.edu.wiet_admin.academics.domain.repository.DivisionRepository
import com.edu.wiet_admin.academics.domain.use_case.AddDivisionUseCase
import com.edu.wiet_admin.academics.domain.use_case.GetDivisionByIdUseCase
import com.edu.wiet_admin.academics.domain.use_case.GetDivisionsUseCase
import com.edu.wiet_admin.academics.domain.use_case.RemoveDivisionUseCase
import com.edu.wiet_admin.academics.domain.use_case.UpdateDivisionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DivisionModule {

    @Provides
    fun providesDivisionApi(retrofit: Retrofit): DivisionApi {
        return retrofit.create(DivisionApi::class.java)
    }

    @Provides
    fun providesDivisionRepository(api: DivisionApi): DivisionRepository {
        return DivisionRepositoryImpl(api)
    }

    @Provides
    fun providesAddDivisionUseCase(repository: DivisionRepository): AddDivisionUseCase {
        return AddDivisionUseCase(repository)
    }

    @Provides
    fun providesGetDivisionsUseCase(repository: DivisionRepository): GetDivisionsUseCase {
        return GetDivisionsUseCase(repository)
    }

    @Provides
    fun providesGetDivisionByIdUseCase(repository: DivisionRepository): GetDivisionByIdUseCase {
        return GetDivisionByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateDivisionUseCase(repository: DivisionRepository): UpdateDivisionUseCase {
        return UpdateDivisionUseCase(repository)
    }

    @Provides
    fun providesRemoveDivisionUseCase(repository: DivisionRepository): RemoveDivisionUseCase {
        return RemoveDivisionUseCase(repository)
    }
}