package com.attendify_admin.home.feature_users.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.attendify_admin.common.data.remote.dto.response.StaffDto
import com.attendify_admin.common.data.remote.dto.response.toStaff
import com.attendify_admin.common.domain.model.Staff
import com.attendify_admin.home.feature_users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// StaffRepository Use Cases
class GetStaffUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(
        searchQuery: String?,
        courseId: Int? = null,
    ): Flow<PagingData<Staff>> {
        return staffRepository.getStaff(searchQuery, courseId).map {
            it.map(StaffDto::toStaff)
        }
    }
}