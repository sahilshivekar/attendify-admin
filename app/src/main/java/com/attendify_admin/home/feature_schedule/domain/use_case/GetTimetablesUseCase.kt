package com.attendify_admin.home.feature_schedule.domain.use_case

// TimetableRepository Use Cases
import androidx.paging.PagingData
import androidx.paging.map
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toTimetable
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Timetable
import com.attendify_admin.home.feature_schedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
class GetTimetablesUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository,
) {
    operator fun invoke(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?
    ): Flow<PagingData<Timetable>> {
        return timetableRepository.getTimetables(
            semesterNumber,
            academicStartYearOfSemester,
            academicEndYearOfSemester
        ).map { pagingData ->
            pagingData.map { it.toTimetable() }
        }
    }
}
