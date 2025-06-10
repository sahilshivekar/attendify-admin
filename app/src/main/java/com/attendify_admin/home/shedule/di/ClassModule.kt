package com.attendify_admin.home.shedule.di

import com.attendify_admin.home.shedule.data.ClassApi
import com.attendify_admin.home.shedule.data.ClassRepositoryImpl
import com.attendify_admin.home.shedule.domain.repository.ClassRepository
import com.attendify_admin.home.shedule.domain.use_case.AddClassUseCase
import com.attendify_admin.home.shedule.domain.use_case.AddExtraClassUseCase
import com.attendify_admin.home.shedule.domain.use_case.CancelClassUseCase
import com.attendify_admin.home.shedule.domain.use_case.ExtendActiveTillDateOfClassUseCase
import com.attendify_admin.home.shedule.domain.use_case.GetCancelledClassesUseCase
import com.attendify_admin.home.shedule.domain.use_case.GetClassByIdUseCase
import com.attendify_admin.home.shedule.domain.use_case.GetClassesUseCase
import com.attendify_admin.home.shedule.domain.use_case.RemoveClassUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ClassModule {

    @Provides
    fun providesClassApi(retrofit: Retrofit): ClassApi {
        return retrofit.create(ClassApi::class.java)
    }

    @Provides
    fun providesClassRepository(api: ClassApi): ClassRepository {
        return ClassRepositoryImpl(api)
    }

    @Provides
    fun providesAddClassUseCase(repository: ClassRepository): AddClassUseCase {
        return AddClassUseCase(repository)
    }

    @Provides
    fun providesGetClassesUseCase(repository: ClassRepository): GetClassesUseCase {
        return GetClassesUseCase(repository)
    }

    @Provides
    fun providesGetClassByIdUseCase(repository: ClassRepository): GetClassByIdUseCase {
        return GetClassByIdUseCase(repository)
    }

    @Provides
    fun providesExtendActiveTillDateOfClassUseCase(repository: ClassRepository): ExtendActiveTillDateOfClassUseCase {
        return ExtendActiveTillDateOfClassUseCase(repository)
    }

    @Provides
    fun providesRemoveClassUseCase(repository: ClassRepository): RemoveClassUseCase {
        return RemoveClassUseCase(repository)
    }

    @Provides
    fun providesCancelClassUseCase(repository: ClassRepository): CancelClassUseCase {
        return CancelClassUseCase(repository)
    }

    @Provides
    fun providesAddExtraClassUseCase(repository: ClassRepository): AddExtraClassUseCase {
        return AddExtraClassUseCase(repository)
    }

    @Provides
    fun providesGetCancelledClassesUseCase(repository: ClassRepository): GetCancelledClassesUseCase {
        return GetCancelledClassesUseCase(repository)
    }
}