package com.attendify_admin.home.feature_users.presentation.search_student.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
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
import com.attendify_admin.common.presentation.ComponentPreview
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyTextDivider


@Composable
fun StudentCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    studentName: String,
    studentBranch: String,
    studentYear: String? = null,
    studentImageUrl: String? = null,
) {
    val painter = rememberAsyncImagePainter(R.drawable.baseline_account_circle_24)

    var imageLoaded by remember { mutableStateOf(false) }

    ListItem(
        headlineContent = {
            Text(
                text = studentName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        supportingContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = studentBranch,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                studentYear?.let { AttendifyTextDivider() }
                Text(
                    text = studentYear ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        leadingContent = {
            Log.d("image", "image for $studentName")
            AsyncImage(
                model = studentImageUrl,
                contentDescription = "Student Image",
                modifier = Modifier.size(50.dp).clip(CircleShape),
                placeholder = painter,
                error = painter,
                fallback = painter,
                onLoading = null,
                onSuccess = { imageLoaded = true },
                onError = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                alpha = DefaultAlpha,
                colorFilter = if (!imageLoaded) ColorFilter.tint(
                    MaterialTheme.colorScheme.onSurface.copy(
                        alpha = .5f
                    )
                ) else null
            )

        },
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = UiConstants.MAX_WIDTH)
            .background(MaterialTheme.colorScheme.background)
            .clickable(onClick = onClick),
        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
    )
}


@ComponentPreview
@Composable
fun SearchStudentScreenPreview() {
    PreviewWrapper {
        StudentCard(
            modifier = Modifier,
            onClick = {},
            studentBranch = "Comp. Engg.",
            studentName = "Shivam Pandey",
            studentYear = "TE",
            studentImageUrl = null,
        )
    }
}