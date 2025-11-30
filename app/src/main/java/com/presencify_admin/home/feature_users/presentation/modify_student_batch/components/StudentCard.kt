package com.presencify_admin.home.feature_users.presentation.modify_student_batch.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.presencify_admin.R
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyTextButton
import com.presencify_admin.common.utils.DateTimeUtil
import com.presencify_admin.home.feature_users.presentation.modify_student_batch.ModifyStudentBatchState


@Composable
fun StudentCard(
    studentId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    studentName: String,
    studentImageUrl: String? = null,
    isChanged: Boolean = false,
    isFailedToChange: Boolean = false,
    isChanging: Boolean = false,
    studentBatchId: Int? = null,
    supportingText: String = "Failed To Change",
    onChangeClicked: (Int) -> Unit,
    state: ModifyStudentBatchState,
    currentBatchStartDate: String? = null,
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
            Column {
                currentBatchStartDate?.let {
                    Text(
                        text = "Current div start date: ${
                            DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(
                                it
                            )
                        }",
                    )
                }
                AnimatedVisibility(
                    visible = isFailedToChange,
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Text(
                        text = supportingText,
                        color = MaterialTheme.colorScheme.error
                    )
                }
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
            PresencifyTextButton(
                onClick = { studentBatchId?.let { onChangeClicked(it) } },
                enabled = !isChanged && !isChanging && state.newBatchStartDate != null,
            ) {
                if (isChanging) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else if (isChanged) {
                    Text(
                        text = "Changed", color = Color.Green
                    )
                } else {
                    Text(
                        text = "Change", color = MaterialTheme.colorScheme.error
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

