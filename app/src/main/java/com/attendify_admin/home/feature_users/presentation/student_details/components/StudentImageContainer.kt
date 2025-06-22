package com.attendify_admin.home.feature_users.presentation.student_details.components

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
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.attendify_admin.R
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyTextButton
import com.attendify_admin.home.feature_users.presentation.student_details.StudentDetailsEvent
import com.attendify_admin.home.feature_users.presentation.student_details.StudentDetailsState

@Composable
fun StudentImageContainer(
    modifier: Modifier = Modifier,
    state: StudentDetailsState,
    onEvent: (StudentDetailsEvent) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onEvent(StudentDetailsEvent.StudentNewImageUploaded(uri))
        }
    )
    val painter =
        rememberAsyncImagePainter(R.drawable.baseline_account_circle_24)


    if (state.isImageDialogVisible) {
        Dialog(
            onDismissRequest = { onEvent(StudentDetailsEvent.ToggleImageDialog) },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Column(
                modifier = modifier
                    .widthIn(max = UiConstants.MAX_WIDTH)
                    .wrapContentHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // belows if condition is needed don't use ?: it will result in unwanted state
                AsyncImage(
                    model = if (state.newUploadedImageFileUri == null) state.student?.studentImgUrl else state.newUploadedImageFileUri,
                    placeholder = painter,
                    fallback = painter,
                    error = painter,
                    contentDescription = "StudentImage",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AttendifyTextButton(
                        onClick = {
                            onEvent(StudentDetailsEvent.ToggleImageDialog)
                        },
                    ) {
                        Text("Dismiss")
                    }
                    if (state.student?.studentImgUrl != null) {
                        AttendifyTextButton(
                            modifier = Modifier.wrapContentSize(),
                            onClick = {
                                onEvent(StudentDetailsEvent.RemoveImageClicked)
                            },
                            enabled = !state.isStudentImageFileUploading && !state.isRemovingImage
                        ) {
                            if (state.isRemovingImage) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp,
                                )
                            } else {
                                Text(text = "Remove Image", color = MaterialTheme.colorScheme.error)
                            }
                        }
                    } else if (state.newUploadedImageFileUri == null) {
                        Button(
                            modifier = Modifier.wrapContentSize(),
                            onClick = {
                                launcher.launch(input = "image/*")
                            },
                            enabled = !state.isStudentImageFileUploading && !state.isRemovingImage
                        ) {
                            Text(text = "Add Image")
                        }
                    } else {
                        Button(
                            modifier = Modifier.wrapContentSize(),
                            onClick = {
                                onEvent(StudentDetailsEvent.UpdateStudentImageClicked)
                            },
                            enabled = !state.isStudentImageFileUploading && !state.isRemovingImage,
                        ) {
                            if (state.isStudentImageFileUploading) {
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

    Box(
        modifier = Modifier.widthIn(max = UiConstants.MAX_WIDTH),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            AsyncImage(
                model = state.student?.studentImgUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                onSuccess = { onEvent(StudentDetailsEvent.StudentImageFetchedFromUrl) },
                colorFilter = if (state.isImageFetchedFromUrl && state.student?.studentImgUrl != null) null else ColorFilter.tint(
                    MaterialTheme.colorScheme.onSurface.copy(
                        alpha = .5f
                    )
                ),
                placeholder = painter,
                error = painter,
                fallback = painter
            )

        IconButton(
            onClick = {
                onEvent(StudentDetailsEvent.ToggleImageDialog)
            },
            modifier = Modifier.align(alignment = Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@PreviewScreenSizes
@Composable
fun StudentImageContainerPreview() {
    PreviewWrapper {
        StudentImageContainer(
            state = StudentDetailsState(),
            onEvent = {}
        )
    }
}
