package com.attendify_admin.home.feature_users.presentation.add_student

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.attendify_admin.R
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.ScreenPreview
import com.attendify_admin.common.presentation.components.AttendifyAlertDialog
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyDatePicker
import com.attendify_admin.common.presentation.components.AttendifyOutlinedButton
import com.attendify_admin.common.presentation.components.AttendifyTextField

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentScreen(
    modifier: Modifier = Modifier,
    onEvent: (AddStudentEvent) -> Unit,
    state: AddStudentState,
    onAddStudentSuccess: () -> Unit
) {
    if (state.dialogText != null) {
        AttendifyAlertDialog(
            dialogText = state.dialogText,
            onDismiss = { onEvent(AddStudentEvent.DismissAlertDialog) }
        )
    }

    if(state.isSubmitted && state.dialogText == null){
        onAddStudentSuccess()
    }

    if (state.isDatePickerVisible) {
        AttendifyDatePicker(
            onCancelClicked = {
                onEvent(AddStudentEvent.DatePickerVisibilityChanged)
            },
            onDateSelected = { localDate ->
                onEvent(AddStudentEvent.DobChanged(localDate))
                onEvent(AddStudentEvent.DatePickerVisibilityChanged)
            },
            selectedLocalDate = state.dob
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onEvent(AddStudentEvent.StudentImageUriUpdated(uri))
        }
    )


    val scrollState = rememberScrollState()
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Fill student details",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.Start)
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AttendifyTextField(
                value = state.firstName,
                onValueChange = { onEvent(AddStudentEvent.FirstNameChanged(it)) },
                label = "First Name",
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp),
                enabled = !state.isSubmitting && !state.isSubmitted,
                supportingText = state.isFirstNameError,
                isError = state.isFirstNameError != null
            )
            AttendifyTextField(
                value = state.lastName,
                onValueChange = { onEvent(AddStudentEvent.LastNameChanged(it)) },
                label = "Last Name",
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp),
                enabled = !state.isSubmitting && !state.isSubmitted,
                supportingText = state.isLastNameError,
                isError = state.isLastNameError != null
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AttendifyTextField(
                value = state.middleName,
                onValueChange = { onEvent(AddStudentEvent.MiddleNameChanged(it)) },
                label = "Middle Name",
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp),
                enabled = !state.isSubmitting && !state.isSubmitted,
                supportingText = state.isMiddleNameError,
                isError = state.isMiddleNameError != null
            )
            AttendifyTextField(
                value = state.prn,
                onValueChange = { onEvent(AddStudentEvent.PrnChanged(it)) },
                label = "PRN",
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp),
                enabled = !state.isSubmitting && !state.isSubmitted,
                supportingText = state.isPRNError,
                isError = state.isPRNError != null
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AttendifyTextField(
                value = if (state.dob == null) "" else state.dob.toString(),
                onValueChange = {},
                label = "Date of Birth",
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp),
                enabled = !state.isSubmitting && !state.isSubmitted,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onEvent(AddStudentEvent.DatePickerVisibilityChanged)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                    }
                },
                readOnly = true,
                maxLines = 1,
                supportingText = state.isDobError,
                isError = state.isDobError != null
            )
            ExposedDropdownMenuBox(
                expanded = state.isGenderDropDownOpen,
                onExpandedChange = { onEvent(AddStudentEvent.GenderDropDownVisibilityChanged(it)) },
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp)
            ) {
                AttendifyTextField(
                    value = state.gender,
                    onValueChange = { onEvent(AddStudentEvent.PrnChanged(it)) },
                    label = "Gender",
                    readOnly = true,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth().menuAnchor(MenuAnchorType.PrimaryEditable),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isGenderDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isGenderError,
                    isError = state.isGenderError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isGenderDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.GenderDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    val options = listOf("Male", "Female", "Other")
                    options.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                onEvent(AddStudentEvent.GenderChanged(option))
                                onEvent(AddStudentEvent.GenderDropDownVisibilityChanged(false))
                                localFocusManager.clearFocus()
                            },
                            text = {
                                Text(text = option, color = MaterialTheme.colorScheme.onSurface)
                            }
                        )
                    }
                }
            }

        }

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .widthIn(max = 600.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            ExposedDropdownMenuBox(
//                expanded = state.isCountryCodeDropDownOpen,
//                onExpandedChange = { onEvent(AddStudentEvent.CountryCodeDropDownVisibilityChanged(it)) },
//                modifier = Modifier
//                    .weight(.4f)
//                    .padding(end = 4.dp)
//            ) {

//                AttendifyTextField(
//                    value = state.phoneNumberCountryCode,
//                    onValueChange = { onEvent(AddStudentEvent.PhoneNumberCountryCodeChanged(it)) },
//                    label = "Country Code",
//                    readOnly = true,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(end = 4.dp),
//                    trailingIcon = {
//                        ExposedDropdownMenuDefaults.TrailingIcon(
//                            expanded = state.isCountryCodeDropDownOpen,
//                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
//                        )
//                    }
//                )
//                val dropDownScrollState = rememberScrollState()
//                ExposedDropdownMenu(
//                    expanded = state.isCountryCodeDropDownOpen,
//                    onDismissRequest = {
//                        onEvent(
//                            AddStudentEvent.CountryCodeDropDownVisibilityChanged(
//                                false
//                            )
//                        )
//                    },
//                    shape = MaterialTheme.shapes.medium,
//                    containerColor = MaterialTheme.colorScheme.surface,
//                    scrollState = dropDownScrollState,
//                    modifier = Modifier.heightIn(max = 100.dp)
//                ) {
//                    state.countryCodeOptions.forEach { option ->
//                        DropdownMenuItem(
//                            onClick = {
//                                onEvent(AddStudentEvent.PhoneNumberCountryCodeChanged(option.phoneCode))
//                                onEvent(AddStudentEvent.CountryCodeDropDownVisibilityChanged(false))
//                            },
//                            text = {
//                                Text(
//                                    text = option.code,
//                                    color = MaterialTheme.colorScheme.onSurface
//                                )
//                            },
//                            leadingIcon = {
//                                Text(
//                                    text = option.phoneCode,
//                                    color = MaterialTheme.colorScheme.onSurface
//                                )
//                            }
//                        )
//                    }
//                }
//            }

//        AttendifyTextField(
//            value = state.phoneNumber,
//            onValueChange = { onEvent(AddStudentEvent.PhoneNumberChanged(it)) },
//            label = "Phone Number",
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                    .weight(.6f)
//                    .padding(start = 4.dp)
//        )
//        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp),
                expanded = state.isAdmissionYearDropDownOpen,
                onExpandedChange = {
                    onEvent(
                        AddStudentEvent.AdmissionYearDropDownVisibilityChanged(
                            it
                        )
                    )
                }
            ) {

                AttendifyTextField(
                    value = state.admissionYear,
                    onValueChange = { onEvent(AddStudentEvent.AdmissionYearChanged(it)) },
                    label = "Admission Year",
                    modifier = Modifier.fillMaxWidth().menuAnchor(MenuAnchorType.PrimaryEditable),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isAdmissionYearDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    maxLines = 1,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isAdmissionYearError,
                    isError = state.isAdmissionYearError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isAdmissionYearDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.AdmissionYearDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.heightIn(max = 160.dp),
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    state.admissionYearOptions.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = {
                                onEvent(AddStudentEvent.AdmissionYearChanged(it))
                                onEvent(AddStudentEvent.AdmissionYearDropDownVisibilityChanged(false))
                                localFocusManager.clearFocus()
                            }

                        )
                    }
                }

            }

            ExposedDropdownMenuBox(
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp),
                expanded = state.isAdmissionTypeDropDownOpen,
                onExpandedChange = {
                    onEvent(
                        AddStudentEvent.AdmissionTypeDropDownVisibilityChanged(
                            it
                        )
                    )
                }
            ) {
                AttendifyTextField(
                    value = state.admissionType,
                    onValueChange = { onEvent(AddStudentEvent.AdmissionTypeChanged(it)) },
                    label = "Admission Type",
                    modifier = Modifier.fillMaxWidth().menuAnchor(MenuAnchorType.PrimaryEditable),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isAdmissionTypeDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    maxLines = 1,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isAdmissionTypeError,
                    isError = state.isAdmissionTypeError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isAdmissionTypeDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.AdmissionTypeDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    state.admissionTypeOptions.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = {
                                onEvent(AddStudentEvent.AdmissionTypeChanged(it))
                                onEvent(AddStudentEvent.AdmissionTypeDropDownVisibilityChanged(false))
                                localFocusManager.clearFocus()
                            }
                        )
                    }
                }

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            // here add code for image taking

            AttendifyTextField(
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp),
                enabled = !state.isSubmitting && !state.isSubmitted,
                value = "",
                onValueChange = { },
                readOnly = true,
                trailingIcon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = state.studentImageFileName ?: "Add Image",
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(.7f),
                            color = if (state.studentImageFileName != null) MaterialTheme.colorScheme.onBackground else colorResource(
                                id = R.color.text_field_border_label
                            ),
                            style = if (state.studentImageFileName != null) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                        )

                        IconButton(
                            onClick = {
                                if (state.isStudentFileUploading) {
                                    // if loading do nothing
                                } else if (state.studentImageUri == null) {
                                    launcher.launch("image/*")
                                } else {
                                    onEvent(AddStudentEvent.StudentImageUriUpdated(null))
                                }
                            },
                            modifier = Modifier.weight(.3f)
                        ) {
                            if (!state.isStudentFileUploading) {
                                Icon(
                                    imageVector = if (state.studentImageFileName != null) Icons.Default.Close else Icons.Default.Add,
                                    contentDescription = null
                                )
                            } else {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .size(20.dp)
                                )
                            }
                        }
                    }
                }
            )
        }

        AttendifyTextField(
            value = state.phoneNumber,
            onValueChange = { onEvent(AddStudentEvent.PhoneNumberChanged(it)) },
            label = "Phone Number",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            enabled = !state.isSubmitting && !state.isSubmitted,
            supportingText = state.isPhoneNumberError,
            isError = state.isPhoneNumberError != null
        )

        AttendifyTextField(
            value = state.email,
            onValueChange = { onEvent(AddStudentEvent.EmailChanged(it)) },
            label = "Email",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            enabled = !state.isSubmitting && !state.isSubmitted,
            supportingText = state.isEmailError,
            isError = state.isEmailError != null
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp),
                expanded = state.isBranchDropDownOpen,
                onExpandedChange = {
                    onEvent(
                        AddStudentEvent.BranchDropDownVisibilityChanged(
                            it
                        )
                    )
                }
            ) {

                AttendifyTextField(
                    value = state.selectedBranch?.abbreviation ?: "",
                    onValueChange = { },
                    label = "Branch",
                    modifier = Modifier.fillMaxWidth().menuAnchor(MenuAnchorType.PrimaryEditable),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isBranchDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    maxLines = 1,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isBranchError,
                    isError = state.isBranchError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isBranchDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.BranchDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.heightIn(max = 160.dp),
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    state.branchOptions.forEach { branch ->
                        DropdownMenuItem(
                            text = {
                                branch?.let {
                                    Text(text = branch.name)
                                }
                            },
                            onClick = {
                                onEvent(AddStudentEvent.BranchChanged(newBranch = branch))
                                onEvent(
                                    AddStudentEvent.BranchDropDownVisibilityChanged(
                                        false
                                    )
                                )
                                localFocusManager.clearFocus()
                            }
                        )
                    }
                }
            }


            ExposedDropdownMenuBox(
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp),
                expanded = state.isSchemeDropDownOpen,
                onExpandedChange = {
                    onEvent(
                        AddStudentEvent.SchemeDropDownVisibilityChanged(
                            it
                        )
                    )
                }
            ) {

                AttendifyTextField(
                    value = state.selectedScheme?.name ?: "",
                    onValueChange = { },
                    label = "Scheme",
                    modifier = Modifier.fillMaxWidth().menuAnchor(MenuAnchorType.PrimaryEditable),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isSchemeDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    maxLines = 1,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    supportingText = state.isSchemeError,
                    isError = state.isSchemeError != null
                )
                ExposedDropdownMenu(
                    expanded = state.isSchemeDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStudentEvent.SchemeDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    modifier = Modifier.heightIn(max = 160.dp),
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    state.schemeOptions.forEach { scheme ->
                        DropdownMenuItem(
                            text = {
                                scheme?.let {
                                    Text(text = scheme.name)
                                }
                            },
                            onClick = {
                                onEvent(AddStudentEvent.SchemeChanged(newScheme = scheme))
                                onEvent(
                                    AddStudentEvent.SchemeDropDownVisibilityChanged(
                                        false
                                    )
                                )
                                localFocusManager.clearFocus()
                            }
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AttendifyOutlinedButton(
                onClick = {
                    onEvent(AddStudentEvent.ResetClicked)
                },
                enabled = !state.isSubmitting && !state.isSubmitted,
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = "Reset",
                )
            }
            AttendifyButton(
                onClick = {
                    onEvent(AddStudentEvent.SubmitClicked)
                },
                text = "Add Student",
                isLoading = state.isSubmitting,
                enabled = !state.isSubmitting && !state.isSubmitted,
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp)
            )
        }
    }
}


@ScreenPreview
@Composable
fun UserDashboardScreenPreview() {
    PreviewWrapper {
        AddStudentScreen(Modifier, {}, AddStudentState(), {})
    }
}