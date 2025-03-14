package com.edu.wiet_admin.shedule.di

import com.edu.wiet_admin.shedule.data.ClassApi
import com.edu.wiet_admin.shedule.data.ClassRepositoryImpl
import com.edu.wiet_admin.shedule.domain.repository.ClassRepository
import com.edu.wiet_admin.shedule.domain.use_case.AddClassUseCase
import com.edu.wiet_admin.shedule.domain.use_case.ExtendActiveTillDateOfClassUseCase
import com.edu.wiet_admin.shedule.domain.use_case.GetClassByIdUseCase
import com.edu.wiet_admin.shedule.domain.use_case.GetClassesUseCase
import com.edu.wiet_admin.shedule.domain.use_case.RemoveClassUseCase
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
}