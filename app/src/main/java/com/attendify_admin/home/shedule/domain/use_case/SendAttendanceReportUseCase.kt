package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.NoParentEmailStudents
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendAttendanceReportUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(
        startDate: String,
        endDate: String,
        studentIds: List<String>,
        courseIds: List<String>,
        semesterId: Int,
    ): Flow<Resource<AttendifyApiResponse<List<NoParentEmailStudents>?>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.sendAttendanceReport(
                startDate,
                endDate,
                studentIds,
                courseIds,
                semesterId
            )
        }
    }
}