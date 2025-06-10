package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.AttendanceStudent
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.data.dto.request.UpdateStudentAttendanceRequest
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateStudentAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: UpdateStudentAttendanceRequest): Flow<Resource<AttendifyApiResponse<AttendanceStudent?>>> {
        return RemoteUtils.responseFlow { attendanceRepository.updateStudentAttendance(requestBody) }
    }
}