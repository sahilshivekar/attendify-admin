package com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyTextButton
import com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher.CourseData

@Composable
fun CourseCard(
    courseData: CourseData,
    modifier: Modifier = Modifier,
    onAssignClick: () -> Unit = {},
) {
    ListItem(
        headlineContent = {
            Text(
                text = courseData.courseName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        supportingContent = {
            Column {
                Text(
                    text = courseData.courseCode,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                courseData.schemeName?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                AnimatedVisibility(
                    visible = courseData.isFailedToAssign,
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Text(
                        text = courseData.supportingText,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        trailingContent = {
            when {
                courseData.isAssigningCourse -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp)
                    )
                }

                courseData.isAssigned -> {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Assigned",
                        tint = Color.Green
                    )
                }

                courseData.isFailedToAssign -> {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Failed",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                else -> {
                    AttendifyTextButton(
                        onClick = onAssignClick
                    ) {
                        Text("Assign", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = UiConstants.MAX_WIDTH)
            .clip(MaterialTheme.shapes.medium),
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
    )
}

