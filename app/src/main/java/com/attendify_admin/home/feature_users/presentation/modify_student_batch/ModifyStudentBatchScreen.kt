package com.attendify_admin.home.feature_users.presentation.modify_student_batch

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
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.components.ChangeStudentBatch
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.components.SelectBatchToAssign
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.components.SelectCurrentBatch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyStudentBatchScreen(
    modifier: Modifier = Modifier,
    state: ModifyStudentBatchState,
    onEvent: (ModifyStudentBatchEvent) -> Unit,
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
                    onEvent(ModifyStudentBatchEvent.DatePickerVisibilityChanged)
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onEvent(ModifyStudentBatchEvent.DatePickerVisibilityChanged)
                            datePickerState.selectedDateMillis?.let { millis ->
                                onEvent(
                                    ModifyStudentBatchEvent.DateChanged(
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
                            onEvent(ModifyStudentBatchEvent.DatePickerVisibilityChanged)
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
                    SelectCurrentBatch(
                        state = state,
                        onEvent = onEvent
                    )
                }

                2 -> {
                    SelectBatchToAssign(
                        state = state,
                        onEvent = onEvent
                    )
                }

                3 -> {
                    ChangeStudentBatch(
                        state = state,
                        onEvent = onEvent
                    )
                }
            }
        }
    }
}
