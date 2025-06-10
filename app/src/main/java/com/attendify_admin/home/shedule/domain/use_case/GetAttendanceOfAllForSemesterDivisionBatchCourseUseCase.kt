package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.AttendanceAllStudents
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttendanceOfAllForSemesterDivisionBatchCourseUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Flow<Resource<AttendifyApiResponse<AttendanceAllStudents>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.getAttendanceOfAllForSemesterDivisionBatchCourse(
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