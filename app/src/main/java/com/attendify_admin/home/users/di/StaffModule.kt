package com.attendify_admin.home.users.di

import com.attendify_admin.home.users.data.StaffApi
import com.attendify_admin.home.users.data.StaffRepositoryImpl
import com.attendify_admin.home.users.domain.repository.StaffRepository
import com.attendify_admin.home.users.domain.use_case.AddStaffUseCase
import com.attendify_admin.home.users.domain.use_case.AddTeachingSubjectUseCase
import com.attendify_admin.home.users.domain.use_case.GetStaffByIdUseCase
import com.attendify_admin.home.users.domain.use_case.GetStaffUseCase
import com.attendify_admin.home.users.domain.use_case.GetTeachingSubjectsUseCase
import com.attendify_admin.home.users.domain.use_case.RemoveImageUseCase
import com.attendify_admin.home.users.domain.use_case.RemoveStaffUseCase
import com.attendify_admin.home.users.domain.use_case.RemoveTeachingSubjectUseCase
import com.attendify_admin.home.users.domain.use_case.UpdateStaffDetailsUseCase
import com.attendify_admin.home.users.domain.use_case.UpdateStaffImageUseCase
import com.attendify_admin.home.users.domain.use_case.UpdateStaffPasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class StaffModule {

    @Provides
    fun providesStaffApi(retrofit: Retrofit): StaffApi {
        return retrofit.create(StaffApi::class.java)
    }

    @Provides
    fun providesStaffRepository(api: StaffApi): StaffRepository {
        return StaffRepositoryImpl(api)
    }

    @Provides
    fun providesGetStaffUseCase(repository: StaffRepository): GetStaffUseCase {
        return GetStaffUseCase(repository)
    }

    @Provides
    fun providesGetStaffByIdUseCase(repository: StaffRepository): GetStaffByIdUseCase {
        return GetStaffByIdUseCase(repository)
    }

    @Provides
    fun providesAddStaffUseCase(repository: StaffRepository): AddStaffUseCase {
        return AddStaffUseCase(repository)
    }

    @Provides
    fun providesUpdateStaffDetailsUseCase(repository: StaffRepository): UpdateStaffDetailsUseCase {
        return UpdateStaffDetailsUseCase(repository)
    }

    @Provides
    fun providesUpdateStaffPasswordUseCase(repository: StaffRepository): UpdateStaffPasswordUseCase {
        return UpdateStaffPasswordUseCase(repository)
    }

    @Provides
    fun providesUpdateStaffImageUseCase(repository: StaffRepository): UpdateStaffImageUseCase {
        return UpdateStaffImageUseCase(repository)
    }

    @Provides
    fun providesRemoveStaffUseCase(repository: StaffRepository): RemoveStaffUseCase {
        return RemoveStaffUseCase(repository)
    }

    @Provides
    fun providesRemoveImageUseCase(repository: StaffRepository): RemoveImageUseCase {
        return RemoveImageUseCase(repository)
    }

    @Provides
    fun providesGetTeachingSubjectsUseCase(repository: StaffRepository): GetTeachingSubjectsUseCase {
        return GetTeachingSubjectsUseCase(repository)
    }

    @Provides
    fun providesAddTeachingSubjectUseCase(repository: StaffRepository): AddTeachingSubjectUseCase {
        return AddTeachingSubjectUseCase(repository)
    }

    @Provides
    fun providesRemoveTeachingSubjectUseCase(repository: StaffRepository): RemoveTeachingSubjectUseCase {
        return RemoveTeachingSubjectUseCase(repository)
    }
}