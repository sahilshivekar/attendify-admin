package com.attendify_admin.home.feature_users.presentation.staff_details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.domain.model.Staff
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.components.AttendifyTextButton
import com.attendify_admin.home.feature_users.presentation.staff_details.components.StaffDetailsContainer
import com.attendify_admin.home.feature_users.presentation.staff_details.components.StaffImageContainer

@Composable
fun StaffDetailsScreen(
    modifier: Modifier = Modifier,
    state: StaffDetailsState,
    onEvent: (StaffDetailsEvent) -> Unit,
    onEditStaffDetails: () -> Unit,
    navigateUp: () -> Unit,
) {

    LaunchedEffect(state.isStaffMemberRemoved) {
        if (state.isStaffMemberRemoved) navigateUp()
    }

    AnimatedContent(
        targetState = state.isLoadingInitialStaffDetails,
        transitionSpec = {
            (slideInVertically { it / 4 } + fadeIn()).togetherWith(fadeOut())
        }
    ) { targetState ->
        if (targetState) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                StaffImageContainer(state = state, onEvent = onEvent)


                Spacer(modifier = Modifier.height(16.dp))

                StaffDetailsContainer(state = state)


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AttendifyTextButton(
                        onClick = onEditStaffDetails,
                        enabled = !state.isRemovingStaffMember
                    ) {
                        Text("Edit details", color = MaterialTheme.colorScheme.primary)
                    }
                    AttendifyTextButton(
                        onClick = { onEvent(StaffDetailsEvent.RemoveStaffClicked) },
                        enabled = !state.isRemovingStaffMember
                    ) {
                        if (state.isRemovingStaffMember) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                            )
                        } else {
                            Text(
                                text = "Remove staff member",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }


}


@Preview
@Composable
fun StaffDetailsScreenPreview() {
    PreviewWrapper {
        StaffDetailsScreen(
            state = StaffDetailsState(
                staff = Staff(
                    id = 1,
                    email = "jane.doe@example.com",
                    firstName = "Jane",
                    middleName = "Marie",
                    lastName = "Doe",
                    gender = "Female",
                    highestQualification = "M.Tech",
                    isActive = true,
                    password = "hashedPassword",
                    phoneNumber = "+919876543210",
                    refreshToken = "",
                    role = "Teacher",
                    staffImagePublicId = null,
                    staffImageUrl = null
                )
            ),
            onEvent = {},
            onEditStaffDetails = {},
            navigateUp = {}
        )
    }
}


