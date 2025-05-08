package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Timetable
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.data.dto.request.AddTimetableRequest
import com.attendify_admin.home.shedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTimetableUseCase @Inject constructor(private val timetableRepository: TimetableRepository) {
    operator fun invoke(requestBody: AddTimetableRequest): Flow<Resource<AttendifyApiResponse<Timetable>>> {
        return RemoteUtils.responseFlow { timetableRepository.addTimetable(requestBody) }
    }
}