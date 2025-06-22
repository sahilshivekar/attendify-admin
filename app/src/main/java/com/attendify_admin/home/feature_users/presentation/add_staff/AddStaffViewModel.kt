package com.attendify_admin.home.feature_users.presentation.add_staff

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.common.utils.FileUtil
import com.attendify_admin.common.utils.PhoneUtil.getCountryByPhoneCode
import com.attendify_admin.common.validation.ValidateEmail
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStaffDetailsRequest
import com.attendify_admin.home.feature_users.domain.use_case.AddStaffUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStaffByIdUseCase
import com.attendify_admin.home.feature_users.domain.use_case.UpdateStaffDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStaffViewModel @Inject constructor(
    private val addStaffUseCase: AddStaffUseCase,
    @ApplicationContext private val context: Context,
    private val getStaffByIdUseCase: GetStaffByIdUseCase,
    private val updateStaffDetailsUseCase: UpdateStaffDetailsUseCase,
    savedStateHandle: SavedStateHandle,
    private val validateEmail: ValidateEmail,
) : ViewModel() {

    private val _state = MutableStateFlow(AddStaffState())
    val state: StateFlow<AddStaffState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("staffId")?.let { staffId ->
            _state.update { it.copy(staffId = staffId) }
            getStaff(staffId)
        }
    }

    private fun getStaff(staffId: Int) {
        getStaffByIdUseCase(staffId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(isLoadingInitialStaffDetails = false) }
                    SnackbarController.sendEvent(
                        SnackbarEvent(
                            result.message ?: "Failed to load staff details"
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoadingInitialStaffDetails = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoadingInitialStaffDetails = false,
                        )
                    }
                    if (result.data != null) {
                        val staff = result.data
                        val phone = staff.phoneNumber.reversed().substring(0, 10).reversed()
                        val countryCode = staff.phoneNumber.replace(phone, "")
                        _state.update {
                            it.copy(
                                firstName = staff.firstName,
                                middleName = staff.middleName ?: "",
                                lastName = staff.lastName,
                                email = staff.email,
                                phoneNumber = phone,
                                country = getCountryByPhoneCode(countryCode),
                                gender = staff.gender,
                                highestQualification = staff.highestQualification ?: "",
                                role = staff.role,
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: AddStaffEvent) {
        when (event) {
            is AddStaffEvent.FirstNameChanged -> {
                _state.update { // Used .update
                    it.copy(firstName = event.newFirstName, isFirstNameError = null)
                }
            }

            is AddStaffEvent.MiddleNameChanged -> {
                _state.update { // Used .update
                    it.copy(middleName = event.newMiddleName)
                }
            }

            is AddStaffEvent.LastNameChanged -> {
                _state.update { // Used .update
                    it.copy(lastName = event.newLastName, isLastNameError = null)
                }
            }

            is AddStaffEvent.EmailChanged -> {
                _state.update { // Used .update
                    it.copy(email = event.newEmail, isEmailError = null)
                }
            }

            is AddStaffEvent.PhoneNumberChanged -> {
                _state.update { // Used .update
                    it.copy(phoneNumber = event.newPhoneNumber, isPhoneNumberError = null)
                }
            }

            is AddStaffEvent.GenderChanged -> {
                _state.update { // Used .update
                    it.copy(gender = event.newGender.toString(), isGenderError = null)
                }
            }

            is AddStaffEvent.HighestQualificationChanged -> {
                _state.update { // Used .update
                    it.copy(highestQualification = event.newQualification)
                }
            }

            is AddStaffEvent.RoleChanged -> {
                _state.update { // Used .update
                    it.copy(role = event.newRole.toString(), isRoleError = null)
                }
            }

            is AddStaffEvent.ResetClicked -> {
                _state.update { AddStaffState() }
            }

            is AddStaffEvent.GenderDropDownVisibilityChanged -> {
                _state.update {
                    it.copy(isGenderDropDownOpen = event.newVisibility)
                }
            }

            is AddStaffEvent.RoleDropDownVisibilityChanged -> {
                _state.update {
                    it.copy(isRoleDropDownOpen = event.newVisibility)
                }
            }

            is AddStaffEvent.StaffImageUriUpdated -> {
                handleStaffImageUpdate(event.updatedUri)
            }

            is AddStaffEvent.SubmitClicked -> {
                if (state.value.staffId == null) {
                    addStaff()
                } else {
                    updateStaff()
                }
            }

            AddStaffEvent.CloseImageClicked -> {
                _state.update {
                    it.copy(isImageVisible = false)
                }
            }

            is AddStaffEvent.CountryCodeDropDownVisibilityChanged -> _state.update {
                it.copy(
                    isCountryCodeDropDownOpen = event.newVisibility
                )
            }

            is AddStaffEvent.PhoneNumberCountryCodeChanged -> _state.update {
                it.copy(
                    country = event.newCountry,
                    isCountryError = null
                )
            }

            AddStaffEvent.ShowImageClicked -> {
                _state.update {
                    it.copy(
                        isImageVisible = true
                    )
                }
            }
        }
    }

    private fun handleStaffImageUpdate(updatedUri: Uri?) {
        if (updatedUri == null) {
            _state.update {
                it.copy(
                    staffImageFileName = null,
                    staffImageUri = null
                )
            }
        } else {
            viewModelScope.launch {
                val fileName = FileUtil.getFileNameFromUri(context, updatedUri)
                _state.update {
                    it.copy(
                        staffImageFileName = fileName,
                        staffImageUri = updatedUri,
                    )
                }
            }
        }
    }

    private fun addStaff() {
        if (isStaffDataValid()) {
            val staffImageUri = _state.value.staffImageUri
            addStaffUseCase(
                firstName = _state.value.firstName,
                middleName = _state.value.middleName.ifBlank { null },
                lastName = _state.value.lastName,
                email = _state.value.email,
                phoneNumber = state.value.country!!.phoneCode + _state.value.phoneNumber,
                gender = _state.value.gender!!,
                highestQualification = _state.value.highestQualification.ifBlank { null },
                role = _state.value.role!!,
                isActive = true,
                staffImageFile = if (staffImageUri != null) FileUtil.getFileFromUri(
                    context, staffImageUri
                ) else null
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isSubmitting = false
                            )
                        }
                        viewModelScope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = result.message ?: "An unknown error occurred"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isSubmitting = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isSubmitting = false,
                                isSubmitted = true
                            )
                        }
                        viewModelScope.launch { // Snackbar event
                            SnackbarController.sendEvent(SnackbarEvent(message = "Staff member added successfully"))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun updateStaff() {
        if (isStaffDataValid()) {
            val requestBody = UpdateStaffDetailsRequest(
                id = _state.value.staffId!!,
                firstName = _state.value.firstName,
                middleName = _state.value.middleName.ifBlank { null },
                lastName = _state.value.lastName,
                email = _state.value.email,
                phoneNumber = state.value.country!!.phoneCode + _state.value.phoneNumber,
                gender = _state.value.gender!!,
                highestQualification = _state.value.highestQualification.ifBlank { null },
                role = _state.value.role!!,
                isActive = true
            )
            updateStaffDetailsUseCase(
                requestBody
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isSubmitting = false
                            )
                        }
                        viewModelScope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = result.message ?: "An unknown error occurred"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isSubmitting = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isSubmitting = false,
                                isSubmitted = true
                            )
                        }
                        viewModelScope.launch { // Snackbar event
                            SnackbarController.sendEvent(SnackbarEvent(message = "Staff member updated successfully"))
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun isStaffDataValid(): Boolean {
        val currentFirstName = _state.value.firstName
        val currentLastName = _state.value.lastName
        val currentEmail = _state.value.email
        val currentPhoneNumber = _state.value.phoneNumber
        val currentGender = _state.value.gender
        val currentRole = _state.value.role
        val emailResult = validateEmail(state.value.email)

        return when {
            currentFirstName.isBlank() -> {
                _state.update {
                    it.copy(isFirstNameError = "First name cannot be empty")
                }
                false
            }

            currentLastName.isBlank() -> {
                _state.update {
                    it.copy(isLastNameError = "Last name cannot be empty")
                }
                false
            }

            currentEmail.isBlank() -> {
                _state.update {
                    it.copy(isEmailError = "Email cannot be empty")
                }
                false
            }

            currentPhoneNumber.isBlank() -> {
                _state.update {
                    it.copy(isPhoneNumberError = "Phone number cannot be empty")
                }
                false
            }

            currentGender == null -> {
                _state.update {
                    it.copy(isGenderError = "Gender cannot be empty")
                }
                false
            }

            currentRole == null -> {
                _state.update {
                    it.copy(isRoleError = "Role cannot be empty")
                }
                false
            }

            state.value.country == null -> {
                _state.update { it.copy(isCountryError = "Country code is required") }
                false
            }

            state.value.phoneNumber.isBlank() -> {
                _state.update { it.copy(isPhoneNumberError = "Phone number is required") }
                false
            }

            state.value.phoneNumber.length < 10 -> { // Basic validation, consider more robust validation
                _state.update { it.copy(isPhoneNumberError = "Phone number is invalid") }
                false
            }

            !emailResult.successful -> {
                _state.update { it.copy(isEmailError = emailResult.errorMessage) }

                false
            }

            else -> true
        }
    }
}