package com.attendify_admin.home.feature_users.presentation.student_details

import android.net.Uri
import com.attendify_admin.common.domain.model.Dropout
import com.attendify_admin.common.domain.model.Semester
import com.attendify_admin.common.domain.model.Student
import com.attendify_admin.common.domain.model.StudentBatch
import com.attendify_admin.common.domain.model.StudentDivision
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class StudentDetailsState(
    val dialogText: String? = null,
    val studentId: Int? = null,
    val student: Student? = null,
    val isImageDialogVisible: Boolean = false,
    val newUploadedImageFileUri: Uri? = null,
    val isUpdatingImage: Boolean = false,
    val isRemovingImage: Boolean = false,
    val studentImageUri: Uri? = null,
    val isImageFetchedFromUrl: Boolean = false,
    val isBatchesLoading: Boolean = false,
    val isDivisionsLoading: Boolean = false,
    val isSemestersLoading: Boolean = true,
    val semesters: List<Semester>? = null,
    val studentDivisions: List<StudentDivision>? = null,
    val studentBatches: List<StudentBatch>? = null,
    val isLoadingInitialStudentDetails: Boolean = true,
    val isStudentImageFileUploading: Boolean = false,
    val isRemovingStudent: Boolean = false,
    val isStudentRemoved: Boolean = false,
    val dropoutDetails: ImmutableList<Dropout> = persistentListOf(),
    val areDropoutDetailsLoading: Boolean = false,

    )