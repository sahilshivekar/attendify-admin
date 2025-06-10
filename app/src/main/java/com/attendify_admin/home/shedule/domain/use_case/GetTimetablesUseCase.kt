package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Timetable
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// TimetableRepository Use Cases
class GetTimetablesUseCase @Inject constructor(private val timetableRepository: TimetableRepository) {
    operator fun invoke(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        page: Int,
        limit: Int
    ): Flow<Resource<AttendifyApiResponse<List<Timetable>>>> {
        return RemoteUtils.responseFlow {
            timetableRepository.getTimetables(
                semesterNumber,
                academicStartYearOfSemester,
                academicEndYearOfSemester,
                page,
                limit
            )
        }
    }
}