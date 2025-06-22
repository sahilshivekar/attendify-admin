package com.attendify_admin.home.feature_users.presentation.staff_details

import android.net.Uri
import com.attendify_admin.common.domain.model.Staff

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
)