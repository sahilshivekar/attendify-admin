package com.attendify_admin.home.users.domain.use_case

import androidx.paging.PagingData
import com.attendify_admin.common.data.dto.response.Student
import com.attendify_admin.home.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// StudentRepository Use Cases
class GetStudentsUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(
        searchQuery: String? = null,
        branchIds: List<Int>? = null,
        semesterNumbers: List<Int>? = null,
        academicStartYearOfSemester: Int? = null,
        academicEndYearOfSemester: Int? = null,
        batchId: Int? = null,
        schemeId: Int? = null,
        divisionId: Int? = null,
        academicStatuses: List<String>? = null,
        admissionTypes: List<String>? = null,
        admissionYear: Int? = null,
        currentBatch: Boolean? = null,
        currentDivision: Boolean? = null,
        currentSemester: Boolean? = null,
        divisionCode: String? = null,
        batchCode: String? = null,
    ): Flow<PagingData<Student>> {
        return studentRepository.getStudents(
            searchQuery, branchIds, semesterNumbers, academicStartYearOfSemester,
            academicEndYearOfSemester, batchId, schemeId, divisionId, academicStatuses,
            admissionTypes, admissionYear, currentBatch, currentDivision, currentSemester,
            divisionCode, batchCode
        )
    }
}