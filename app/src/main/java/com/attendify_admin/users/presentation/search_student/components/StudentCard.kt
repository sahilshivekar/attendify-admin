package com.attendify_admin.users.presentation.search_student.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.attendify_admin.R
import com.attendify_admin.common.presentation.ComponentPreview
import com.attendify_admin.common.presentation.components.AttendifyTextDivider
import com.attendify_admin.ui.theme.AttendifyAdminTheme


//Text(
//text = if (student.StudentSemesters?.first()?.Semester?.semesterNumber != null)
//StudentUtils.getCurrentYearFromSem(
//student.StudentSemesters.first().Semester.semesterNumber
//) else "",
//style = MaterialTheme.typography.bodyMedium
//)

@Composable
fun StudentCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    studentName: String,
    studentBranch: String,
    studentYear: String? = null,
    studentImageUrl: String? = null
) {

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 300.dp)
            .clip(MaterialTheme.shapes.small),
        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .5f),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
    ) {

        var imageLoaded by remember { mutableStateOf(false) }
        LaunchedEffect(
            imageLoaded
        ) {
            Log.d("StudentCard", "StudentCard: $imageLoaded")
        }
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier.fillMaxWidth()
//        ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentWidth()
        ) {

//            if (studentImageUrl == null) {
//                Icon(
//                    imageVector = Icons.Default.AccountCircle,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(12.dp)
//                        .clip(CircleShape)
//                        .height(50.dp)
//                        .width(50.dp),
//                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
//                )
//            } else {
            Image(
                painter = rememberAsyncImagePainter(
                    model = studentImageUrl,
                    contentScale = ContentScale.FillBounds,
                    onSuccess = {
                        imageLoaded = true
                    },
                    placeholder = painterResource(R.drawable.baseline_account_circle_24),
                    fallback = painterResource(R.drawable.baseline_account_circle_24),
                    error = painterResource(R.drawable.baseline_account_circle_24)
                ),
                contentDescription = null,
                modifier = Modifier

                    //to understand why the padding, width and height is applied this
                    //way then make it normal (without the if condition) and you will see
                    //image is taking larger size than icons which doesn't look good on screen
//                    .padding(if (imageLoaded) 16.dp else 12.dp)
                    .padding(end = if (imageLoaded) 16.dp else 12.dp)
                    .padding(vertical = if (imageLoaded) 8.dp else 4.dp)
                    .padding(start = if (imageLoaded) 8.dp else 4.dp)
                    .clip(CircleShape)
                    .height(if (imageLoaded) 42.dp else 50.dp)
                    .width(if (imageLoaded) 42.dp else 50.dp),
                contentScale = ContentScale.FillBounds,
                colorFilter = if (imageLoaded) null else ColorFilter.tint(
                    MaterialTheme.colorScheme.onSurface.copy(
                        alpha = .5f
                    )
                )
            )
//            }


            // name and other details root column
            Column {
                Row(
                    modifier = Modifier,
//                            .padding(start = 8.dp),
//                            .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = studentName,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Row(
                    modifier = Modifier,
//                            .padding(start = 8.dp),
//                            .padding(bottom = 16.dp),
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
            }
        }

//            IconButton(
//                onClick = {
//
//                },
//                modifier = Modifier.padding(end = 8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.onSurface,
//                    modifier = Modifier
//                        .background(
//                            color = MaterialTheme.colorScheme.onSurface.copy(
//                                alpha = .09f
//                            ), shape = CircleShape
//                        )
//                )
//            }
//        }
    }
}


@ComponentPreview
@Composable
fun SearchStudentScreenPreview() {
    AttendifyAdminTheme {
        StudentCard(
            modifier = Modifier,
            onClick = {},
            studentBranch = "Comp. Engg.",
            studentName = "Shivam Pandey",
            studentYear = "TE",
            studentImageUrl = null
        )
    }
}