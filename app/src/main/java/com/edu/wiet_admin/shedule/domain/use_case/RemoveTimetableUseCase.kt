package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RemoveTimetableUseCase @Inject constructor(private val timetableRepository: TimetableRepository) {
    operator fun invoke(timetableId: Int): Flow<Resource<WietApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { timetableRepository.removeTimetable(timetableId) }
    }
}