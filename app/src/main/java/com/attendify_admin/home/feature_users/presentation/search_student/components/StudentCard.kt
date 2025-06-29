package com.attendify_admin.home.feature_users.presentation.search_student.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
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
    isSelected: Boolean = false,
    showCheckmark: Boolean = false,
//    isRemovable: Boolean = false,
//    onRemove: () -> Unit = {},
//    showDefaultSupportingTxt: Boolean = true,
//    isSupportingTextRisky: Boolean = false,
//    supportingText: String? = null,

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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = studentBranch,
                    style = MaterialTheme.typography.bodyMedium,
                )
                studentYear?.let { AttendifyTextDivider() }
                Text(
                    text = studentYear ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
//            val textColor = if (isSupportingTextRisky) {
//                MaterialTheme.colorScheme.error
//            } else {
//                MaterialTheme.colorScheme.onSurface
//            }
//
//            when {
//                supportingText != null -> {
//                    Text(
//                        text = supportingText,
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = textColor
//                    )
//                }
//
//                showDefaultSupportingTxt -> {

//                }
//            }
        },
        leadingContent = {
            Log.d("image", "image for $studentName")
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
            if (showCheckmark) {
                Icon(
                    imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Outlined.CheckCircle,
                    contentDescription = "Select student",
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Companion.Gray
                )
            }
//            if (isRemovable) {
//                IconButton(onClick = onRemove) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = "Remove Student",
//                        tint = MaterialTheme.colorScheme.onSurface
//                    )
//                }
//            }
        },

        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = UiConstants.MAX_WIDTH)
            .background(MaterialTheme.colorScheme.background)
            .clickable(onClick = onClick),
        colors = ListItemDefaults.colors(containerColor = Color.Companion.Transparent)
    )
}


@ComponentPreview
@Composable
fun SearchStudentScreenPreview() {
    PreviewWrapper {
        StudentCard(
            modifier = Modifier.Companion,
            onClick = {},
            studentBranch = "Comp. Engg.",
            studentName = "Shivam Pandey",
            studentYear = "TE",
            studentImageUrl = null,
        )
    }
}