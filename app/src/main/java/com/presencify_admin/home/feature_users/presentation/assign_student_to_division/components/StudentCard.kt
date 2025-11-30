package com.presencify_admin.home.feature_users.presentation.assign_student_to_division.components


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
import com.presencify_admin.R
import com.presencify_admin.common.presentation.ComponentPreview
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyTextButton

@Composable
fun StudentCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    studentName: String,
//    studentBranch: String,
//    studentYear: String? = null,
    studentImageUrl: String? = null,
    onCancel: () -> Unit = {},
    isAdded: Boolean = false,
    isFailedToAdd: Boolean = false,
    supportingText: String = "",
    isAdding: Boolean = false,
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
                visible = isFailedToAdd,
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
            if (isAdding) {
                CircularProgressIndicator(
                    modifier = Modifier.Companion.size(24.dp)
                )
            } else if (isAdded) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Student Added",
                    tint = Color.Green
                )
            } else if (isFailedToAdd) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Student Failed to Add",
                    tint = MaterialTheme.colorScheme.error
                )
            } else {
                PresencifyTextButton(
                    onClick = onCancel
                ) {
                    Text(
                        text = "Cancel"
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


@ComponentPreview
@Composable
fun SearchStudentScreenPreview() {
    PreviewWrapper {
        StudentCard(
            modifier = Modifier.Companion,
            onClick = {},
            studentName = "Shivam Pandey",
            studentImageUrl = null,

            )
    }
}