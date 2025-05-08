package com.attendify_admin.home.academics.di

import com.attendify_admin.home.academics.data.CourseApi
import com.attendify_admin.home.academics.data.CourseRepositoryImpl
import com.attendify_admin.home.academics.domain.repository.CourseRepository
import com.attendify_admin.home.academics.domain.use_case.AddCourseToBranchWithSemesterNumberUseCase
import com.attendify_admin.home.academics.domain.use_case.AddCourseUseCase
import com.attendify_admin.home.academics.domain.use_case.GetCourseByIdUseCase
import com.attendify_admin.home.academics.domain.use_case.GetCoursesUseCase
import com.attendify_admin.home.academics.domain.use_case.RemoveCourseFromBranchWithSemesterNumberUseCase
import com.attendify_admin.home.academics.domain.use_case.RemoveCourseUseCase
import com.attendify_admin.home.academics.domain.use_case.UpdateCourseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CourseModule {

    @Provides
    fun providesCourseApi(retrofit: Retrofit): CourseApi {
        return retrofit.create(CourseApi::class.java)
    }

    @Provides
    fun providesCourseRepository(api: CourseApi): CourseRepository {
        return CourseRepositoryImpl(api)
    }

    @Provides
    fun providesAddCourseUseCase(repository: CourseRepository): AddCourseUseCase {
        return AddCourseUseCase(repository)
    }

    @Provides
    fun providesGetCoursesUseCase(repository: CourseRepository): GetCoursesUseCase {
        return GetCoursesUseCase(repository)
    }

    @Provides
    fun providesGetCourseByIdUseCase(repository: CourseRepository): GetCourseByIdUseCase {
        return GetCourseByIdUseCase(repository)
    }

    @Provides
    fun providesUpdateCourseUseCase(repository: CourseRepository): UpdateCourseUseCase {
        return UpdateCourseUseCase(repository)
    }

    @Provides
    fun providesRemoveCourseUseCase(repository: CourseRepository): RemoveCourseUseCase {
        return RemoveCourseUseCase(repository)
    }

    @Provides
    fun providesAddCourseToBranchWithSemesterNumberUseCase(repository: CourseRepository): AddCourseToBranchWithSemesterNumberUseCase {
        return AddCourseToBranchWithSemesterNumberUseCase(repository)
    }

    @Provides
    fun providesRemoveCourseFromBranchWithSemesterNumberUseCase(repository: CourseRepository): RemoveCourseFromBranchWithSemesterNumberUseCase {
        return RemoveCourseFromBranchWithSemesterNumberUseCase(repository)
    }
}