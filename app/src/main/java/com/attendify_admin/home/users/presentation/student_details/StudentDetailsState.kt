package com.attendify_admin.home.users.presentation.student_details

import android.net.Uri
import com.attendify_admin.common.data.dto.response.Semester
import com.attendify_admin.common.data.dto.response.Student
import com.attendify_admin.common.data.dto.response.StudentBatch
import com.attendify_admin.common.data.dto.response.StudentDivision
import java.io.File

data class StudentDetailsState(
    val dialogText: String? = null,
    val studentId: Int? = null,
    val student: Student? = null,
//    val prn: String = "",
//    val firstName: String = "",
//    val middleName: String = "",
//    val lastName: String = "",
//    val email: String = "",
//    val phoneNumber: String = "",
//    val phoneNumberCountryCode: String = "",
//    val gender: String = "",
//    val dob: LocalDate? = null,
//    val admissionYear: String = "",
//    val admissionType: String = "",
    val isImageDialogVisible: Boolean = false,
    val newUploadedImageFile: File? = null,
    val newUploadedImageFileUri: Uri? = null,
    val newUploadedImageFileName: String? = null,

    val isUpdatingImage: Boolean = false,
    val isRemovingImage: Boolean = false,
//    val academicStatus: String = "",
    val studentImageFile: File? = null,
    val studentImageUri: Uri? = null,
    val studentImageFileName: String? = null,
    val isImageFetchedFromUrl: Boolean = false,
    val isBatchesLoading: Boolean = false,
    val isDivisionsLoading: Boolean = false,
    val isSemestersLoading: Boolean = true,
    val semesters: List<Semester>? = null,
    val studentDivisions: List<StudentDivision>? = null,
    val studentBatches: List<StudentBatch>? = null,
    val isLoadingInitialStudentDetails: Boolean = true,
    val isStudentImageFileUploading: Boolean = false,
)