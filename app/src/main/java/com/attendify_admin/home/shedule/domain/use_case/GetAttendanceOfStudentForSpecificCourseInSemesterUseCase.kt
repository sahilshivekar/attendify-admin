package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.AttendanceStudentCount
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.data.dto.request.GetAttendanceOfStudentForSpecificCourseInSemesterRequest
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAttendanceOfStudentForSpecificCourseInSemesterUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    operator fun invoke(requestBody: GetAttendanceOfStudentForSpecificCourseInSemesterRequest): Flow<Resource<AttendifyApiResponse<List<AttendanceStudentCount?>>>> {
        return RemoteUtils.responseFlow {
            attendanceRepository.getAttendanceOfStudentForSpecificCourseInSemester(
                requestBody
            )
        }
    }
}