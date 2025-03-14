package com.edu.wiet_admin.academics.di

import com.edu.wiet_admin.academics.data.BranchApi
import com.edu.wiet_admin.academics.data.BranchRepositoryImpl
import com.edu.wiet_admin.academics.domain.repository.BranchRepository
import com.edu.wiet_admin.academics.domain.use_case.AddBranchUseCase
import com.edu.wiet_admin.academics.domain.use_case.GetBranchByIdUseCase
import com.edu.wiet_admin.academics.domain.use_case.GetBranchesUseCase
import com.edu.wiet_admin.academics.domain.use_case.RemoveBranchUseCase
import com.edu.wiet_admin.academics.domain.use_case.UpdateBranchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class BranchModule {

    @Provides
    fun providesBranchApi(retrofit: Retrofit): BranchApi {
        return retrofit.create(BranchApi::class.java)
    }

    @Provides
    fun providesBranchRepository(api: BranchApi): BranchRepository {
        return BranchRepositoryImpl(api)
    }

    @Provides
    fun providesAddBranchUseCase(repository: BranchRepository): AddBranchUseCase {
        return AddBranchUseCase(repository)
    }

    @Provides
    fun providesGetBranchesUseCase(repository: BranchRepository): GetBranchesUseCase {
        return GetBranchesUseCase(repository)
    }

    @Provides
    fun providesGetBranchByIdUseCase(repository: BranchRepository): GetBranchByIdUseCase {
        return GetBranchByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateBranchUseCase(repository: BranchRepository): UpdateBranchUseCase {
        return UpdateBranchUseCase(repository)
    }

    @Provides
    fun providesRemoveBranchUseCase(repository: BranchRepository): RemoveBranchUseCase {
        return RemoveBranchUseCase(repository)
    }
}