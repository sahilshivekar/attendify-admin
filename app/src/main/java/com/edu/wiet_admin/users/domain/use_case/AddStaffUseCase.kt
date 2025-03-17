package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Staff
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class AddStaffUseCase @Inject constructor(private val staffRepository: StaffRepository) {
    operator fun invoke(
        firstName: String,
        middleName: String?,
        lastName: String,
        email: String,
        phoneNumber: String,
        gender: String,
        highestQualification: String?,
        role: String,
//        password: String,
//        confirmPassword: String,
        isActive: Boolean,
        staffImageFile: File?
    ): Flow<Resource<WietApiResponse<Staff>>> {
        return RemoteUtils.responseFlow {
            staffRepository.addStaff(
                firstName,
                middleName,
                lastName,
                email,
                phoneNumber,
                gender,
                highestQualification,
                role,
//                password,
//                confirmPassword,
                isActive,
                staffImageFile
            )
        }
    }
}