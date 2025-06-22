package com.attendify_admin.home.feature_users.presentation.add_student

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.common.utils.DateTimeUtil
import com.attendify_admin.common.utils.DateTimeUtil.getDateInYYYYMMDDFromDDMMYYYY
import com.attendify_admin.common.utils.FileUtil
import com.attendify_admin.common.utils.PhoneUtil.getCountryByPhoneCode
import com.attendify_admin.common.validation.ValidateEmail
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStudentDetailsRequest
import com.attendify_admin.home.feature_users.domain.use_case.AddStudentUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentDetailsByIdUseCase
import com.attendify_admin.home.feature_users.domain.use_case.UpdateStudentDetailsUseCase
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.AdmissionTypeChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.AdmissionTypeDropDownVisibilityChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.AdmissionYearChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.AdmissionYearDropDownVisibilityChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.BackClicked
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.BranchChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.BranchDropDownVisibilityChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.CloseImageClicked
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.CountryCodeDropDownVisibilityChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.DatePickerVisibilityChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.DobChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.EmailChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.FirstNameChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.GenderChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.GenderDropDownVisibilityChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.LastNameChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.MiddleNameChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.ParentEmailChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.PhoneNumberChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.PhoneNumberCountryCodeChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.PrnChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.SchemeChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.SchemeDropDownVisibilityChanged
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.ShowImageClicked
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.StudentImageUriUpdated
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.SubmitClicked
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent.ValidateFields
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddStudentViewModel @Inject constructor(
    private val getBranchesUseCase: GetBranchesUseCase,
    private val addStudentUseCase: AddStudentUseCase,
    private val validateEmail: ValidateEmail,
    private val getStudentDetailsByIdUseCase: GetStudentDetailsByIdUseCase,
    private val updateStudentDetailsUseCase: UpdateStudentDetailsUseCase,
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(AddStudentState())
    val state = _state.asStateFlow()

    init {
        getBranches()
        savedStateHandle.get<Int>("studentId")?.let { studentId ->
            if (studentId != -1) { // -1 is the default value indicating no studentId was passed
                _state.update {
                    it.copy(
                        studentId = studentId,
                    )
                }
                getStudent(studentId)
            }
        }
    }


    private fun getBranches() {
        getBranchesUseCase(null).onEach { result ->
            when (result) {
                is Resource.Error -> viewModelScope.launch {
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = result.message ?: "Error fetching branches")
                    )
                }

                is Resource.Loading -> Unit
                is Resource.Success -> _state.update {
                    it.copy(branchOptions = result.data?.toImmutableList())
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getStudent(studentId: Int) {
        getStudentDetailsByIdUseCase(studentId).onEach { result ->
            when (result) {
                is Resource.Error -> viewModelScope.launch {
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = result.message ?: "Error fetching student details")
                    )
                }

                is Resource.Loading -> _state.update { it.copy(isLoadingInitialStudentDetails = true) }
                is Resource.Success -> {
                    val student = result.data!!
                    val phone = student.phoneNumber.reversed().substring(0, 10).reversed()
                    val countryCode = student.phoneNumber.replace(phone, "")
                    _state.update {
                        it.copy(
                            isLoadingInitialStudentDetails = false,
                            prn = student.prn,
                            firstName = student.firstName,
                            middleName = student.middleName ?: "",
                            lastName = student.lastName,
                            email = student.email,
                            phoneNumber = phone,
                            gender = student.gender,
                            dob = student.dob?.let { DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(it) }
                                ?: "",
                            admissionType = AdmissionType.getTypeFromString(student.admissionType.toString()),
                            admissionYear = student.admissionYear.toString(),
                            selectedBranch = student.branch,
                            selectedScheme = student.scheme,
                            parentEmail = student.parentEmail ?: "",
                            country = getCountryByPhoneCode(countryCode)
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    @SuppressLint("NewApi")
    fun onEvent(event: AddStudentEvent) {
        when (event) {
            CloseImageClicked -> _state.update { it.copy(isImageVisible = false) }
            ShowImageClicked -> _state.update { it.copy(isImageVisible = true) }
            is PrnChanged -> _state.update { it.copy(prn = event.newPrn, isPRNError = null) }
            is FirstNameChanged -> _state.update {
                it.copy(
                    firstName = event.newFirstName,
                    isFirstNameError = null
                )
            }

            is MiddleNameChanged -> _state.update {
                it.copy(
                    middleName = event.newMiddleName,
                    isMiddleNameError = null
                )
            }

            is LastNameChanged -> _state.update {
                it.copy(
                    lastName = event.newLastName,
                    isLastNameError = null
                )
            }

            is EmailChanged -> _state.update {
                it.copy(
                    email = event.newEmail,
                    isEmailError = null
                )
            }

            is PhoneNumberChanged -> _state.update {
                it.copy(
                    phoneNumber = event.newPhoneNumber,
                    isPhoneNumberError = null
                )
            }

            is GenderChanged -> _state.update {
                it.copy(
                    gender = event.newGender,
                    isGenderError = null
                )
            }

            is DobChanged -> _state.update {
                it.copy(
                    dob = DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(event.newDob),
                    isDobError = null
                )
            }

            is AdmissionYearChanged -> _state.update {
                it.copy(
                    admissionYear = event.newAdmissionYear,
                    isAdmissionYearError = null
                )
            }

            is AdmissionTypeChanged -> _state.update {
                it.copy(
                    admissionType = AdmissionType.getTypeFromString(event.newAdmissionType),
                    isAdmissionTypeError = null
                )
            }

            is BranchChanged -> _state.update {
                it.copy(
                    selectedBranch = event.newBranch,
                    isBranchError = null
                )
            }

            is SchemeChanged -> _state.update {
                it.copy(
                    selectedScheme = event.newScheme,
                    isSchemeError = null
                )
            }

            is DatePickerVisibilityChanged -> _state.update { it.copy(isDatePickerVisible = !it.isDatePickerVisible) }


            is GenderDropDownVisibilityChanged -> _state.update { it.copy(isGenderDropDownOpen = event.newVisibility) }
            is PhoneNumberCountryCodeChanged -> _state.update {
                it.copy(
                    country = event.country,
                    isCountryError = null
                )
            }

            is CountryCodeDropDownVisibilityChanged -> _state.update {
                it.copy(
                    isCountryCodeDropDownOpen = event.newVisibility
                )
            }

            is AdmissionYearDropDownVisibilityChanged -> _state.update {
                it.copy(
                    isAdmissionYearDropDownOpen = event.newVisibility
                )
            }

            is AdmissionTypeDropDownVisibilityChanged -> _state.update {
                it.copy(
                    isAdmissionTypeDropDownOpen = event.newVisibility
                )
            }

            is BranchDropDownVisibilityChanged -> _state.update { it.copy(isBranchDropDownOpen = event.newVisibility) }
            is SchemeDropDownVisibilityChanged -> _state.update { it.copy(isSchemeDropDownOpen = event.newVisibility) }

            is StudentImageUriUpdated -> {
                if (event.updatedUri == null) {
                    _state.update { it.copy(studentImageFileName = null, studentImageUri = null) }
                } else {
                    viewModelScope.launch {
                        val fileName = FileUtil.getFileNameFromUri(context, event.updatedUri)
                        _state.update {
                            it.copy(
                                studentImageFileName = fileName,
                                studentImageUri = event.updatedUri,
                            )
                        }
                    }
                }
            }

            is SubmitClicked -> {
                if (state.value.studentId == null) addStudent()
                else updateStudent()
            }

            ValidateFields -> {
                when (state.value.currStep) {
                    AddStudentSteps.PERSONAL_DETAILS -> {
                        if (isStudentPersonalDetailsValid()) {
                            _state.update { it.copy(currStep = AddStudentSteps.CONTACT_DETAILS) }
                        }
                    }

                    AddStudentSteps.CONTACT_DETAILS -> {
                        if (isStudentContactDetailsValid()) {
                            _state.update { it.copy(currStep = AddStudentSteps.ACADEMIC_DETAILS) }
                        }
                    }

                    AddStudentSteps.ACADEMIC_DETAILS -> {
                        if (isStudentAcademicDetailsValid()) {
                            if (state.value.studentId == null) addStudent()
                            else updateStudent()
                        }
                    }
                }
            }

            BackClicked -> {
                _state.update {
                    it.copy(
                        currStep = if (it.currStep == AddStudentSteps.CONTACT_DETAILS)
                            AddStudentSteps.PERSONAL_DETAILS else AddStudentSteps.CONTACT_DETAILS
                    )
                }
            }

            is ParentEmailChanged -> _state.update {
                it.copy(
                    parentEmail = event.newEmail,
                    isParentEmailError = null
                )
            }
        }
    }

    private fun addStudent() {
        if (isStudentPersonalDetailsValid() && isStudentContactDetailsValid() && isStudentAcademicDetailsValid()) {
            val dob = state.value.dob
            val studentImageUri = state.value.studentImageUri
            addStudentUseCase(
                prn = state.value.prn,
                firstName = state.value.firstName,
                middleName = state.value.middleName,
                lastName = state.value.lastName,
                email = state.value.email,
                phoneNumber = state.value.country!!.phoneCode + state.value.phoneNumber,
                gender = state.value.gender,
                dob = if (dob == null) null else getDateInYYYYMMDDFromDDMMYYYY(dob),
                schemeId = state.value.selectedScheme!!.id,
                admissionYear = state.value.admissionYear,
                admissionType = state.value.admissionType.displayName,
                branchId = state.value.selectedBranch!!.id,
                studentImageFile = studentImageUri?.let { FileUtil.getFileFromUri(context, it) },
                parentEmail = state.value.parentEmail
            ).onEach {
                when (it) {
                    is Resource.Error -> {
                        _state.update { s -> s.copy(isSubmitting = false) }
                        viewModelScope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = it.message ?: "Failed to add student"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> _state.update { s -> s.copy(isSubmitting = true) }
                    is Resource.Success -> {
                        _state.update { s ->
                            s.copy(
                                isSubmitting = false,
                                isSubmitted = true
                            )
                        }
                        viewModelScope.launch { SnackbarController.sendEvent(SnackbarEvent(message = "Student added successfully")) }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun updateStudent() {
        if (isStudentPersonalDetailsValid() && isStudentContactDetailsValid() && isStudentAcademicDetailsValid()) {
            val dob = state.value.dob
            val requestBody = UpdateStudentDetailsRequest(
                prn = state.value.prn,
                firstName = state.value.firstName,
                middleName = state.value.middleName,
                lastName = state.value.lastName,
                email = state.value.email,
                phoneNumber = state.value.country!!.phoneCode + state.value.phoneNumber,
                gender = state.value.gender,
                dob = if (dob == null) null else getDateInYYYYMMDDFromDDMMYYYY(dob),
                schemeId = state.value.selectedScheme!!.id,
                admissionYear = state.value.admissionYear,
                admissionType = state.value.admissionType.displayName,
                branchId = state.value.selectedBranch!!.id,
                parentEmail = state.value.parentEmail,
                id = state.value.studentId!!
            )
            updateStudentDetailsUseCase(requestBody).onEach {
                when (it) {
                    is Resource.Error -> {
                        _state.update { s -> s.copy(isSubmitting = false) }
                        viewModelScope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = it.message ?: "Failed to update student"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> _state.update { s -> s.copy(isSubmitting = true) }
                    is Resource.Success -> {
                        _state.update { s ->
                            s.copy(
                                isSubmitting = false,
                                isSubmitted = true
                            )
                        }
                        viewModelScope.launch { SnackbarController.sendEvent(SnackbarEvent(message = "Student updated successfully")) }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun isStudentPersonalDetailsValid(): Boolean {
        val isValid = when {
            state.value.firstName.isBlank() -> {
                _state.update { it.copy(isFirstNameError = "First name is required") }
                false
            }

            state.value.lastName.isBlank() -> {
                _state.update { it.copy(isLastNameError = "Last name is required") }
                false
            }

            state.value.gender.isBlank() -> {
                _state.update { it.copy(isGenderError = "Gender is required") }
                false
            }

            else -> true
        }
        return isValid
    }

    private fun isStudentContactDetailsValid(): Boolean {
        val emailResult = validateEmail(state.value.email)
        val parentEmailResult = validateEmail(state.value.parentEmail)

        val isValid = when {
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

            state.value.parentEmail.isNotBlank() && !parentEmailResult.successful -> {
                _state.update { it.copy(isParentEmailError = parentEmailResult.errorMessage) }
                false
            }

            else -> true
        }
        return isValid
    }

    private fun isStudentAcademicDetailsValid(): Boolean {
        val isValid = when {
            state.value.admissionYear.isBlank() -> {
                _state.update { it.copy(isAdmissionYearError = "Admission year is required") }
                false
            }

            state.value.selectedBranch == null -> {
                _state.update { it.copy(isBranchError = "Branch is required") }
                false
            }

            state.value.selectedScheme == null -> {
                _state.update { it.copy(isSchemeError = "Scheme is required") }
                false
            }

            state.value.prn.isBlank() -> {
                _state.update { it.copy(isPRNError = "PRN is required") }
                false
            }

            else -> true
        }
        return isValid
    }
}