package com.presencify_admin.home.feature_users.presentation.staff_details

import android.net.Uri
import com.presencify_admin.common.domain.model.Staff
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class StaffDetailsState(
    val staffId: Int? = null,
    val staff: Staff? = null,
    val isImageDialogVisible: Boolean = false,
    val newUploadedImageFileUri: Uri? = null,
    val isUpdatingImage: Boolean = false,
    val isRemovingImage: Boolean = false,
    val staffImageUri: Uri? = null,
    val isImageFetchedFromUrl: Boolean = false,
    val isLoadingInitialStaffDetails: Boolean = true,
    val isStaffMemberRemoved: Boolean = false,
    val isRemovingStaffMember: Boolean = false,
    val assignedCourses: PersistentList<CourseData> = persistentListOf(),
    val isLoadingAssignedSubjects: Boolean = true
)


data class CourseData(
    val courseId: Int,
    val isAssigningCourse: Boolean = false,
    val isAssigned: Boolean = false,
    val isFailedToAssign: Boolean = false,
    val courseCode: String,
    val courseName: String,
)