package com.attendify_admin.home.feature_users.presentation.staff_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.home.feature_users.presentation.staff_details.StaffDetailsState


@Composable
fun StaffAssignedSubjects(
    modifier: Modifier = Modifier,
    state: StaffDetailsState,
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp)
            .widthIn(max = UiConstants.MAX_WIDTH),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Assigned subjects",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp)
            )

        }
        if (state.assignedCourses.isEmpty()) {
            Text(
                text = "No courses assigned",
                modifier = Modifier.padding(16.dp)
            )
        }

        Column {
            state.assignedCourses.forEachIndexed { idx, courseData ->
                CourseCard(
                    courseData = courseData,
                )
                if (idx < state.assignedCourses.size - 1) {
                    HorizontalDivider()
                }
            }
        }

    }
}