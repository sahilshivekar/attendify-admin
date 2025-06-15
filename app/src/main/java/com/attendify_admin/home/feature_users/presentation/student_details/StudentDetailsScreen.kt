package com.attendify_admin.home.feature_users.presentation.student_details

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.attendify_admin.R
import com.attendify_admin.common.domain.model.Batch
import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Division
import com.attendify_admin.common.domain.model.Scheme
import com.attendify_admin.common.domain.model.Semester
import com.attendify_admin.common.domain.model.Student
import com.attendify_admin.common.domain.model.StudentBatch
import com.attendify_admin.common.domain.model.StudentDivision
import com.attendify_admin.common.domain.model.StudentSemester
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.ScreenPreview
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyTextButton

@Composable
fun StudentDetailsScreen(
    modifier: Modifier = Modifier,
    state: StudentDetailsState,
    onEvent: (StudentDetailsEvent) -> Unit,
    onEditStudentDetails: () -> Unit
) {

    if (state.student == null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onEvent(StudentDetailsEvent.StudentNewImageUploaded(uri))
        }
    )

    if (state.isImageDialogVisible) {
        Dialog(
            onDismissRequest = { onEvent(StudentDetailsEvent.ToggleImageDialog) },
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                // only changing the model in the async image painter with elvis operator is good approach
                // but that approach is working strangely in a case that case is when its showing the placeholder
                // and you update the state of the newUploadedImageFile then it just show blank hence now creating a totally diff composable
                // to show the uploaded image
                if(state.newUploadedImageFile == null){
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = state.student?.studentImgUrl,
                            contentScale = ContentScale.FillBounds,
                            placeholder = painterResource(R.drawable.baseline_account_circle_24),
                            fallback = painterResource(R.drawable.baseline_account_circle_24),
                            error = painterResource(R.drawable.baseline_account_circle_24)
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 300.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds,
                        colorFilter = if (state.isImageFetchedFromUrl) null else ColorFilter.tint(
                            MaterialTheme.colorScheme.onSurface.copy(
                                alpha = .5f
                            )
                        )
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = state.newUploadedImageFile,
                            contentScale = ContentScale.FillBounds,
                            placeholder = painterResource(R.drawable.baseline_account_circle_24),
                            fallback = painterResource(R.drawable.baseline_account_circle_24),
                            error = painterResource(R.drawable.baseline_account_circle_24)
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 300.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds,
                        colorFilter = if (state.isImageFetchedFromUrl) null else ColorFilter.tint(
                            MaterialTheme.colorScheme.onSurface.copy(
                                alpha = .5f
                            )
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                AttendifyButton(
                    onClick = {
                        if (state.newUploadedImageFile == null) {
                            launcher.launch(input = "image/*")
                        } else {
                            onEvent(StudentDetailsEvent.UpdateStudentImageClicked)
                        }
                    },
                    text = if (state.newUploadedImageFile == null) "Upload new image" else "Update Image",
                    isLoading = state.isUpdatingImage,
                    enabled = !state.isUpdatingImage && !state.isRemovingImage
                )
                if (state.student?.studentImgUrl != null) {
                    AttendifyTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onEvent(StudentDetailsEvent.RemoveImageClicked)
                        },
                        enabled = !state.isUpdatingImage && !state.isRemovingImage

                    ) {
                        Text(text = "Remove Image", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }

    state.student?.let {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        model = state.student.studentImgUrl,
                        contentScale = ContentScale.FillBounds,
                        onSuccess = { onEvent(StudentDetailsEvent.StudentImageFetchedFromUrl) },
                        placeholder = painterResource(R.drawable.baseline_account_circle_24),
                        fallback = painterResource(R.drawable.baseline_account_circle_24),
                        error = painterResource(R.drawable.baseline_account_circle_24)
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        //to understand why the padding, width and height is applied this
                        //way then make it normal (without the if condition) and you will see
                        //image is taking larger size than icons which doesn't look good on screen
                        .padding(10.dp)
                        .height(120.dp)
                        .width(120.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds,
                    colorFilter = if (state.isImageFetchedFromUrl) null else ColorFilter.tint(
                        MaterialTheme.colorScheme.onSurface.copy(
                            alpha = .5f
                        )
                    )
                )
                IconButton(
                    onClick = {
                        onEvent(StudentDetailsEvent.ToggleImageDialog)
                    },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            //personal details card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .widthIn(max = 300.dp)
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
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
                        text = "Personal Details",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 16.dp)
                    )
//                    IconButton(
//                        onClick = onEditStudentDetails
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }
                }
                // full name
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Full Name",
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(.05f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = "${state.student.firstName} ${if (state.student.middleName == null) "" else "${state.student.middleName} "}${state.student.lastName}",
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // dob
                state.student.dob?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Date of Birth",
                            modifier = Modifier.weight(.5f),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )
                        Text(
                            text = ":",
                            modifier = Modifier.weight(.05f),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )
                        Text(
                            text = state.student.dob,
                            modifier = Modifier.weight(.5f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                // gender
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Gender",
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(.05f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = state.student.gender,
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // contact details card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .widthIn(max = 300.dp)
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
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
                        text = "Contact Details",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 16.dp)
                    )
//                    IconButton(
//                        onClick = onEditStudentDetails
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }
                }
                // phone number
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Phone number",
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(.05f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = state.student.phoneNumber,
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                // email
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Email",
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(.05f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = state.student.email,
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // educational details
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .widthIn(max = 300.dp)
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
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
                        text = "Educational Details",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 16.dp)
                    )
//                    IconButton(
//                        onClick = onEditStudentDetails
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }
                }
                state.student.branch?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Branch",
                            modifier = Modifier.weight(.5f),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )
                        Text(
                            text = ":",
                            modifier = Modifier.weight(.05f),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )
                        Text(
                            text = state.student.branch.abbreviation,
                            modifier = Modifier.weight(.5f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "PRN",
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(.05f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = state.student.prn,
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                state.student.scheme?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Scheme",
                            modifier = Modifier.weight(.5f),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )
                        Text(
                            text = ":",
                            modifier = Modifier.weight(.05f),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )
                        Text(
                            text = state.student.scheme.name,
                            modifier = Modifier.weight(.5f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Admission Year",
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(.05f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = state.student.admissionYear.toString(),
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Academic Status",
                        modifier = Modifier.weight(.5f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = ":",
                        modifier = Modifier.weight(.05f),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }

            AttendifyTextButton(
                onClick = onEditStudentDetails,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Start)
            ) {
                Text(text = "Edit details", color = MaterialTheme.colorScheme.primary)
            }
            // past semesters
            state.semesters?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .widthIn(max = 300.dp)
                        .padding(vertical = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Past Semesters Details",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                        )
                    }
                    state.semesters.forEach { semester ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(top = 16.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Semester ${semester.semesterNumber}",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                            )
                            Text(
                                text = "${semester.academicStartYear}-${
                                    semester.academicEndYear.toString().takeLast(2)
                                }",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .8f)
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .height(IntrinsicSize.Min)
                        ) {
                            //semester line
                            Box(
                                modifier = Modifier
                                    .width(5.dp)
                                    .fillMaxHeight()
                                    .padding(top = 4.dp, bottom = 20.dp)
                                    .background(
                                        color = Color.Blue,
                                        shape = MaterialTheme.shapes.medium
                                    )
                            )
                            //semester end date start date column
                            Column(
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp)
                                ) {
                                    Text(
                                        text = "Start Date",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = ":",
                                        modifier = Modifier.padding(horizontal = 4.dp),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = semester.startDate,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }


                                // past divisions
                                var pastDivisions =
                                    state.studentDivisions?.filter { studentDivision ->
                                        studentDivision.division.semesterId == semester.id
                                    }

                                pastDivisions = pastDivisions?.sortedBy { studentDivision ->
                                    studentDivision.startDate
                                }
                                pastDivisions?.forEach { studentDivision ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 4.dp, start = 32.dp, end = 16.dp),
                                        verticalAlignment = Alignment.Top,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Division ${studentDivision.division.divisionCode}",
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontWeight = FontWeight.Medium
                                            )
                                        )
                                    }
                                    Row(
                                        verticalAlignment = Alignment.Top,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .padding(start = 32.dp)
                                            .height(IntrinsicSize.Min)
                                    ) {
                                        //division line
                                        Box(
                                            modifier = Modifier
                                                .width(5.dp)
                                                .fillMaxHeight()
                                                .padding(vertical = 4.dp)
                                                .background(
                                                    color = Color.Green,
                                                    shape = MaterialTheme.shapes.medium
                                                )
                                        )
                                        //division from till column
                                        Column(
                                            verticalArrangement = Arrangement.Top,
                                            horizontalAlignment = Alignment.Start,
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.Top,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 8.dp)
                                            ) {
                                                Text(
                                                    text = "From",
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                                Text(
                                                    text = "",
                                                    modifier = Modifier.padding(horizontal = 4.dp),
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                                Text(
                                                    text = studentDivision.startDate,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }


                                            var pastBatches =
                                                state.studentBatches?.filter { studentBatch ->
                                                    studentBatch.batch.divisionId == studentDivision.division.id
                                                }
                                            pastBatches = pastBatches?.sortedBy { studentBatch ->
                                                studentBatch.startDate
                                            }
                                            pastBatches?.forEach { studentBatch ->

                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(
                                                            top = 4.dp,
                                                            start = 32.dp,
                                                            end = 16.dp
                                                        ),
                                                    verticalAlignment = Alignment.Top,
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text(
                                                        text = "Batch ${studentBatch.batch.batchCode}",
                                                        style = MaterialTheme.typography.bodyLarge.copy(
                                                            fontWeight = FontWeight.Medium
                                                        )
                                                    )
                                                }
                                                Row(
                                                    verticalAlignment = Alignment.Top,
                                                    horizontalArrangement = Arrangement.SpaceBetween,
                                                    modifier = Modifier
                                                        .padding(start = 32.dp)
                                                        .height(IntrinsicSize.Min)
                                                ) {
                                                    //division line
                                                    Box(
                                                        modifier = Modifier
                                                            .width(5.dp)
                                                            .fillMaxHeight()
                                                            .padding(vertical = 4.dp)
                                                            .background(
                                                                color = Color.Yellow,
                                                                shape = MaterialTheme.shapes.medium
                                                            )
                                                    )
                                                    //division from till column
                                                    Column(
                                                        verticalArrangement = Arrangement.Top,
                                                        horizontalAlignment = Alignment.Start,
                                                    ) {
                                                        Row(
                                                            verticalAlignment = Alignment.Top,
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(horizontal = 8.dp)
                                                        ) {
                                                            Text(
                                                                text = "From",
                                                                style = MaterialTheme.typography.bodyMedium
                                                            )
                                                            Text(
                                                                text = "",
                                                                modifier = Modifier.padding(
                                                                    horizontal = 4.dp
                                                                ),
                                                                style = MaterialTheme.typography.bodyMedium
                                                            )
                                                            Text(
                                                                text = studentBatch.startDate,
                                                                style = MaterialTheme.typography.bodyMedium
                                                            )
                                                        }
                                                        Row(
                                                            verticalAlignment = Alignment.Top,
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(horizontal = 8.dp)
                                                                .padding(top = 4.dp)
                                                        ) {
                                                            Text(
                                                                text = "till",
                                                                style = MaterialTheme.typography.bodyMedium
                                                            )
                                                            Text(
                                                                text = "",
                                                                modifier = Modifier.padding(
                                                                    horizontal = 4.dp
                                                                ),
                                                                style = MaterialTheme.typography.bodyMedium
                                                            )
                                                            Text(
                                                                text = studentBatch.endDate
                                                                    ?: "Today",
                                                                style = MaterialTheme.typography.bodyMedium
                                                            )
                                                        }

                                                    }
                                                }
                                            }

                                            Row(
                                                verticalAlignment = Alignment.Top,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 8.dp)
                                                    .padding(top = 4.dp)
                                            ) {
                                                Text(
                                                    text = "till",
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                                Text(
                                                    text = "",
                                                    modifier = Modifier.padding(horizontal = 4.dp),
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                                Text(
                                                    text = studentDivision.endDate ?: "Today",
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                        }


                                    }
                                }

                                // semester end date
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp)
                                        .padding(top = 4.dp, bottom = 16.dp)
                                ) {
                                    Text(
                                        text = "End Date",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = ":",
                                        modifier = Modifier.padding(horizontal = 4.dp),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = semester.endDate,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }


                            }

                        }
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@ScreenPreview
@Composable
fun StudentDetailsScreenPreview() {
    PreviewWrapper {
        StudentDetailsScreen(
            state = StudentDetailsState(
                student = Student(
                    id = 1,
                    admissionType = "Regular",
                    admissionYear = 2023,
                    dob = "2000-01-01",
                    email = "student1@example.com",
                    firstName = "John",
                    gender = "Male",
                    lastName = "Doe",
                    middleName = "Middle",
                    password = "password123",
                    phoneNumber = "123-456-7890",
                    prn = "PRN12345",
                    studentImgPublicId = "public_id_123",
                    studentImgUrl = "https://example.com/student1.jpg",
                    schemeId = 1,
                    scheme = createSampleScheme(), // Assuming you have a function to create a sample Scheme
                    studentSemesters = listOf(createSampleStudentSemester()), // Assuming you have a function for StudentSemester
                    studentDivisions = listOf(createSampleStudentDivision()), // Assuming you have a function for StudentDivision
                    studentBatches = listOf(createSampleStudentBatch()), // Assuming you have a function for StudentBatch
                    branchId = 1,
                    parentEmail = "parent1@example.com",
                    branch = createSampleBranch()// Assuming you have a function to create a sample Branch
                ),
                semesters = listOf(
//                    Semester(
//                        id = 1,
//                        academicEndYear = 2025,
//                        academicStartYear = 2024,
//                        semesterNumber = 4,
//                        endDate = "2024-04-05",
//                        startDate = "2024-04-05",
//                        branchId = 1,
//                        Branch = null,
//                        schemeId = 1,
//                        Scheme = null,
//                        createdAt = "TODO()",
//                        updatedAt = " TODO()"
//                    ),
//                    Semester(
//                        id = 1,
//                        academicEndYear = 2025,
//                        academicStartYear = 2024,
//                        semesterNumber = 2,
//                        endDate = "2024-04-05",
//                        startDate = "2024-04-05",
//                        branchId = 1,
//                        Branch = null,
//                        schemeId = 1,
//                        Scheme = null,
//                        createdAt = "TODO()",
//                        updatedAt = " TODO()"
//                    ),
                    Semester(
                        id = 1,
                        academicEndYear = 2025,
                        academicStartYear = 2024,
                        semesterNumber = 3,
                        endDate = "2024-04-05",
                        startDate = "2024-04-05",
                        branchId = 1,
                        branch = null,
                        schemeId = 1,
                        scheme = null,
                    ),

                    ),
                studentDivisions = listOf(
                    createSampleStudentDivision(),
//                    createSampleStudentDivision()
                ),
                studentBatches = listOf(
                    createSampleStudentBatch(),
//                    createSampleStudentBatch()
                )
            ),
            onEvent = {},
            onEditStudentDetails = {}
        )
    }
}


// Sample Scheme creation function (you need to define this based on your Scheme data class)
fun createSampleScheme(): Scheme {
    return Scheme(
        id = 1,
        name = "Sample Scheme",
        universityId = 1,
        university = null,
    )
}

//Sample Branch creation function (you need to define this based on your branch data class)
fun createSampleBranch(): Branch {
    return Branch(
        id = 1,
        name = "Computer Engineering",
        abbreviation = "Comp. Engg.",
    )
}

// Sample StudentSemester creation function (you need to define this based on your StudentSemester data class)
fun createSampleStudentSemester(): StudentSemester {
    return StudentSemester(
        id = 1,
        studentId = 1,
        semesterId = 1,
        semester = Semester(
            id = 1,
            academicEndYear = 2024,
            academicStartYear = 2025,
            semesterNumber = 5,
            endDate = "TODO()",
            startDate = "TODO()",
            branchId = 1,
            branch = createSampleBranch(),
            schemeId = 1,
            scheme = null,
        ),
        student = null
    )
}

// Sample StudentDivision creation function (you need to define this based on your StudentDivision data class)
fun createSampleStudentDivision(): StudentDivision {
    return StudentDivision(
        id = 1,
        studentId = 1,
        divisionId = 1,
        division = Division(
            id = 1,
            divisionCode = "A",
            semesterId = 1,
            semester = null,
            batch = null
        ),
        endDate = "2024-05-20",
        startDate = "2024-05-20",
        student = null
    )
}

// Sample StudentBatch creation function (you need to define this based on your StudentBatch data class)
fun createSampleStudentBatch(): StudentBatch {
    return StudentBatch(
        id = 1,
        studentId = 1,
        batchId = 1,
        batch = Batch(
            id = 1,
            batchCode = "TB1",
            divisionId = 1,
            division = Division(
                id = 1,
                divisionCode = "A",
                semesterId = 1,
                semester = null,
                batch = null
            )
        ),
        endDate = "2024-05-20",
        startDate = "2024-05-20",
        student = null
    )
}