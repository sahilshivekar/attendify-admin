package com.attendify_admin.home.shedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Class
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.shedule.domain.repository.ClassRepository
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
    ): Flow<Resource<AttendifyApiResponse<List<Class>>>> {
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