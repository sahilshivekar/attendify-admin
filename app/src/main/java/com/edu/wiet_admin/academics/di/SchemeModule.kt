package com.edu.wiet_admin.academics.di

import com.edu.wiet_admin.academics.data.SchemeApi
import com.edu.wiet_admin.academics.data.SchemeRepositoryImpl
import com.edu.wiet_admin.academics.domain.repository.SchemeRepository
import com.edu.wiet_admin.academics.domain.use_case.AddSchemeUseCase
import com.edu.wiet_admin.academics.domain.use_case.GetSchemeByIdUseCase
import com.edu.wiet_admin.academics.domain.use_case.GetSchemesUseCase
import com.edu.wiet_admin.academics.domain.use_case.RemoveSchemeUseCase
import com.edu.wiet_admin.academics.domain.use_case.UpdateSchemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SchemeModule {

    @Provides
    fun providesSchemeApi(retrofit: Retrofit): SchemeApi {
        return retrofit.create(SchemeApi::class.java)
    }

    @Provides
    fun providesSchemeRepository(api: SchemeApi): SchemeRepository {
        return SchemeRepositoryImpl(api)
    }

    @Provides
    fun providesAddSchemeUseCase(repository: SchemeRepository): AddSchemeUseCase {
        return AddSchemeUseCase(repository)
    }

    @Provides
    fun providesGetSchemesUseCase(repository: SchemeRepository): GetSchemesUseCase {
        return GetSchemesUseCase(repository)
    }

    @Provides
    fun providesGetSchemeByIdUseCase(repository: SchemeRepository): GetSchemeByIdUseCase {
        return GetSchemeByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateSchemeUseCase(repository: SchemeRepository): UpdateSchemeUseCase {
        return UpdateSchemeUseCase(repository)
    }

    @Provides
    fun providesRemoveSchemeUseCase(repository: SchemeRepository): RemoveSchemeUseCase {
        return RemoveSchemeUseCase(repository)
    }
}