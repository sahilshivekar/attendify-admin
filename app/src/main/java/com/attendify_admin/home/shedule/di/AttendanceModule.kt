package com.attendify_admin.home.shedule.di

import com.attendify_admin.home.shedule.data.AttendanceApi
import com.attendify_admin.home.shedule.data.AttendanceRepositoryImpl
import com.attendify_admin.home.shedule.domain.repository.AttendanceRepository
import com.attendify_admin.home.shedule.domain.use_case.AddStudentsAttendanceUseCase
import com.attendify_admin.home.shedule.domain.use_case.CreateAttendanceUseCase
import com.attendify_admin.home.shedule.domain.use_case.GetAttendanceOfCourseOnDateUseCase
import com.attendify_admin.home.shedule.domain.use_case.GetAttendanceOfCourseThroughoutSemesterUseCase
import com.attendify_admin.home.shedule.domain.use_case.GetAttendanceOfStudentForSpecificCourseInSemesterUseCase
import com.attendify_admin.home.shedule.domain.use_case.GetAttendanceUseCase
import com.attendify_admin.home.shedule.domain.use_case.RemoveAttendanceUseCase
import com.attendify_admin.home.shedule.domain.use_case.UpdateStudentAttendanceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class AttendanceModule {

    @Provides
    fun providesAttendanceApi(retrofit: Retrofit): AttendanceApi {
        return retrofit.create(AttendanceApi::class.java)
    }

    @Provides
    fun providesAttendanceRepository(api: AttendanceApi): AttendanceRepository {
        return AttendanceRepositoryImpl(api)
    }

    @Provides
    fun providesCreateAttendanceUseCase(repository: AttendanceRepository): CreateAttendanceUseCase {
        return CreateAttendanceUseCase(repository)
    }

    @Provides
    fun providesAddStudentsAttendanceUseCase(repository: AttendanceRepository): AddStudentsAttendanceUseCase {
        return AddStudentsAttendanceUseCase(repository)
    }

    @Provides
    fun providesUpdateStudentAttendanceUseCase(repository: AttendanceRepository): UpdateStudentAttendanceUseCase {
        return UpdateStudentAttendanceUseCase(repository)
    }

    @Provides
    fun providesRemoveAttendanceUseCase(repository: AttendanceRepository): RemoveAttendanceUseCase {
        return RemoveAttendanceUseCase(repository)
    }

    @Provides
    fun providesGetAttendanceUseCase(repository: AttendanceRepository): GetAttendanceUseCase {
        return GetAttendanceUseCase(repository)
    }

    @Provides
    fun providesGetAttendanceOfStudentForSpecificCourseInSemesterUseCase(repository: AttendanceRepository): GetAttendanceOfStudentForSpecificCourseInSemesterUseCase {
        return GetAttendanceOfStudentForSpecificCourseInSemesterUseCase(repository)
    }

    @Provides
    fun providesGetAttendanceOfCourseOnDateUseCase(repository: AttendanceRepository): GetAttendanceOfCourseOnDateUseCase {
        return GetAttendanceOfCourseOnDateUseCase(repository)
    }

    @Provides
    fun providesGetAttendanceOfCourseThroughoutSemesterUseCase(repository: AttendanceRepository): GetAttendanceOfCourseThroughoutSemesterUseCase {
        return GetAttendanceOfCourseThroughoutSemesterUseCase(repository)
    }
}