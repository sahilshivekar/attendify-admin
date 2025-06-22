package com.attendify_admin.home.feature_users.domain.use_case

import android.util.Log
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toStudent
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Student
import com.attendify_admin.home.feature_users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.IOException
import javax.inject.Inject

class AddStudentUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
    operator fun invoke(
        prn: String,
        firstName: String,
        middleName: String?,
        lastName: String,
        email: String,
        phoneNumber: String,
        gender: String,
        dob: String?,
        schemeId: Int,
        admissionYear: String,
        admissionType: String,
        branchId: Int,
        studentImageFile: File?,
        parentEmail: String?
    ): Flow<Resource<Student?>> = flow {

        emit(Resource.Loading<Student?>())

        val response = runCatching {
            studentRepository.addStudent(
                prn,
                firstName,
                middleName,
                lastName,
                email,
                phoneNumber,
                gender,
                dob,
                schemeId,
                admissionYear,
                admissionType,
                branchId,
                studentImageFile,
                parentEmail
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {

                Log.d("success",response.body()?.data.toString())
                emit(Resource.Success(response.body()?.data?.toStudent()))
                Log.d("success conversion",response.body()?.data?.toStudent().toString())

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
