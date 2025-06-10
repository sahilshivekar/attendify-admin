package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.Room
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.data.dto.request.UpdateRoomRequest
import com.attendify_admin.home.shedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    operator fun invoke(requestBody: UpdateRoomRequest): Flow<Resource<AttendifyApiResponse<Room?>>> {
        return RemoteUtils.responseFlow { roomRepository.updateRoom(requestBody) }
    }
}