package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.Attendance
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActiveAttendanceSheetUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(
        studentId: Int,
        divisionId: Int,
    ): Flow<Resource<AttendifyApiResponse<List<Attendance>?>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.getActiveAttendanceSheet(
                studentId,
                divisionId
            )
        }
    }
}