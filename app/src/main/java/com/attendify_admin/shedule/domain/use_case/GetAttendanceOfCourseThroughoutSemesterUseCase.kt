package com.attendify_admin.shedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudentCount
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.shedule.data.dto.request.GetAttendanceOfCourseThroughoutSemesterRequest
import com.attendify_admin.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttendanceOfCourseThroughoutSemesterUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: GetAttendanceOfCourseThroughoutSemesterRequest): Flow<Resource<AttendifyApiResponse<List<AttendanceStudentCount?>>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.getAttendanceOfCourseThroughoutSemester(
                requestBody
            )
        }
    }
}