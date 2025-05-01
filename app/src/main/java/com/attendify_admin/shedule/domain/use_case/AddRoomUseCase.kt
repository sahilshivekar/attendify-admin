package com.attendify_admin.shedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Room
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.shedule.data.dto.request.AddRoomRequest
import com.attendify_admin.shedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// RoomRepository Use Cases
class AddRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    operator fun invoke(requestBody: AddRoomRequest): Flow<Resource<AttendifyApiResponse<Room?>>> {
        return RemoteUtils.responseFlow { roomRepository.addRoom(requestBody) }
    }
}