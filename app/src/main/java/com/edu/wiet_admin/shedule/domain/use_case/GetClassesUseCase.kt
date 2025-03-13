package com.edu.wiet_admin.shedule.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Class
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.shedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClassesUseCase @Inject constructor(private val classRepository: ClassRepository) {
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
        semesterId: Int?,
        page: Int,
        limit: Int
    ): Flow<Resource<WietApiResponse<List<Class>>>> {
        return RemoteUtils.responseFlow {
            classRepository.getClasses(
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
                semesterId,
                page,
                limit
            )
        }
    }
}