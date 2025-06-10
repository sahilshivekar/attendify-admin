package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.AttendanceStudent
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(
        date: String?,
        attendanceId: Int?,
        classId: Int?,
        studentId: Int?,
        courseId: Int?,
        semesterId: Int?,
        divisionId: Int?
    ): Flow<Resource<AttendifyApiResponse<List<AttendanceStudent?>>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.getAttendance(
                date,
                attendanceId,
                classId,
                studentId,
                courseId,
                semesterId,
                divisionId
            )
        }
    }
}