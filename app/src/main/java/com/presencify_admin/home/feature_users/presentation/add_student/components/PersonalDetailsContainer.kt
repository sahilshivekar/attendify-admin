package com.presencify_admin.home.feature_users.presentation.add_student.components

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.presencify_admin.R
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyTextButton
import com.presencify_admin.common.presentation.components.PresencifyTextField
import com.presencify_admin.common.utils.DateTimeUtil
import com.presencify_admin.home.feature_users.presentation.add_student.AddStudentEvent
import com.presencify_admin.home.feature_users.presentation.add_student.AddStudentState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDetailsContainer(
    modifier: Modifier = Modifier,
    onEvent: (AddStudentEvent) -> Unit,
    state: AddStudentState,
) {
    // for image input
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onEvent(AddStudentEvent.StudentImageUriUpdated(uri))
        }
    )

    if (state.isImageVisible) {

        Dialog(
            onDismissRequest = {
                onEvent(AddStudentEvent.CloseImageClicked)
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surface)
                    .widthIn(max = UiConstants.MAX_WIDTH)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = state.studentImageUri,
                    contentDescription = "Student image",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PresencifyTextButton(
                        onClick = {
                            onEvent(AddStudentEvent.CloseImageClicked)
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Close")
                    }

                    Button(
                        onClick = {
                            onEvent(AddStudentEvent.CloseImageClicked)
                            onEvent(AddStudentEvent.StudentImageUriUpdated(null))
                        },
                        modifier = Modifier.padding(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        )
                    ) {
                        Text("Remove")
                    }
                }

            }
        }
    }

    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val datePickerState = rememberDatePickerState(
            selectableDates = object : SelectableDates {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis <= System.currentTimeMillis()
                }
            }
        )

        if (state.isDatePickerVisible) {
            DatePickerDialog(
                onDismissRequest = {
                    onEvent(AddStudentEvent.DatePickerVisibilityChanged)
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onEvent(AddStudentEvent.DatePickerVisibilityChanged)
                            datePickerState.selectedDateMillis?.let { millis ->
                                onEvent(
                                    AddStudentEvent.DobChanged(
                                        DateTimeUtil.datePickerMillisToYYYYMMDD(
                                            millis
                                        )
                                    )
                                )
                            }
                            localFocusManager.clearFocus()
                        },

                        ) {
                        Text("Select")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            onEvent(AddStudentEvent.DatePickerVisibilityChanged)
                            localFocusManager.clearFocus()
                        },
                    ) {
                        Text("Dismiss")
                    }
                },
                modifier = Modifier.fillMaxSize(),
                colors = DatePickerDefaults.colors(

                )
            ) {
                DatePicker(
                    state = datePickerState
                )
            }
        }

        Text(
            text = if (state.studentId == null) "Fill student's personal details" else "Edit student's personal details",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.Start)
        )

        // Declare these at the top of your @Composable before UI code
        val firstNameRequester = remember { FocusRequester() }
        val lastNameRequester = remember { FocusRequester() }
        val middleNameRequester = remember { FocusRequester() }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PresencifyTextField(
                value = state.firstName,
                onValueChange = { onEvent(AddStudentEvent.FirstNameChanged(it)) },
                label = "First Name",
                modifier = Modifier
                    .weight(.5f)
                    .focusRequester(firstNameRequester),
                enabled = !state.isSubmitting && !state.isSubmitted,
                supportingText = state.isFirstNameError,
                isError = state.isFirstNameError != null,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { lastNameRequester.requestFocus() }
                )
            )
            PresencifyTextField(
                value = state.lastName,
                onValueChange = { onEvent(AddStudentEvent.LastNameChanged(it)) },
                label = "Last Name",
                modifier = Modifier
                    .weight(.5f)
                    .focusRequester(lastNameRequester),
                enabled = !state.isSubmitting && !state.isSubmitted,
                supportingText = state.isLastNameError,
                isError = state.isLastNameError != null,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { middleNameRequester.requestFocus() }
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PresencifyTextField(
                value = state.middleName,
                onValueChange = { onEvent(AddStudentEvent.MiddleNameChanged(it)) },
                label = "Middle Name",
                modifier = Modifier
                    .weight(.5f)
                    .focusRequester(middleNameRequester),
                enabled = !state.isSubmitting && !state.isSubmitted,
                supportingText = state.isMiddleNameError,
                isError = state.isMiddleNameError != null
            )




            ExposedDropdownMenuBox(
                expanded = state.isGenderDropDownOpen,
                onExpandedChange = { onEvent(AddStudentEvent.GenderDropDownVisibilityChanged(it)) },
                modifier = Modifier
                    .weight(.5f)
            ) {
                PresencifyTextField(
                    value = state.gender,
                    onValueChange = { },
                    label = "Gender",
                    readOnly = true,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable), // this causes recomposition
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

                    state.genderOptions.forEach { option ->
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


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            PresencifyTextField(
                value = if (state.dob == null) "" else state.dob.toString(),
                onValueChange = {},
                label = "Date of Birth",
                modifier = Modifier
                    .weight(.5f),
                enabled = !state.isSubmitting && !state.isSubmitted,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onEvent(AddStudentEvent.DatePickerVisibilityChanged)
                        },
                        enabled = !state.isSubmitting && !state.isSubmitted
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
            if (state.studentId == null) {

                PresencifyTextField(
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .weight(.5f),
                    readOnly = true,
                    leadingIcon = {
                        Text(
                            text = state.studentImageFileName ?: "Add Image",
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .clickable(state.studentImageFileName != null) {
                                    onEvent(AddStudentEvent.ShowImageClicked)
                                },
                            color = if (state.studentImageFileName != null) MaterialTheme.colorScheme.onBackground else colorResource(
                                id = R.color.text_field_border_label
                            ),
                            style = if (state.studentImageFileName != null) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                        )
                    },
                    trailingIcon = {
                        if (state.studentImageUri == null) {
                            IconButton(
                                onClick = {
                                    launcher.launch("image/*")
                                },
                                enabled = !state.isSubmitting && !state.isSubmitted
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            }
        }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@PreviewScreenSizes
@Composable
private fun PersonalDetailsContainerPreview() {
    PreviewWrapper {
        PersonalDetailsContainer(
            onEvent = {},
            state = AddStudentState()
        )
    }
}


