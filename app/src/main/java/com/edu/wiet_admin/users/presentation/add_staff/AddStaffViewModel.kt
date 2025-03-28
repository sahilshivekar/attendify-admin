package com.edu.wiet_admin.users.presentation.add_staff

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.utils.UriToFileUtil
import com.edu.wiet_admin.common.validation.Validators
import com.edu.wiet_admin.users.domain.use_case.AddStaffUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddStaffViewModel @Inject constructor(
    private val addStaffUseCase: AddStaffUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state = MutableStateFlow(AddStaffState())
        private set

    fun onEvent(event: AddStaffEvent) {
        when (event) {
            is AddStaffEvent.FirstNameChanged -> {
                state.value = state.value.copy(firstName = event.newFirstName, isFirstNameError = null)
            }
            is AddStaffEvent.MiddleNameChanged -> {
                state.value = state.value.copy(middleName = event.newMiddleName)
            }
            is AddStaffEvent.LastNameChanged -> {
                state.value = state.value.copy(lastName = event.newLastName, isLastNameError = null)
            }
            is AddStaffEvent.EmailChanged -> {
                state.value = state.value.copy(email = event.newEmail, isEmailError = null)
            }
            is AddStaffEvent.PhoneNumberChanged -> {
                state.value = state.value.copy(phoneNumber = event.newPhoneNumber, isPhoneNumberError = null)
            }
            is AddStaffEvent.GenderChanged -> {
                state.value = state.value.copy(gender = event.newGender, isGenderError = null)
            }
            is AddStaffEvent.HighestQualificationChanged -> {
                state.value = state.value.copy(highestQualification = event.newQualification)
            }
            is AddStaffEvent.RoleChanged -> {
                state.value = state.value.copy(role = event.newRole, isRoleError = null)
            }
            is AddStaffEvent.StaffImageChanged -> {
                state.value = state.value.copy(staffImageFile = event.newStaffImage)
            }
            is AddStaffEvent.DismissAlertDialog -> {
                state.value = state.value.copy(dialogText = null)
            }
            is AddStaffEvent.ResetClicked -> {
                state.value = AddStaffState()
            }
            is AddStaffEvent.GenderDropDownVisibilityChanged -> {
                state.value = state.value.copy(isGenderDropDownOpen = event.newVisibility)
            }
            is AddStaffEvent.RoleDropDownVisibilityChanged -> {
                state.value = state.value.copy(isRoleDropDownOpen = event.newVisibility)
            }
            is AddStaffEvent.StaffImageUriUpdated -> {
                handleStaffImageUpdate(event.updatedUri)
            }
            is AddStaffEvent.SubmitClicked -> {
                addStaff()
            }
        }
    }

    private fun handleStaffImageUpdate(updatedUri: Uri?) {
        if (updatedUri == null) {
            viewModelScope.launch {
                state.value.staffImageFile?.delete()
            }
            state.value = state.value.copy(
                staffImageFile = null,
                staffImageFileName = null,
                staffImageUri = null
            )
        } else {
            state.value = state.value.copy(isStaffFileUploading = true)
            viewModelScope.launch {
                val file: File? = UriToFileUtil.getFileFromUri(context, updatedUri)
                val fileName = UriToFileUtil.getFileNameFromUri(context, updatedUri)
                state.value = state.value.copy(
                    staffImageFile = file,
                    staffImageFileName = fileName,
                    staffImageUri = updatedUri,
                    isStaffFileUploading = false
                )
            }
        }

    }

    private fun addStaff() {
        if (isStaffDataValid()) {
            addStaffUseCase(
                firstName = state.value.firstName,
                middleName = state.value.middleName.ifBlank { null },
                lastName = state.value.lastName,
                email = state.value.email,
                phoneNumber = state.value.phoneNumber,
                gender = state.value.gender?.displayName ?: "",
                highestQualification = state.value.highestQualification.ifBlank { null },
                role = state.value.role?.displayName ?: "",
                isActive = true, // Default value as per requirements
                staffImageFile = state.value.staffImageFile
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            dialogText = result.message,
                            isSubmitting = false
                        )
                    }
                    is Resource.Loading -> {
                        state.value = state.value.copy(isSubmitting = true)
                    }
                    is Resource.Success -> {
                        state.value = state.value.copy(
                            isSubmitting = false,
                            isSubmitted = true,
                            dialogText = "Staff member added successfully"
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun isStaffDataValid(): Boolean {
        return when {
            state.value.firstName.isBlank() -> {
                state.value = state.value.copy(isFirstNameError = "First name cannot be empty")
                false
            }
            state.value.lastName.isBlank() -> {
                state.value = state.value.copy(isLastNameError = "Last name cannot be empty")
                false
            }
            state.value.email.isBlank() -> {
                state.value = state.value.copy(isEmailError = "Email cannot be empty")
                false
            }
            state.value.phoneNumber.isBlank() -> {
                state.value = state.value.copy(isPhoneNumberError = "Phone number cannot be empty")
                false
            }
            state.value.gender == null -> {
                state.value = state.value.copy(isGenderError = "Gender cannot be empty")
                false
            }
            state.value.role == null -> {
                state.value = state.value.copy(isRoleError = "Role cannot be empty")
                false
            }
            Validators.validateEmail(state.value.email) != null -> {
                state.value = state.value.copy(isEmailError = Validators.validateEmail(state.value.email))
                false
            }
            else -> true
        }
    }
}