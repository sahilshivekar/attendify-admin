package com.attendify_admin.home.feature_users.presentation.add_student

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyOutlinedButton
import com.attendify_admin.home.feature_users.presentation.add_student.components.AcademicDetailsContainer
import com.attendify_admin.home.feature_users.presentation.add_student.components.ContactDetailsContainer
import com.attendify_admin.home.feature_users.presentation.add_student.components.PersonalDetailsContainer

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentScreen(
    modifier: Modifier = Modifier,
    onEvent: (AddStudentEvent) -> Unit,
    state: AddStudentState,
    onAddStudentSuccess: () -> Unit,
) {

    LaunchedEffect(
        state.isSubmitted
    ) {
        if (state.isSubmitted) {
            onAddStudentSuccess()
        }
    }

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        AnimatedContent(
            modifier = Modifier.padding(top = 16.dp),
            targetState = state.currStep,
            transitionSpec = {
                if (targetState.ordinal > initialState.ordinal) {
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

                AddStudentSteps.PERSONAL_DETAILS -> {
                    PersonalDetailsContainer(
                        onEvent = onEvent,
                        state = state
                    )
                }

                AddStudentSteps.CONTACT_DETAILS -> {
                    ContactDetailsContainer(
                        onEvent = onEvent,
                        state = state
                    )
                }

                AddStudentSteps.ACADEMIC_DETAILS -> {
                    AcademicDetailsContainer(
                        onEvent = onEvent,
                        state = state
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .widthIn(max = UiConstants.MAX_WIDTH)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (state.currStep == AddStudentSteps.ACADEMIC_DETAILS ||
                state.currStep == AddStudentSteps.CONTACT_DETAILS
            ) {
                AttendifyOutlinedButton(
                    onClick = {
                        onEvent(AddStudentEvent.BackClicked)
                    },
                    enabled = !state.isSubmitting && !state.isSubmitted,
                    modifier = Modifier
                        .weight(.5f)
                        .padding(end = 4.dp)
                ) {
                    Text(
                        text = "Back",
                    )
                }
            }
            val currStep = state.currStep
            AttendifyButton(
                onClick = {
                    if (currStep == AddStudentSteps.ACADEMIC_DETAILS) {
                        onEvent(AddStudentEvent.SubmitClicked)
                    } else {
                        onEvent(AddStudentEvent.ValidateFields)
                    }
                },
                text = if (currStep == AddStudentSteps.ACADEMIC_DETAILS) "Submit" else "Next",
                isLoading = state.isSubmitting,
                enabled = !state.isSubmitting && !state.isSubmitted,
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp)
            )

        }

    }
}


@PreviewScreenSizes
@Composable
fun UserDashboardScreenPreview() {
    PreviewWrapper {
        AddStudentScreen(Modifier, {}, AddStudentState(), {})
    }
}