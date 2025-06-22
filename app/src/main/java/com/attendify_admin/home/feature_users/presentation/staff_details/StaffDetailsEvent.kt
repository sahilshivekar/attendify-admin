package com.attendify_admin.home.feature_users.presentation.staff_details

import android.net.Uri

sealed class StaffDetailsEvent {
    data object ToggleImageDialog : StaffDetailsEvent()
    data object RemoveImageClicked : StaffDetailsEvent()
    data object UpdateStaffImageClicked : StaffDetailsEvent()
    data class StaffNewImageUploaded(val staffImageUri: Uri?) : StaffDetailsEvent()
    data object StaffImageFetchedFromUrl : StaffDetailsEvent()
    data object RemoveStaffClicked : StaffDetailsEvent()
}