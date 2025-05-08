package com.attendify_admin.home.academics.di

import com.attendify_admin.home.academics.data.BatchApi
import com.attendify_admin.home.academics.data.BatchRepositoryImpl
import com.attendify_admin.home.academics.domain.repository.BatchRepository
import com.attendify_admin.home.academics.domain.use_case.AddBatchUseCase
import com.attendify_admin.home.academics.domain.use_case.GetBatchByIdUseCase
import com.attendify_admin.home.academics.domain.use_case.GetBatchesUseCase
import com.attendify_admin.home.academics.domain.use_case.RemoveBatchUseCase
import com.attendify_admin.home.academics.domain.use_case.UpdateBatchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class BatchModule {

    @Provides
    fun providesBatchApi(retrofit: Retrofit): BatchApi {
        return retrofit.create(BatchApi::class.java)
    }

    @Provides
    fun providesBatchRepository(api: BatchApi): BatchRepository {
        return BatchRepositoryImpl(api)
    }

    @Provides
    fun providesAddBatchUseCase(repository: BatchRepository): AddBatchUseCase {
        return AddBatchUseCase(repository)
    }

    @Provides
    fun providesGetBatchesUseCase(repository: BatchRepository): GetBatchesUseCase {
        return GetBatchesUseCase(repository)
    }

    @Provides
    fun providesGetBatchByIdUseCase(repository: BatchRepository): GetBatchByIdUseCase {
        return GetBatchByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateBatchUseCase(repository: BatchRepository): UpdateBatchUseCase {
        return UpdateBatchUseCase(repository)
    }

    @Provides
    fun providesDeleteBatchUseCase(repository: BatchRepository): RemoveBatchUseCase {
        return RemoveBatchUseCase(repository)
    }
}