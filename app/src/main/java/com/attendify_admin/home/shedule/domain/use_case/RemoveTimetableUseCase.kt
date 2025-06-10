package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RemoveTimetableUseCase @Inject constructor(private val timetableRepository: TimetableRepository) {
    operator fun invoke(timetableId: Int): Flow<Resource<AttendifyApiResponse<Unit>>> {
        return RemoteUtils.responseFlow { timetableRepository.removeTimetable(timetableId) }
    }
}