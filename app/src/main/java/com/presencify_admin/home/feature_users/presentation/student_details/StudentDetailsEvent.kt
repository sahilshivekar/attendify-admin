package com.presencify_admin.home.feature_users.presentation.student_details

import android.net.Uri

sealed class StudentDetailsEvent {
    data object ToggleImageDialog : StudentDetailsEvent()
    data object RemoveImageClicked : StudentDetailsEvent()
    data object UpdateStudentImageClicked : StudentDetailsEvent()
    data class StudentNewImageUploaded(val studentImageUri: Uri?) : StudentDetailsEvent()
    data object StudentImageFetchedFromUrl : StudentDetailsEvent()
    data object RemoveStudentClicked : StudentDetailsEvent()
}