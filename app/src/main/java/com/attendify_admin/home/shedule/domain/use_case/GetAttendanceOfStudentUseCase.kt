package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.AttendanceStudentAggregatedAndDetailedAttendance
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttendanceOfStudentUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(
        studentId: Int,
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Flow<Resource<AttendifyApiResponse<AttendanceStudentAggregatedAndDetailedAttendance>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.getAttendanceOfStudent(
                studentId,
                courseId,
                semesterId,
                divisionId,
                batchId,
                startDate,
                endDate
            )
        }
    }
}