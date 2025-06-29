package com.attendify_admin.home.feature_users.presentation.remove_student_from_semester.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.attendify_admin.R
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyTextButton

@Composable
fun StudentCard(
    studentId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    studentName: String,
    studentImageUrl: String? = null,
    isUnassigned: Boolean = false,
    isFailedToUnassign: Boolean = false,
    isUnassigning: Boolean = false,
    studentSemesterId: Int? = null,
    supportingText: String = "",
    onUnassignClicked: (Int) -> Unit,
) {
    val painter = rememberAsyncImagePainter(R.drawable.baseline_account_circle_24)

    var imageLoaded by remember { mutableStateOf(false) }

    ListItem(
        headlineContent = {
            Text(
                text = studentName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Companion.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        supportingContent = {
            AnimatedVisibility(
                visible = isFailedToUnassign,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Text(
                    text = supportingText,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        leadingContent = {
            AsyncImage(
                model = studentImageUrl,
                contentDescription = "Student Image",
                modifier = Modifier.Companion
                    .size(42.dp)
                    .clip(CircleShape),
                placeholder = painter,
                error = painter,
                fallback = painter,
                onLoading = null,
                onSuccess = { if (studentImageUrl != null) imageLoaded = true },
                onError = null,
                alignment = Alignment.Companion.Center,
                contentScale = ContentScale.Companion.Crop,
                alpha = DefaultAlpha,
                colorFilter = if (!imageLoaded) ColorFilter.Companion.tint(
                    MaterialTheme.colorScheme.onSurface.copy(
                        alpha = .5f
                    )
                ) else null
            )

        },
        trailingContent = {
            AttendifyTextButton(
                onClick = { studentSemesterId?.let { onUnassignClicked(it) } },
                enabled = !isUnassigned && !isUnassigning,
            ) {
                if (isUnassigning) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp)
                    )
                } else if (isUnassigned) {
                    Text(
                        text = "Unassigned", color = Color.Green
                    )
                } else {
                    Text(
                        text = "Unassign", color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },

        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = UiConstants.MAX_WIDTH)
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick),
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
    )
}


