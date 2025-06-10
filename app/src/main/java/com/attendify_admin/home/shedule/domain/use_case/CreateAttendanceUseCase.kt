package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Attendance
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.data.dto.request.CreateAttendanceRequest
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// AttendanceRepository Use Cases
class CreateAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: CreateAttendanceRequest): Flow<Resource<AttendifyApiResponse<Attendance?>>> {
        return RemoteUtils.responseFlow { attendanceRepository.createAttendance(requestBody) }
    }
}