package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudent
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.data.dto.request.AddStudentsAttendanceRequest
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddStudentsAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: AddStudentsAttendanceRequest): Flow<Resource<AttendifyApiResponse<List<AttendanceStudent?>>>> {
        return RemoteUtils.responseFlow { attendanceRepository.addStudentsAttendance(requestBody) }
    }
}