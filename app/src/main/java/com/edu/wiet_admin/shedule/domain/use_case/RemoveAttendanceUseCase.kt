package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.data.dto.request.RemoveAttendanceRequest
import com.edu.wiet_admin.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: RemoveAttendanceRequest): Flow<Resource<WietApiResponse<String?>>> {
        return RemoteUtils.responseFlow { attendanceRepository.removeAttendance(requestBody) }
    }
}