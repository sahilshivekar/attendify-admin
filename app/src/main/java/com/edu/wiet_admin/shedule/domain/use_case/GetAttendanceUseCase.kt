package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.AttendanceStudent
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.data.dto.request.GetAttendanceRequest
import com.edu.wiet_admin.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: GetAttendanceRequest): Flow<Resource<WietApiResponse<List<AttendanceStudent?>>>> {
        return RemoteUtils.responseFlow { attendanceRepository.getAttendance(requestBody) }
    }
}