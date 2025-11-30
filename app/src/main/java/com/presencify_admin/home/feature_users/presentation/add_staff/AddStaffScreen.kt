package com.presencify_admin.home.feature_users.presentation.add_staff

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.presencify_admin.R
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifyDropDownMenuBox
import com.presencify_admin.common.presentation.components.PresencifyOutlinedButton
import com.presencify_admin.common.presentation.components.PresencifyTextButton
import com.presencify_admin.common.presentation.components.PresencifyTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStaffScreen(
    modifier: Modifier = Modifier,
    onEvent: (AddStaffEvent) -> Unit,
    state: AddStaffState,
    onAddStaffSuccess: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(state.isSubmitted, state.dialogText) {
        if (state.isSubmitted && state.dialogText == null) {
            onAddStaffSuccess()
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onEvent(AddStaffEvent.StaffImageUriUpdated(uri))
        }
    )





    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.widthIn(max = UiConstants.MAX_WIDTH),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (state.isImageVisible) {

                Dialog(
                    onDismissRequest = {
                        onEvent(AddStaffEvent.CloseImageClicked)
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
                    ) {
                        AsyncImage(
                            model = state.staffImageUri,
                            contentDescription = "Student image",
                            modifier = Modifier.fillMaxSize()
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PresencifyTextButton(
                                onClick = {
                                    onEvent(AddStaffEvent.CloseImageClicked)
                                },
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text("Close")
                            }

                            Button(
                                onClick = {
                                    onEvent(AddStaffEvent.CloseImageClicked)
                                    onEvent(AddStaffEvent.StaffImageUriUpdated(null))
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

            Text(
                text = if (state.staffId == null) "Fill staff member details" else "Edit staff member details",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.Start)
            )

            // Declare these at the top of your @Composable function before UI code
            val firstNameRequester = remember { FocusRequester() }
            val middleNameRequester = remember { FocusRequester() }
            val lastNameRequester = remember { FocusRequester() }
            val emailRequester = remember { FocusRequester() }
            val phoneRequester = remember { FocusRequester() }
            val qualificationRequester = remember { FocusRequester() }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PresencifyTextField(
                    value = state.firstName,
                    onValueChange = { onEvent(AddStaffEvent.FirstNameChanged(it)) },
                    label = "First Name",
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(firstNameRequester),
                    supportingText = state.isFirstNameError,
                    isError = state.isFirstNameError != null,
                    enabled = !state.isSubmitted && !state.isSubmitting,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { middleNameRequester.requestFocus() }
                    )
                )
                PresencifyTextField(
                    value = state.middleName,
                    onValueChange = { onEvent(AddStaffEvent.MiddleNameChanged(it)) },
                    label = "Middle Name",
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(middleNameRequester),
                    enabled = !state.isSubmitted && !state.isSubmitting,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { lastNameRequester.requestFocus() }
                    )
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PresencifyTextField(
                    value = state.lastName,
                    onValueChange = { onEvent(AddStaffEvent.LastNameChanged(it)) },
                    label = "Last Name",
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(lastNameRequester),
                    supportingText = state.isLastNameError,
                    isError = state.isLastNameError != null,
                    enabled = !state.isSubmitted && !state.isSubmitting,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { phoneRequester.requestFocus() }
                    )
                )
                if(state.staffId == null) {

                    PresencifyTextField(
                        modifier = Modifier.weight(1f),
                        enabled = !state.isSubmitting && !state.isSubmitted,
                        value = "",
                        onValueChange = { },
                        readOnly = true,
                        leadingIcon = {
                            Text(
                                text = state.staffImageFileName ?: "Add Image",
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .weight(.7f)
                                    .clickable(state.staffImageFileName != null) {
                                        onEvent(AddStaffEvent.ShowImageClicked)
                                    },
                                color = if (state.staffImageFileName != null) MaterialTheme.colorScheme.onBackground else colorResource(
                                    id = R.color.text_field_border_label
                                ),
                                style = if (state.staffImageFileName != null) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                            )
                        },
                        trailingIcon = {
                            if (state.staffImageUri == null) {
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



            ExposedDropdownMenuBox(
                expanded = state.isCountryCodeDropDownOpen,
                onExpandedChange = { onEvent(AddStaffEvent.CountryCodeDropDownVisibilityChanged(it)) },
            ) {

                PresencifyTextField(
                    value = state.country?.name ?: "",
                    onValueChange = { },
                    label = "Country",
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(
                            MenuAnchorType.PrimaryEditable
                        ),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state.isCountryCodeDropDownOpen,
                            modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                        )
                    },
                    isError = state.isCountryError != null,
                    supportingText = state.isCountryError
                )
                val dropDownScrollState = rememberScrollState()
                ExposedDropdownMenu(
                    expanded = state.isCountryCodeDropDownOpen,
                    onDismissRequest = {
                        onEvent(
                            AddStaffEvent.CountryCodeDropDownVisibilityChanged(
                                false
                            )
                        )
                        localFocusManager.clearFocus()
                    },
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrollState = dropDownScrollState,
                    modifier = Modifier.heightIn(max = 300.dp)
                ) {
                    state.countryCodeOptions.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                onEvent(AddStaffEvent.PhoneNumberCountryCodeChanged(option))
                                onEvent(AddStaffEvent.CountryCodeDropDownVisibilityChanged(false))
                                localFocusManager.clearFocus()
                            },
                            text = {
                                Text(
                                    text = option.name,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            },
                            trailingIcon = {
                                Text(
                                    text = option.phoneCode,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        )
                    }
                }
            }

            PresencifyTextField(
                value = state.phoneNumber,
                onValueChange = { onEvent(AddStaffEvent.PhoneNumberChanged(it)) },
                label = "Phone Number",
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(phoneRequester),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { emailRequester.requestFocus() }
                ),
                supportingText = state.isPhoneNumberError,
                isError = state.isPhoneNumberError != null,
                enabled = !state.isSubmitted && !state.isSubmitting,
                prefix = {
                    Text(state.country?.phoneCode ?: "")
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PresencifyDropDownMenuBox(
                    modifier = Modifier.weight(1f),
                    expanded = state.isGenderDropDownOpen,
                    onDropDownVisibilityChanged = {
                        onEvent(AddStaffEvent.GenderDropDownVisibilityChanged(it))
                    },
                    value = state.gender ?: "",
                    enabled = !state.isSubmitted && !state.isSubmitting,
                    supportingText = state.isGenderError,
                    options = Gender.entries,
                    onSelectItem = {
                        onEvent(AddStaffEvent.GenderChanged(it))
                    },
                    label = "Gender"
                )

                PresencifyDropDownMenuBox(
                    modifier = Modifier.weight(1f),
                    expanded = state.isRoleDropDownOpen,
                    onDropDownVisibilityChanged = {
                        onEvent(AddStaffEvent.RoleDropDownVisibilityChanged(it))
                    },
                    value = state.role ?: "",
                    enabled = !state.isSubmitted && !state.isSubmitting,
                    supportingText = state.isRoleError,
                    options = StaffRole.entries,
                    onSelectItem = {
                        onEvent(AddStaffEvent.RoleChanged(it))
                    },
                    label = "Role"
                )
            }

            PresencifyTextField(
                value = state.email,
                onValueChange = { onEvent(AddStaffEvent.EmailChanged(it)) },
                label = "Email",
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(emailRequester),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { qualificationRequester.requestFocus() }
                ),
                supportingText = state.isEmailError,
                isError = state.isEmailError != null,
                enabled = !state.isSubmitted && !state.isSubmitting
            )

            PresencifyTextField(
                value = state.highestQualification,
                onValueChange = { onEvent(AddStaffEvent.HighestQualificationChanged(it)) },
                label = "Highest Qualification",
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(qualificationRequester),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { onEvent(AddStaffEvent.SubmitClicked) }
                ),
                enabled = !state.isSubmitted && !state.isSubmitting
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = UiConstants.MAX_WIDTH)
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PresencifyOutlinedButton(
                    onClick = {
                        onEvent(AddStaffEvent.ResetClicked)
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
                PresencifyButton(
                    onClick = { onEvent(AddStaffEvent.SubmitClicked) },
                    text = "Submit",
                    modifier = Modifier
                        .weight(.5f)
                        .padding(start = 4.dp),
                    isLoading = state.isSubmitting,
                    enabled = !state.isSubmitting && !state.isSubmitted,
                )
            }

        }
    }

}

@PreviewScreenSizes
@Composable
fun AddStaffScreenPreview() {
    MaterialTheme {
        AddStaffScreen(
            onEvent = {},
            state = AddStaffState(
                firstName = "John",
                lastName = "Doe",
                email = "john.doe@example.com",
                phoneNumber = "1234567890",
                highestQualification = "PhD",
                gender = Gender.MALE.toString(),
                role = StaffRole.HEAD_OF_DEPARTMENT.toString()
            ),
            onAddStaffSuccess = {}
        )
    }
}