package com.attendify_admin.home.users.di

import com.attendify_admin.home.users.data.StudentApi
import com.attendify_admin.home.users.data.StudentRepositoryImpl
import com.attendify_admin.home.users.domain.repository.StudentRepository
import com.attendify_admin.home.users.domain.use_case.AddStudentFcmTokenUseCase
import com.attendify_admin.home.users.domain.use_case.AddStudentToBatchUseCase
import com.attendify_admin.home.users.domain.use_case.AddStudentToDivisionUseCase
import com.attendify_admin.home.users.domain.use_case.AddStudentToSemesterUseCase
import com.attendify_admin.home.users.domain.use_case.AddStudentUseCase
import com.attendify_admin.home.users.domain.use_case.ChangeStudentBatchUseCase
import com.attendify_admin.home.users.domain.use_case.ChangeStudentDivisionUseCase
import com.attendify_admin.home.users.domain.use_case.GetStudentBatchesByIdUseCase
import com.attendify_admin.home.users.domain.use_case.GetStudentDetailsByIdUseCase
import com.attendify_admin.home.users.domain.use_case.GetStudentDivisionsByIdUseCase
import com.attendify_admin.home.users.domain.use_case.GetStudentSemestersByIdUseCase
import com.attendify_admin.home.users.domain.use_case.GetStudentsUseCase
import com.attendify_admin.home.users.domain.use_case.RemoveStudentFcmTokenUseCase
import com.attendify_admin.home.users.domain.use_case.RemoveStudentFromSemesterUseCase
import com.attendify_admin.home.users.domain.use_case.RemoveStudentImageUseCase
import com.attendify_admin.home.users.domain.use_case.RemoveStudentUseCase
import com.attendify_admin.home.users.domain.use_case.UpdateStudentDetailsUseCase
import com.attendify_admin.home.users.domain.use_case.UpdateStudentFcmTokenUseCase
import com.attendify_admin.home.users.domain.use_case.UpdateStudentImageUseCase
import com.attendify_admin.home.users.domain.use_case.UpdateStudentPasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class StudentModule {

    @Provides
    fun providesStudentApi(retrofit: Retrofit): StudentApi {
        return retrofit.create(StudentApi::class.java)
    }

    @Provides
    fun providesStudentRepository(api: StudentApi): StudentRepository {
        return StudentRepositoryImpl(api)
    }

    @Provides
    fun providesGetStudentsUseCase(repository: StudentRepository): GetStudentsUseCase {
        return GetStudentsUseCase(repository)
    }

    @Provides
    fun providesAddStudentUseCase(repository: StudentRepository): AddStudentUseCase {
        return AddStudentUseCase(repository)
    }

    @Provides
    fun providesUpdateStudentDetailsUseCase(repository: StudentRepository): UpdateStudentDetailsUseCase {
        return UpdateStudentDetailsUseCase(repository)
    }

    @Provides
    fun providesUpdateStudentPasswordUseCase(repository: StudentRepository): UpdateStudentPasswordUseCase {
        return UpdateStudentPasswordUseCase(repository)
    }

    @Provides
    fun providesUpdateStudentImageUseCase(repository: StudentRepository): UpdateStudentImageUseCase {
        return UpdateStudentImageUseCase(repository)
    }

    @Provides
    fun providesRemoveStudentImageUseCase(repository: StudentRepository): RemoveStudentImageUseCase {
        return RemoveStudentImageUseCase(repository)
    }

    @Provides
    fun providesRemoveStudentUseCase(repository: StudentRepository): RemoveStudentUseCase {
        return RemoveStudentUseCase(repository)
    }

    @Provides
    fun providesGetStudentDetailsByIdUseCase(repository: StudentRepository): GetStudentDetailsByIdUseCase {
        return GetStudentDetailsByIdUseCase(repository)
    }

    @Provides
    fun providesAddStudentToSemesterUseCase(repository: StudentRepository): AddStudentToSemesterUseCase {
        return AddStudentToSemesterUseCase(repository)
    }

    @Provides
    fun providesRemoveStudentFromSemesterUseCase(repository: StudentRepository): RemoveStudentFromSemesterUseCase {
        return RemoveStudentFromSemesterUseCase(repository)
    }

    @Provides
    fun providesAddStudentToDivisionUseCase(repository: StudentRepository): AddStudentToDivisionUseCase {
        return AddStudentToDivisionUseCase(repository)
    }

    @Provides
    fun providesChangeStudentDivisionUseCase(repository: StudentRepository): ChangeStudentDivisionUseCase {
        return ChangeStudentDivisionUseCase(repository)
    }

    @Provides
    fun providesAddStudentToBatchUseCase(repository: StudentRepository): AddStudentToBatchUseCase {
        return AddStudentToBatchUseCase(repository)
    }

    @Provides
    fun providesChangeStudentBatchUseCase(repository: StudentRepository): ChangeStudentBatchUseCase {
        return ChangeStudentBatchUseCase(repository)
    }

    @Provides
    fun providesGetStudentSemestersByIdUseCase(repository: StudentRepository): GetStudentSemestersByIdUseCase {
        return GetStudentSemestersByIdUseCase(repository)
    }

    @Provides
    fun providesGetStudentDivisionsByIdUseCase(repository: StudentRepository): GetStudentDivisionsByIdUseCase {
        return GetStudentDivisionsByIdUseCase(repository)
    }

    @Provides
    fun providesGetStudentBatchesByIdUseCase(repository: StudentRepository): GetStudentBatchesByIdUseCase {
        return GetStudentBatchesByIdUseCase(repository)
    }


    @Provides
    fun providesAddStudentFcmTokenUseCase(repository: StudentRepository): AddStudentFcmTokenUseCase {
        return AddStudentFcmTokenUseCase(repository)
    }

    @Provides
    fun providesUpdateStudentFcmTokenUseCase(repository: StudentRepository): UpdateStudentFcmTokenUseCase {
        return UpdateStudentFcmTokenUseCase(repository)
    }

    @Provides
    fun providesRemoveStudentFcmTokenUseCase(repository: StudentRepository): RemoveStudentFcmTokenUseCase {
        return RemoveStudentFcmTokenUseCase(repository)
    }
}