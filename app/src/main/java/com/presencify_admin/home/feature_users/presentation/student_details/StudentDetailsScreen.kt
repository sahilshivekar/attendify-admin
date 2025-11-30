package com.presencify_admin.home.feature_users.presentation.student_details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.presencify_admin.common.domain.model.Batch
import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Division
import com.presencify_admin.common.domain.model.Scheme
import com.presencify_admin.common.domain.model.Semester
import com.presencify_admin.common.domain.model.Student
import com.presencify_admin.common.domain.model.StudentBatch
import com.presencify_admin.common.domain.model.StudentDivision
import com.presencify_admin.common.domain.model.StudentSemester
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyTextButton
import com.presencify_admin.home.feature_users.presentation.student_details.components.AcademicDetailsContainer
import com.presencify_admin.home.feature_users.presentation.student_details.components.ContactDetailsContainer
import com.presencify_admin.home.feature_users.presentation.student_details.components.DropoutDetailsContainer
import com.presencify_admin.home.feature_users.presentation.student_details.components.PersonalDetailsContainer
import com.presencify_admin.home.feature_users.presentation.student_details.components.SemesterDetailsContainer
import com.presencify_admin.home.feature_users.presentation.student_details.components.StudentImageContainer

@Composable
fun StudentDetailsScreen(
    modifier: Modifier = Modifier,
    state: StudentDetailsState,
    onEvent: (StudentDetailsEvent) -> Unit,
    onEditStudentDetails: () -> Unit,
    navigateUp: () -> Unit,
) {

    LaunchedEffect(state.isStudentRemoved) {
        if (state.isStudentRemoved) navigateUp()
    }

    AnimatedContent(
        targetState = state.student == null,
        transitionSpec = {
            (slideInVertically { it / 5 }).togetherWith(fadeOut())
        }
    ) { targetState ->
        if (targetState) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                StudentImageContainer(state = state, onEvent = onEvent)

                PersonalDetailsContainer(state = state)

                ContactDetailsContainer(state = state)

                AcademicDetailsContainer(state = state)

                DropoutDetailsContainer(state = state)

                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .widthIn(max = UiConstants.MAX_WIDTH),
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PresencifyTextButton(
                            onClick = onEditStudentDetails,
                            enabled = !state.isRemovingStudent
                        ) {
                            Text(text = "Edit details", color = MaterialTheme.colorScheme.primary)
                        }
                        PresencifyTextButton(
                            onClick = { onEvent(StudentDetailsEvent.RemoveStudentClicked) },
                            enabled = !state.isRemovingStudent
                        ) {
                            if (state.isRemovingStudent) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp,
                                )
                            } else {
                                Text(
                                    text = "Remove student",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
                SemesterDetailsContainer(state = state)


                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}


@PreviewScreenSizes
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
            onEditStudentDetails = {},
            navigateUp = {}
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