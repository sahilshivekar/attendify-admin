package com.attendify_admin.shedule.di

import com.attendify_admin.shedule.data.TimetableApi
import com.attendify_admin.shedule.data.TimetableRepositoryImpl
import com.attendify_admin.shedule.domain.repository.TimetableRepository
import com.attendify_admin.shedule.domain.use_case.AddTimetableUseCase
import com.attendify_admin.shedule.domain.use_case.GetTimetableByIdUseCase
import com.attendify_admin.shedule.domain.use_case.GetTimetablesUseCase
import com.attendify_admin.shedule.domain.use_case.RemoveTimetableUseCase
import com.attendify_admin.shedule.domain.use_case.UpdateTimetableUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class TimetableModule {

    @Provides
    fun providesTimetableApi(retrofit: Retrofit): TimetableApi {
        return retrofit.create(TimetableApi::class.java)
    }

    @Provides
    fun providesTimetableRepository(api: TimetableApi): TimetableRepository {
        return TimetableRepositoryImpl(api)
    }

    @Provides
    fun providesAddTimetableUseCase(repository: TimetableRepository): AddTimetableUseCase {
        return AddTimetableUseCase(repository)
    }

    @Provides
    fun providesGetTimetablesUseCase(repository: TimetableRepository): GetTimetablesUseCase {
        return GetTimetablesUseCase(repository)
    }

    @Provides
    fun providesGetTimetableByIdUseCase(repository: TimetableRepository): GetTimetableByIdUseCase {
        return GetTimetableByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateTimetableUseCase(repository: TimetableRepository): UpdateTimetableUseCase {
        return UpdateTimetableUseCase(repository)
    }

    @Provides
    fun providesRemoveTimetableUseCase(repository: TimetableRepository): RemoveTimetableUseCase {
        return RemoveTimetableUseCase(repository)
    }
}