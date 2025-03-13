package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Timetable
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.domain.repository.TimetableRepository
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
    ): Flow<Resource<WietApiResponse<List<Timetable>>>> {
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