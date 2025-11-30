package com.presencify_admin.home.feature_users.presentation.unassign_subject_to_teacher.component


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
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyTextButton
import com.presencify_admin.home.feature_users.presentation.unassign_subject_to_teacher.CourseData

@Composable
fun CourseCard(
    courseData: CourseData,
    modifier: Modifier = Modifier,
    onUnassignClick: () -> Unit = {},
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
                    visible = courseData.isFailedToUnassign,
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
                courseData.isUnassigningCourse -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp)
                    )
                }

                courseData.isUnassigned -> {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Unassigned",
                        tint = Color.Green
                    )
                }

                courseData.isFailedToUnassign -> {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Failed",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                else -> {
                    PresencifyTextButton(
                        onClick = onUnassignClick
                    ) {
                        Text("Unassign", color = MaterialTheme.colorScheme.primary)
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

