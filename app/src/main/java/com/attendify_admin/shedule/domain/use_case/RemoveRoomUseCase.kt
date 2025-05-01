package com.attendify_admin.shedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.shedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    operator fun invoke(roomId: String): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow { roomRepository.removeRoom(roomId) }
    }
}