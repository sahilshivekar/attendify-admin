package com.attendify_admin.home.announcements.di

import com.attendify_admin.home.announcements.data.EventApi
import com.attendify_admin.home.announcements.data.EventRepositoryImpl
import com.attendify_admin.home.announcements.domain.repository.EventRepository
import com.attendify_admin.home.announcements.domain.use_case.AddEventUseCase
import com.attendify_admin.home.announcements.domain.use_case.DeleteEventUseCase
import com.attendify_admin.home.announcements.domain.use_case.GetEventByIdUseCase
import com.attendify_admin.home.announcements.domain.use_case.GetEventsUseCase
import com.attendify_admin.home.announcements.domain.use_case.UpdateEventUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class EventModule {

    @Provides
    fun providesEventApi(retrofit: Retrofit): EventApi {
        return retrofit.create(EventApi::class.java)
    }

    @Provides
    fun providesEventRepository(api: EventApi): EventRepository {
        return EventRepositoryImpl(api)
    }

    @Provides
    fun providesGetEventsUseCase(repository: EventRepository): GetEventsUseCase {
        return GetEventsUseCase(repository)
    }

    @Provides
    fun providesGetEventByIdUseCase(repository: EventRepository): GetEventByIdUseCase {
        return GetEventByIdUseCase(repository)
    }

    @Provides
    fun providesAddEventUseCase(repository: EventRepository): AddEventUseCase {
        return AddEventUseCase(repository)
    }

    @Provides
    fun providesUpdateEventUseCase(repository: EventRepository): UpdateEventUseCase {
        return UpdateEventUseCase(repository)
    }

    @Provides
    fun providesDeleteEventUseCase(repository: EventRepository): DeleteEventUseCase {
        return DeleteEventUseCase(repository)
    }
}