package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.AttendanceStudentCount
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.data.dto.request.GetAttendanceOfCourseOnDateRequest
import com.edu.wiet_admin.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttendanceOfCourseOnDateUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: GetAttendanceOfCourseOnDateRequest): Flow<Resource<WietApiResponse<List<AttendanceStudentCount?>>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.getAttendanceOfCourseOnDate(
                requestBody
            )
        }
    }
}