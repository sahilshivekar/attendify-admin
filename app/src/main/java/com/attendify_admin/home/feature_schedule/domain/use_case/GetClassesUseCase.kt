package com.attendify_admin.home.feature_schedule.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.attendify_admin.common.data.remote.dto.response.toClass
import com.attendify_admin.common.domain.model.Class
import com.attendify_admin.home.feature_schedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetClassesUseCase @Inject constructor(
    private val classRepository: ClassRepository,
) {
    operator fun invoke(
        searchQuery: String?,
        timetableId: Int?,
        divisionId: Int?,
        startTime: String?,
        endTime: String?,
        activeFrom: String?,
        activeTill: String?,
        instructorId: Int?,
        dayOfWeek: String?,
        roomId: Int?,
        batchId: Int?,
        classType: String?,
        courseId: Int?,
        semesterId: Int?
    ): Flow<PagingData<Class>> {
        return classRepository.getClasses(
            searchQuery,
            timetableId,
            divisionId,
            startTime,
            endTime,
            activeFrom,
            activeTill,
            instructorId,
            dayOfWeek,
            roomId,
            batchId,
            classType,
            courseId,
            semesterId
        ).map { pagingData ->
            pagingData.map { it.toClass() }
        }
    }
}



