package com.attendify_admin.home.feature_users.presentation.staff_details.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.attendify_admin.R
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyTextButton
import com.attendify_admin.home.feature_users.presentation.staff_details.StaffDetailsEvent
import com.attendify_admin.home.feature_users.presentation.staff_details.StaffDetailsState

@Composable
fun StaffImageContainer(
    modifier: Modifier = Modifier,
    state: StaffDetailsState,
    onEvent: (StaffDetailsEvent) -> Unit,
) {
    Box(
        modifier = Modifier.widthIn(max = UiConstants.MAX_WIDTH),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val painter = rememberAsyncImagePainter(R.drawable.baseline_account_circle_24)
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                onEvent(StaffDetailsEvent.StaffNewImageUploaded(uri))
            }

        if (state.isImageDialogVisible) {
            Dialog(
                onDismissRequest = { onEvent(StaffDetailsEvent.ToggleImageDialog) },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp)
                        .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                        .widthIn(max = UiConstants.MAX_WIDTH),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = if (state.newUploadedImageFileUri == null) state.staff?.staffImageUrl else state.newUploadedImageFileUri,
                        placeholder = painter,
                        error = painter,
                        fallback = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AttendifyTextButton(
                            onClick = { onEvent(StaffDetailsEvent.ToggleImageDialog) },
                        ) {
                            Text("Dismiss")
                        }
                        if (state.staff?.staffImageUrl != null) {
                            AttendifyTextButton(
                                modifier = Modifier.wrapContentSize(),
                                onClick = { onEvent(StaffDetailsEvent.RemoveImageClicked) },
                                enabled = !state.isUpdatingImage && !state.isRemovingImage
                            ) {
                                if (state.isRemovingImage) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(20.dp),
                                        strokeWidth = 2.dp,
                                    )
                                } else {
                                    Text(
                                        text = "Remove Image",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        } else if (state.newUploadedImageFileUri == null) {
                            Button(
                                modifier = Modifier.wrapContentSize(),
                                onClick = { launcher.launch("image/*") },
                                enabled = !state.isUpdatingImage && !state.isRemovingImage
                            ) {
                                Text("Add Image")
                            }
                        } else {
                            Button(
                                modifier = Modifier.wrapContentSize(),
                                onClick = { onEvent(StaffDetailsEvent.UpdateStaffImageClicked) },
                                enabled = !state.isUpdatingImage && !state.isRemovingImage
                            ) {
                                if (state.isUpdatingImage) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(20.dp),
                                        strokeWidth = 2.dp,
                                    )
                                } else {
                                    Text(text = "Upload Image")
                                }
                            }
                        }
                    }
                }
            }
        }

        AsyncImage(
            model = state.staff?.staffImageUrl,
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            onSuccess = { onEvent(StaffDetailsEvent.StaffImageFetchedFromUrl) },
            colorFilter = if (state.isImageFetchedFromUrl && state.staff?.staffImageUrl != null) null else ColorFilter.tint(
                MaterialTheme.colorScheme.onSurface.copy(
                    alpha = .5f
                )
            ),
            placeholder = painter,
            error = painter,
            fallback = painter
        )
        IconButton(
            onClick = { onEvent(StaffDetailsEvent.ToggleImageDialog) },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}