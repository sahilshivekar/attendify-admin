package com.attendify_admin.home.feature_users.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toStudent
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Student
import com.attendify_admin.home.feature_users.domain.repository.StudentRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllStudentsUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
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
        dropoutAcademicStartYear: String? = null,
        dropoutAcademicEndYear: String? = null,
        semesterId: Int? = null
    ): Flow<Resource<ImmutableList<Student>>> = flow {
        emit(Resource.Loading())

        val result = runCatching {
            studentRepository.getAllStudents(
                searchQuery,
                branchIds,
                semesterNumbers,
                academicStartYearOfSemester,
                academicEndYearOfSemester,
                batchId,
                schemeId,
                divisionId,
                academicStatuses,
                admissionTypes,
                admissionYear,
                currentBatch,
                currentDivision,
                currentSemester,
                divisionCode,
                batchCode,
                dropoutAcademicStartYear,
                dropoutAcademicEndYear,
                semesterId
            )
        }

        result.onSuccess { res ->
            if (res.isSuccessful) {
                emit(Resource.Success(res.body()?.data?.students?.map{it.toStudent()}?.toImmutableList()))
            } else {
                emit(Resource.Error(message = RemoteUtils.getErrorMessage(res)))
            }
        }

        result.onFailure { e ->
            emit(
                Resource.Error(
                    message = when (e) {
                        is IOException -> RemoteUtils.NETWORK_IO_ERROR_MESSAGE
                        else -> RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE
                    }
                )
            )
        }
    }
}


/*
* now give me code for step 5 and 6 below is the code you need for that


class AddStudentToSemesterUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
    operator fun invoke(
        requestBody: AddStudentToSemesterRequest
    ): Flow<Resource<StudentSemester?>> = flow {

        emit(Resource.Loading<StudentSemester?>())

        val response = runCatching {
            studentRepository.addStudentToSemester(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toStudentSemester()))
            } else {
                val errorMessage = RemoteUtils.getErrorMessage(response)
                emit(Resource.Error(message = errorMessage))
            }
        }

        response.onFailure { exception ->
            when (exception) {
                is IOException -> emit(Resource.Error(message = RemoteUtils.NETWORK_IO_ERROR_MESSAGE))
                else -> emit(Resource.Error(message = RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE))
            }
        }
    }
}

data class AddStudentToSemesterRequest(
    val studentId: Int,
    val semesterId: Int
)

class GetAllStudentsUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
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
        dropoutAcademicStartYear: String? = null,
        dropoutAcademicEndYear: String? = null,
    ): Flow<Resource<ImmutableList<Student>>> = flow {
        emit(Resource.Loading())

        val result = runCatching {
            studentRepository.getAllStudents(
                searchQuery,
                branchIds,
                semesterNumbers,
                academicStartYearOfSemester,
                academicEndYearOfSemester,
                batchId,
                schemeId,
                divisionId,
                academicStatuses,
                admissionTypes,
                admissionYear,
                currentBatch,
                currentDivision,
                currentSemester,
                divisionCode,
                batchCode,
                dropoutAcademicStartYear,
                dropoutAcademicEndYear
            )
        }

        result.onSuccess { res ->
            if (res.isSuccessful) {
                emit(Resource.Success(res.body()?.data?.students?.map{it.toStudent()}?.toImmutableList()))
            } else {
                emit(Resource.Error(message = RemoteUtils.getErrorMessage(res)))
            }
        }

        result.onFailure { e ->
            emit(
                Resource.Error(
                    message = when (e) {
                        is IOException -> RemoteUtils.NETWORK_IO_ERROR_MESSAGE
                        else -> RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE
                    }
                )
            )
        }
    }
}












*/