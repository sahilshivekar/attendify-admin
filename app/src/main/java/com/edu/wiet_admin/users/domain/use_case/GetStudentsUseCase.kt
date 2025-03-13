package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Student
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// StudentRepository Use Cases
class GetStudentsUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        batchId: Int?,
        schemeId: Int?,
        divisionId: Int?,
        academicStatus: String?,
        admissionType: String?,
        admissionYear: Int?,
        currentBatch: Boolean?,
        currentDivision: Boolean?,
        studentStatus: String?,
        divisionCode: String?,
        batchCode: String?,
        page: Int,
        limit: Int
    ): Flow<Resource<WietApiResponse<List<Student>>>> {
        return RemoteUtils.responseFlow {
            studentRepository.getStudents(
                searchQuery, branchId, semesterNumber, academicStartYearOfSemester,
                academicEndYearOfSemester, batchId, schemeId, divisionId, academicStatus,
                admissionType, admissionYear, currentBatch, currentDivision, studentStatus,
                divisionCode, batchCode, page, limit
            )
        }
    }
}