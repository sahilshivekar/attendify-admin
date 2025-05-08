package com.attendify_admin.home.users.domain.use_case

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.response_dto.Staff
import com.attendify_admin.home.users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// StaffRepository Use Cases
class GetStaffUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(
        searchQuery: String?,
        courseId: Int? = null,
    ): Flow<PagingData<Staff>> {
        return staffRepository.getStaff(searchQuery, courseId)
    }
}