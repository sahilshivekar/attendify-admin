package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Timetable
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.data.dto.request.AddTimetableRequest
import com.edu.wiet_admin.shedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTimetableUseCase @Inject constructor(private val timetableRepository: TimetableRepository) {
    operator fun invoke(requestBody: AddTimetableRequest): Flow<Resource<WietApiResponse<Timetable>>> {
        return RemoteUtils.responseFlow { timetableRepository.addTimetable(requestBody) }
    }
}