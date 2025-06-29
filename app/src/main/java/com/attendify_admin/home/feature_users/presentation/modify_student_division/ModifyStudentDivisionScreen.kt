package com.attendify_admin.home.feature_users.presentation.modify_student_division

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.utils.DateTimeUtil
import com.attendify_admin.home.feature_users.presentation.modify_student_division.components.ChangeStudentDivision
import com.attendify_admin.home.feature_users.presentation.modify_student_division.components.SelectCurrentDivision
import com.attendify_admin.home.feature_users.presentation.modify_student_division.components.SelectDivisionToAssign

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyStudentDivisionScreen(
    modifier: Modifier = Modifier,
    state: ModifyStudentDivisionState,
    onEvent: (ModifyStudentDivisionEvent) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val datePickerState = rememberDatePickerState()
        if (state.isDatePickerVisible) {
            DatePickerDialog(
                onDismissRequest = {
                    onEvent(ModifyStudentDivisionEvent.DatePickerVisibilityChanged)
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onEvent(ModifyStudentDivisionEvent.DatePickerVisibilityChanged)
                            datePickerState.selectedDateMillis?.let { millis ->
                                onEvent(
                                    ModifyStudentDivisionEvent.DateChanged(
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
                            onEvent(ModifyStudentDivisionEvent.DatePickerVisibilityChanged)
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

        AnimatedContent(
            modifier = Modifier
                .padding(top = 16.dp)
                .weight(1f),
            targetState = state.currStep,
            transitionSpec = {
                if (targetState > initialState) {
                    // Navigating FORWARD: New screen slides in from RIGHT, old screen slides out to LEFT
                    ContentTransform(
                        targetContentEnter = slideInHorizontally { fullWidth -> fullWidth } + fadeIn(),
                        initialContentExit = slideOutHorizontally { fullWidth -> -fullWidth } + fadeOut()
                    )
                } else {
                    // Navigating BACKWARD: New screen slides in from LEFT, old screen slides out to RIGHT
                    ContentTransform(
                        targetContentEnter = slideInHorizontally { fullWidth -> -fullWidth } + fadeIn(),
                        initialContentExit = slideOutHorizontally { fullWidth -> fullWidth } + fadeOut()
                    )
                }
            }
        ) { targetState ->
            when (targetState) {

                1 -> {
                    SelectCurrentDivision(
                        state = state,
                        onEvent = onEvent
                    )
                }

                2 -> {
                    SelectDivisionToAssign(
                        state = state,
                        onEvent = onEvent
                    )
                }

                3 -> {
                    ChangeStudentDivision(
                        state = state,
                        onEvent = onEvent
                    )
                }
            }
        }

//        Spacer(Modifier.height(12.dp))

//        Row(
//            modifier = Modifier
//                .widthIn(max = UiConstants.MAX_WIDTH)
//                .padding(bottom = 16.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            if (state.currStep == 2 ||
//                state.currStep == 3
//            ) {
//                AttendifyOutlinedButton(
//                    onClick = {
//                        onEvent(ModifyStudentDivisionEvent.BackClicked)
//                    },
//                    modifier = Modifier
//                        .weight(.5f)
//                        .padding(end = 4.dp),
//                ) {
//                    Text(
//                        text = "Back",
//                    )
//                }
//            }
//            if (state.currStep == 2 ||
//                state.currStep == 1
//            ) {
//                AttendifyButton(
//                    onClick = {
//                        onEvent(ModifyStudentDivisionEvent.NextClicked)
//                    },
//                    text = "Next",
//                    modifier = Modifier
//                        .weight(.5f)
//                        .padding(start = 4.dp),
//                    enabled = when (state.currStep) {
//                        1 -> {
//                            state.selectedCurrentDivision != null
//                        }
//
//                        2 -> {
//                            state.selectedNewDivision != null
//                        }
//                        else -> {
//                            true
//                        }
//                    }
//                )
//            }
//        }
    }
}

