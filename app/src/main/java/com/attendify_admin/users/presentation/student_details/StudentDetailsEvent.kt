package com.attendify_admin.users.presentation.student_details

import android.net.Uri

sealed class StudentDetailsEvent {
    data class ShowAlertDialog(val message: String) : StudentDetailsEvent()
    data object DismissAlertDialog : StudentDetailsEvent()
    data object ToggleImageDialog : StudentDetailsEvent()
    data object RemoveImageClicked : StudentDetailsEvent()
    data object UpdateStudentImageClicked : StudentDetailsEvent()
    data class StudentNewImageUploaded(val studentImageUri: Uri?) : StudentDetailsEvent()
    data object StudentImageFetchedFromUrl : StudentDetailsEvent()
}