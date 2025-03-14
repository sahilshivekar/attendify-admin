package com.edu.wiet_admin.shedule.di

import com.edu.wiet_admin.shedule.data.RoomApi
import com.edu.wiet_admin.shedule.data.RoomRepositoryImpl
import com.edu.wiet_admin.shedule.domain.repository.RoomRepository
import com.edu.wiet_admin.shedule.domain.use_case.AddRoomUseCase
import com.edu.wiet_admin.shedule.domain.use_case.GetRoomByIdUseCase
import com.edu.wiet_admin.shedule.domain.use_case.GetRoomsUseCase
import com.edu.wiet_admin.shedule.domain.use_case.RemoveRoomUseCase
import com.edu.wiet_admin.shedule.domain.use_case.UpdateRoomUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun providesRoomApi(retrofit: Retrofit): RoomApi {
        return retrofit.create(RoomApi::class.java)
    }

    @Provides
    fun providesRoomRepository(api: RoomApi): RoomRepository {
        return RoomRepositoryImpl(api)
    }

    @Provides
    fun providesAddRoomUseCase(repository: RoomRepository): AddRoomUseCase {
        return AddRoomUseCase(repository)
    }

    @Provides
    fun providesGetRoomsUseCase(repository: RoomRepository): GetRoomsUseCase {
        return GetRoomsUseCase(repository)
    }

    @Provides
    fun providesGetRoomByIdUseCase(repository: RoomRepository): GetRoomByIdUseCase {
        return GetRoomByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateRoomUseCase(repository: RoomRepository): UpdateRoomUseCase {
        return UpdateRoomUseCase(repository)
    }

    @Provides
    fun providesRemoveRoomUseCase(repository: RoomRepository): RemoveRoomUseCase {
        return RemoveRoomUseCase(repository)
    }
}