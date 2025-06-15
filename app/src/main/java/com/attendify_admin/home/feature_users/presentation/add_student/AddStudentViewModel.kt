package com.attendify_admin.home.feature_users.presentation.add_student

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.model.Student
import com.attendify_admin.common.utils.FileUtil
import com.attendify_admin.common.validation.Validators
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetSchemesUseCase
import com.attendify_admin.home.feature_users.domain.use_case.AddStudentUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentDetailsByIdUseCase
import com.attendify_admin.home.feature_users.domain.use_case.RemoveStudentImageUseCase
import com.attendify_admin.home.feature_users.domain.use_case.UpdateStudentDetailsUseCase
import com.attendify_admin.home.feature_users.domain.use_case.UpdateStudentImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddStudentViewModel @Inject constructor(
    private val addStudentUseCase: AddStudentUseCase,
    getBranchesUseCase: GetBranchesUseCase,
    getSchemesUseCase: GetSchemesUseCase,
    private val getStudentDetailsByIdUseCase: GetStudentDetailsByIdUseCase,
    private val updateStudentImageUseCase: UpdateStudentImageUseCase,
    private val removeStudentImageUseCase: RemoveStudentImageUseCase,
    private val updateStudentDetailsUseCase: UpdateStudentDetailsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state = MutableStateFlow(AddStudentState())
        private set

    init {
        getBranchesUseCase(searchQuery = null).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(dialogText = result.message)
                }

                is Resource.Loading -> {
                    // do nothing let it load in the background until the user is filling above details
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        branchOptions = result?.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
        getSchemesUseCase(searchQuery = "").onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(dialogText = result.message)
                }

                is Resource.Loading -> {
                    // do nothing let it load in the background until the user is filling above details
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        schemeOptions = result.data ?: emptyList()
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setStudentId(studentId: Int) {
        state.value = state.value.copy(
            studentId = studentId
        )
        getStudent(studentId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getStudent(studentId: Int) {
        var student: Student? = null
        getStudentDetailsByIdUseCase(studentId = studentId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value = state.value.copy(
                        dialogText = result.message
                    )
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(
                        isLoadingInitialStudentDetails = true
                    )
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        isLoadingInitialStudentDetails = false,
                    )
                    student = result.data
                    var dob: LocalDate? = null

                    student?.dob?.let {
                        val year = student?.dob?.substring(0, 4)?.toInt()
                        val month = student?.dob?.substring(5, 7)?.toInt()
                        val day = student?.dob?.substring(8, 10)?.toInt()
                        if (year != null && month != null && day != null) {
                            dob = LocalDate.of(
                                year,
                                month,
                                day
                            )
                        }
                    }

                    //image, branch and scheme remaining
                    state.value = state.value.copy(
                        prn = student?.prn.toString(),
                        firstName = student?.firstName.toString(),
                        middleName = student?.middleName.toString(),
                        lastName = student?.lastName.toString(),
                        email = student?.email.toString(),
                        phoneNumber = student?.phoneNumber.toString(),
                        gender = student?.gender.toString(),
                        dob = dob,
                        admissionType = student?.admissionType.toString(),
//                        academicStatus = student?.academicStatus.toString(),
                        admissionYear = student?.admissionYear.toString(),
                        selectedBranch = student?.branch,
                        selectedScheme = student?.scheme,
                    )
                }

            }
        }.launchIn(viewModelScope)
    }


    @SuppressLint("NewApi")
    fun onEvent(event: AddStudentEvent) {
        when (event) {
            is AddStudentEvent.PrnChanged -> {
                state.value = state.value.copy(prn = event.newPrn, isPRNError = null)
            }

            is AddStudentEvent.FirstNameChanged -> {
                state.value =
                    state.value.copy(firstName = event.newFirstName, isFirstNameError = null)
            }

            is AddStudentEvent.MiddleNameChanged -> {
                state.value =
                    state.value.copy(middleName = event.newMiddleName, isMiddleNameError = null)
            }

            is AddStudentEvent.LastNameChanged -> {
                state.value = state.value.copy(lastName = event.newLastName, isLastNameError = null)
            }

            is AddStudentEvent.EmailChanged -> {
                state.value = state.value.copy(email = event.newEmail, isEmailError = null)
            }

            is AddStudentEvent.PhoneNumberChanged -> {
                state.value =
                    state.value.copy(phoneNumber = event.newPhoneNumber, isPhoneNumberError = null)
            }

            is AddStudentEvent.GenderChanged -> {
                state.value = state.value.copy(gender = event.newGender, isGenderError = null)
            }

            is AddStudentEvent.DobChanged -> {
                state.value = state.value.copy(dob = event.newDob, isDobError = null)
            }

            is AddStudentEvent.AdmissionYearChanged -> {
                state.value = state.value.copy(
                    admissionYear = event.newAdmissionYear,
                    isAdmissionYearError = null
                )
            }

            is AddStudentEvent.AdmissionTypeChanged -> {
                state.value = state.value.copy(
                    admissionType = event.newAdmissionType,
                    isAdmissionTypeError = null
                )
            }

            is AddStudentEvent.BranchChanged -> {
                state.value =
                    state.value.copy(selectedBranch = event.newBranch, isBranchError = null)
            }

            is AddStudentEvent.SchemeChanged -> {
                state.value =
                    state.value.copy(selectedScheme = event.newScheme, isSchemeError = null)
            }

            is AddStudentEvent.StudentImageChanged -> {
                state.value = state.value.copy(studentImageFile = event.newStudentImage)
            }

            is AddStudentEvent.DatePickerVisibilityChanged -> {
                state.value =
                    state.value.copy(isDatePickerVisible = !state.value.isDatePickerVisible)
            }

            is AddStudentEvent.DismissAlertDialog -> {
                state.value = state.value.copy(dialogText = null)
            }

            is AddStudentEvent.ResetClicked -> {
                state.value = AddStudentState()
            }


            is AddStudentEvent.GenderDropDownVisibilityChanged -> {
                state.value = state.value.copy(isGenderDropDownOpen = event.newVisibility)
            }

            is AddStudentEvent.PhoneNumberCountryCodeChanged -> {
                state.value =
                    state.value.copy(phoneNumberCountryCode = event.newPhoneNumberCountryCode)
            }

            is AddStudentEvent.CountryCodeDropDownVisibilityChanged -> {
                state.value = state.value.copy(isCountryCodeDropDownOpen = event.newVisibility)
            }

            is AddStudentEvent.AdmissionYearDropDownVisibilityChanged -> {
                state.value = state.value.copy(isAdmissionYearDropDownOpen = event.newVisibility)
            }

            is AddStudentEvent.AdmissionTypeDropDownVisibilityChanged -> {
                state.value = state.value.copy(isAdmissionTypeDropDownOpen = event.newVisibility)
            }

            is AddStudentEvent.BranchDropDownVisibilityChanged -> {
                state.value = state.value.copy(isBranchDropDownOpen = event.newVisibility)
            }

            is AddStudentEvent.SchemeDropDownVisibilityChanged -> {
                state.value = state.value.copy(isSchemeDropDownOpen = event.newVisibility)
            }

            is AddStudentEvent.StudentImageUriUpdated -> {
                if (event.updatedUri == null) {
                    viewModelScope.launch {
                        state.value.studentImageFile?.delete()
                    }
                    state.value = state.value.copy(
                        studentImageFile = null,
                        studentImageFileName = null,
                        studentImageUri = null
                    )
                } else {
                    state.value = state.value.copy(isStudentFileUploading = true)
                    viewModelScope.launch {
                        val file: File? = FileUtil.getFileFromUri(
                            context,
                            event.updatedUri
                        )
                        val fileName = FileUtil.getFileNameFromUri(
                            context,
                            event.updatedUri
                        )
                        state.value = state.value.copy(
                            studentImageFile = file,
                            studentImageFileName = fileName,
                            studentImageUri = event.updatedUri,
                            isStudentFileUploading = false
                        )
                    }
                }
            }


            is AddStudentEvent.SubmitClicked -> {
                addStudent()
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addStudent() {
        if (isStudentDataValid()) {
            Log.d("in if", "valid hai")
            addStudentUseCase(
                prn = state.value.prn,
                firstName = state.value.firstName,
                middleName = state.value.middleName,
                lastName = state.value.lastName,
                email = state.value.email,
                phoneNumber = state.value.phoneNumber,
                gender = state.value.gender,
                dob = state.value.dob?.toString(),
                schemeId = state.value.selectedScheme!!.id,
                admissionYear = state.value.admissionYear,
                admissionType = state.value.admissionType,
                branchId = state.value.selectedBranch!!.id,
                studentImageFile = state.value.studentImageFile,
            ).onEach {
                when (it) {
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            dialogText = it.message,
                            isSubmitting = false
                        )
                    }

                    is Resource.Loading -> {
                        state.value = state.value.copy(
                            isSubmitting = true
                        )
                    }

                    is Resource.Success -> {
                        state.value = state.value.copy(
                            isSubmitting = false,
                            isSubmitted = true,
                            dialogText = "Student added successfully"
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun isStudentDataValid(): Boolean {
        return when {

            state.value.firstName.isBlank() -> {
                state.value = state.value.copy(isFirstNameError = "First name cannot be empty")
                false
            }

            state.value.lastName.isBlank() -> {
                state.value = state.value.copy(isLastNameError = "Last name cannot be empty")
                false
            }

            state.value.prn.isBlank() -> {
                state.value = state.value.copy(isPRNError = "PRN cannot be empty")
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

            state.value.gender.isBlank() -> {
                state.value = state.value.copy(isGenderError = "Gender cannot be empty")
                false
            }

            state.value.admissionYear.isBlank() -> {
                state.value =
                    state.value.copy(isAdmissionYearError = "Admission year cannot be empty")
                false
            }

            state.value.admissionType.isBlank() -> {
                state.value =
                    state.value.copy(isAdmissionTypeError = "Admission type cannot be empty")
                false
            }

            state.value.selectedBranch == null -> {
                state.value = state.value.copy(isBranchError = "Branch cannot be empty")
                false
            }

            state.value.selectedScheme == null -> {
                state.value = state.value.copy(isSchemeError = "Scheme cannot be empty")
                false
            }

            Validators.validateEmail(state.value.email) != null -> {
                state.value =
                    state.value.copy(isEmailError = Validators.validateEmail(state.value.email))
                false
            }

            else -> true
        }

    }

}
