package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarkStudentAttendanceByBLEsessionUUIDUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(
        bleSessionUUID: String,
        studentId: Int,
    ): Flow<Resource<AttendifyApiResponse<String?>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.markStudentAttendanceByBLEsessionUUID(
                bleSessionUUID,
                studentId
            )
        }
    }
}