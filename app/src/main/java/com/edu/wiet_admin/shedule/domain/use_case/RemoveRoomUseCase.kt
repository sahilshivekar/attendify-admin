package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    operator fun invoke(roomId: String): Flow<Resource<WietApiResponse<String?>>> {
        return RemoteUtils.responseFlow { roomRepository.removeRoom(roomId) }
    }
}