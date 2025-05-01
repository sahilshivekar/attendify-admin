package com.attendify_admin.users.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.attendify_admin.users.presentation.add_staff.AddStaffScreen
import com.attendify_admin.users.presentation.add_staff.AddStaffViewModel
import com.attendify_admin.users.presentation.add_student.AddStudentScreen
import com.attendify_admin.users.presentation.add_student.AddStudentViewModel
import com.attendify_admin.users.presentation.assign_student_to_batch.AssignStudentBatchScreen
import com.attendify_admin.users.presentation.assign_student_to_batch.AssignStudentBatchViewModel
import com.attendify_admin.users.presentation.assign_student_to_division.AssignStudentDivisionScreen
import com.attendify_admin.users.presentation.assign_student_to_division.AssignStudentDivisionViewModel
import com.attendify_admin.users.presentation.assign_student_to_semester.AssignStudentSemesterScreen
import com.attendify_admin.users.presentation.assign_student_to_semester.AssignStudentSemesterViewModel
import com.attendify_admin.users.presentation.assign_subject_to_teacher.AssignSubjectTeacherScreen
import com.attendify_admin.users.presentation.assign_subject_to_teacher.AssignSubjectTeacherViewModel
import com.attendify_admin.users.presentation.modify_student_division.ModifyStudentDivisionScreen
import com.attendify_admin.users.presentation.modify_student_division.ModifyStudentDivisionViewModel
import com.attendify_admin.users.presentation.search_staff.SearchStaffScreen
import com.attendify_admin.users.presentation.search_staff.SearchStaffViewModel
import com.attendify_admin.users.presentation.search_student.SearchStudentScreen
import com.attendify_admin.users.presentation.search_student.SearchStudentViewModel
import com.attendify_admin.users.presentation.student_details.StudentDetailsScreen
import com.attendify_admin.users.presentation.student_details.StudentDetailsViewModel
import com.attendify_admin.users.presentation.unassign_subject_to_teacher.UnassignSubjectTeacherScreen
import com.attendify_admin.users.presentation.unassign_subject_to_teacher.UnassignSubjectTeacherViewModel
import com.attendify_admin.users.presentation.user_dashboard.UserDashboardScreen

@Composable
fun UsersNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = UsersDestination.UsersDashboard.route
    ) {
        composable(route = UsersDestination.UsersDashboard.route) {
            UserDashboardScreen(
                onAddStudentClick = {
                    navController.navigate(UsersDestination.AddStudent.route)
                },
                onSearchStudentClick = {
                    navController.navigate(UsersDestination.SearchStudent.route)
                },
                onAddStaffClick = {
                    navController.navigate(UsersDestination.AddStaff.route)
                },
                onSearchStaffClick = {
                    navController.navigate(UsersDestination.SearchStaff.route)
                },
                onAssignStudentToSemesterClick = {
                    navController.navigate(UsersDestination.AssignStudentToSemester.route)
                },
                onRemoveStudentFromSemesterClick = {
                    navController.navigate(UsersDestination.RemoveStudentFromSemester.route)
                },
                onAssignStudentToDivisionClick = {
                    navController.navigate(UsersDestination.AssignStudentToDivision.route)
                },
                onModifyStudentDivisionClick = {
                    navController.navigate(UsersDestination.ModifyStudentDivision.route)
                },
                onAssignStudentToBatchClick = {
                    navController.navigate(UsersDestination.AssignStudentToBatch.route)
                },
                onModifyStudentBatchClick = {
                    navController.navigate(UsersDestination.ModifyStudentBatch.route)
                },
                onAssignSubjectToTeacher = {
                    navController.navigate(UsersDestination.AssignSubjectToTeacher.route)
                },
                onUnassignSubjectToTeacher = {
                    navController.navigate(UsersDestination.UnassignSubjectToTeacher.route)
                },
            )
        }

        composable(route = UsersDestination.AddStudent.route) {
            val viewModel = hiltViewModel<AddStudentViewModel>()
            val studentId = navController.currentBackStackEntry?.arguments?.getString("studentId")
                ?.toIntOrNull()
            studentId?.let { viewModel.setStudentId(studentId) }
            AddStudentScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onAddStudentSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = UsersDestination.SearchStudent.route) {
            val viewModel = hiltViewModel<SearchStudentViewModel>()
            SearchStudentScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onStudentCardClick = { studentId ->
                    navController.navigate(
                        UsersDestination.StudentDetails.route.replace(
                            "{studentId}",
                            studentId.toString()
                        )
                    )
                },
            )
        }

        composable(route = UsersDestination.AddStaff.route) {
            val viewModel = hiltViewModel<AddStaffViewModel>()
            AddStaffScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onAddStaffSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = UsersDestination.SearchStaff.route) {
            val viewModel = hiltViewModel<SearchStaffViewModel>()
            SearchStaffScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
            )
        }

        composable(route = UsersDestination.AssignStudentToSemester.route) {
            val viewModel = hiltViewModel<AssignStudentSemesterViewModel>()
            AssignStudentSemesterScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = UsersDestination.AssignStudentToDivision.route) {
            val viewModel = hiltViewModel<AssignStudentDivisionViewModel>()
            AssignStudentDivisionScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = UsersDestination.AssignStudentToBatch.route) {
            val viewModel = hiltViewModel<AssignStudentBatchViewModel>()
            AssignStudentBatchScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = UsersDestination.RemoveStudentFromSemester.route) {
            val viewModel = hiltViewModel<AssignStudentSemesterViewModel>()
            AssignStudentSemesterScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = UsersDestination.ModifyStudentDivision.route) {
            val viewModel = hiltViewModel<ModifyStudentDivisionViewModel>()
            ModifyStudentDivisionScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = UsersDestination.ModifyStudentBatch.route) {
            val viewModel = hiltViewModel<AssignStudentBatchViewModel>()
            AssignStudentBatchScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = UsersDestination.AssignSubjectToTeacher.route) {
            val viewModel = hiltViewModel<AssignSubjectTeacherViewModel>()
            AssignSubjectTeacherScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = UsersDestination.UnassignSubjectToTeacher.route) {
            val viewModel = hiltViewModel<UnassignSubjectTeacherViewModel>()
            UnassignSubjectTeacherScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = UsersDestination.StudentDetails.route) {
            val viewModel = hiltViewModel<StudentDetailsViewModel>()
            val studentId = navController.currentBackStackEntry?.arguments?.getString("studentId")
                ?.toIntOrNull()
            studentId?.let { viewModel.setStudentIdAndGetStudent(studentId) }
            StudentDetailsScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
                onEditStudentDetails = {
                    navController.navigate(
                        UsersDestination.AddStudent.route.replace(
                            "{studentId}",
                            studentId.toString()
                        )
                    )
                }
            )

        }
    }
}