package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Attendance
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.data.dto.request.CreateAttendanceRequest
import com.edu.wiet_admin.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// AttendanceRepository Use Cases
class CreateAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: CreateAttendanceRequest): Flow<Resource<WietApiResponse<Attendance?>>> {
        return RemoteUtils.responseFlow { attendanceRepository.createAttendance(requestBody) }
    }
}