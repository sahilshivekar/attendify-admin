package com.attendify_admin.home.feature_users.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.attendify_admin.home.feature_users.presentation.add_staff.AddStaffScreen
import com.attendify_admin.home.feature_users.presentation.add_staff.AddStaffViewModel
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentScreen
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentViewModel
import com.attendify_admin.home.feature_users.presentation.add_to_dropout.AddToDropoutScreen
import com.attendify_admin.home.feature_users.presentation.add_to_dropout.AddToDropoutViewModel
import com.attendify_admin.home.feature_users.presentation.assign_student_to_batch.AssignStudentBatchScreen
import com.attendify_admin.home.feature_users.presentation.assign_student_to_batch.AssignStudentBatchViewModel
import com.attendify_admin.home.feature_users.presentation.assign_student_to_division.AssignStudentDivisionScreen
import com.attendify_admin.home.feature_users.presentation.assign_student_to_division.AssignStudentDivisionViewModel
import com.attendify_admin.home.feature_users.presentation.assign_student_to_semester.AssignStudentSemesterScreen
import com.attendify_admin.home.feature_users.presentation.assign_student_to_semester.AssignStudentSemesterViewModel
import com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher.AssignSubjectTeacherScreen
import com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher.AssignSubjectTeacherViewModel
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.ModifyStudentBatchScreen
import com.attendify_admin.home.feature_users.presentation.modify_student_batch.ModifyStudentBatchViewModel
import com.attendify_admin.home.feature_users.presentation.modify_student_division.ModifyStudentDivisionScreen
import com.attendify_admin.home.feature_users.presentation.modify_student_division.ModifyStudentDivisionViewModel
import com.attendify_admin.home.feature_users.presentation.remove_from_dropout.RemoveFromDropoutScreen
import com.attendify_admin.home.feature_users.presentation.remove_from_dropout.RemoveFromDropoutViewModel
import com.attendify_admin.home.feature_users.presentation.remove_student_from_semester.RemoveStudentSemesterScreen
import com.attendify_admin.home.feature_users.presentation.remove_student_from_semester.RemoveStudentSemesterViewModel
import com.attendify_admin.home.feature_users.presentation.search_staff.SearchStaffScreen
import com.attendify_admin.home.feature_users.presentation.search_staff.SearchStaffViewModel
import com.attendify_admin.home.feature_users.presentation.search_student.SearchStudentEvent
import com.attendify_admin.home.feature_users.presentation.search_student.SearchStudentScreen
import com.attendify_admin.home.feature_users.presentation.search_student.SearchStudentViewModel
import com.attendify_admin.home.feature_users.presentation.staff_details.StaffDetailsScreen
import com.attendify_admin.home.feature_users.presentation.staff_details.StaffDetailsViewModel
import com.attendify_admin.home.feature_users.presentation.student_details.StudentDetailsScreen
import com.attendify_admin.home.feature_users.presentation.student_details.StudentDetailsViewModel
import com.attendify_admin.home.feature_users.presentation.unassign_subject_to_teacher.UnassignSubjectTeacherScreen
import com.attendify_admin.home.feature_users.presentation.unassign_subject_to_teacher.UnassignSubjectTeacherViewModel
import com.attendify_admin.home.feature_users.presentation.user_dashboard.UserDashboardScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UsersNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = UsersDestination.UsersDashboard.route
    ) {
        composable(
            route = UsersDestination.UsersDashboard.route,
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            }
        ) {
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
                onAddToDropout = {
                    navController.navigate(UsersDestination.AddStudentToDropout.route)
                },
                onRemoveFromDropout = {
                    navController.navigate(UsersDestination.RemoveStudentFromDropout.route)
                }
            )
        }

        composable(
            route = UsersDestination.AddStudent.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<AddStudentViewModel>()
            AddStudentScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onAddStudentSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = UsersDestination.AddStudent.route + "?studentId={studentId}",
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<AddStudentViewModel>()
            AddStudentScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onAddStudentSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = UsersDestination.SearchStudent.route + "?isSelectable={isSelectable}",
            arguments = listOf(
                navArgument("isSelectable") { type = NavType.BoolType; defaultValue = false },
            )
        ) {
            val viewModel = hiltViewModel<SearchStudentViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value

            SearchStudentScreen(
                onEvent = viewModel::onEvent,
                state = state,
                onStudentCardClick = { studentId ->
                    if (state.isSelectable) {
                        if (studentId in state.selectedStudentIds) {
                            viewModel.onEvent(SearchStudentEvent.StudentDeselected(studentId))
                        } else {
                            viewModel.onEvent(SearchStudentEvent.StudentSelected(studentId))
                        }
                    } else {
                        navController.navigate(UsersDestination.StudentDetails.route + "?studentId=$studentId")
                    }
                },
                onDoneClick = {
                    Log.d(
                        "SearchStudentScreen",
                        "Previous back stack entry: ${navController.previousBackStackEntry?.destination?.route}"
                    )
//                    navController.previousBackStackEntry
//                        ?.savedStateHandle
//                        ?.set("selectedStudentIds", state.selectedStudentIds.toList())
                    val prevSavedStateHandle =
                        navController.previousBackStackEntry?.savedStateHandle
                    if (prevSavedStateHandle != null) {
                        Log.d(
                            "SearchStudentScreen",
                            "Setting selectedStudentIds: ${state.selectedStudentIds.toList()}"
                        )
                        prevSavedStateHandle["selectedStudentIds"] =
                            state.selectedStudentIds.toList()
                    } else {
                        Log.e("SearchStudentScreen", "Previous SavedStateHandle is null!")
                    }
                    navController.popBackStack()
                }
            )
        }



        composable(
            route = UsersDestination.AddStaff.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<AddStaffViewModel>()
            AddStaffScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onAddStaffSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = UsersDestination.AddStaff.route + "?staffId={staffId}",
            arguments = listOf(
                navArgument("staffId") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<AddStaffViewModel>()
            AddStaffScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onAddStaffSuccess = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = UsersDestination.StaffDetails.route + "?staffId={staffId}",
            arguments = listOf(
                navArgument("staffId") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<StaffDetailsViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            StaffDetailsScreen(
                onEvent = viewModel::onEvent,
                state = state,
                onEditStaffDetails = {
                    navController.navigate(route = UsersDestination.AddStaff.route + "?staffId=${state.staff?.id}")
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = UsersDestination.SearchStaff.route + "?isSelectable={isSelectable}",
            arguments = listOf(
                navArgument("isSelectable") { type = NavType.BoolType; defaultValue = false },
            ),
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<SearchStaffViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            SearchStaffScreen(
                onEvent = viewModel::onEvent,
                state = state,
                onStaffCardClick = { staffId ->
                    if (state.isSelectable) {
                        val prevSavedStateHandle =
                            navController.previousBackStackEntry?.savedStateHandle
                        if (prevSavedStateHandle != null) {
                            prevSavedStateHandle["staffId"] = staffId
                        } else {
                            Log.e("SearchStudentScreen", "Previous SavedStateHandle is null!")
                        }
                        navController.popBackStack()
                    } else {
                        navController.navigate(UsersDestination.StaffDetails.route + "?staffId=$staffId")
                    }
                }

            )
        }

        composable(
            route = UsersDestination.AssignStudentToSemester.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<AssignStudentSemesterViewModel>()
            val backStackEntry = navController.currentBackStackEntryAsState().value
            val selectedIds = backStackEntry?.savedStateHandle?.get<List<Int>>("selectedStudentIds")

            // Trigger only once when value is received
            LaunchedEffect(selectedIds) {
                selectedIds?.let {
                    viewModel.onStudentIdsReceived(it)
                    backStackEntry.savedStateHandle.remove<List<Int>>("selectedStudentIds")
                }
            }

            AssignStudentSemesterScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
                onSelectStudents = {
                    navController.navigate(
                        UsersDestination.SearchStudent.route + "?isSelectable=${true}"
                    )
                }
            )
        }

        composable(
            route = UsersDestination.AssignStudentToDivision.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {

            val viewModel = hiltViewModel<AssignStudentDivisionViewModel>()
            val backStackEntry = navController.currentBackStackEntryAsState().value
            val selectedIds = backStackEntry?.savedStateHandle?.get<List<Int>>("selectedStudentIds")

            // Trigger only once when value is received
            LaunchedEffect(selectedIds) {
                selectedIds?.let {
                    viewModel.onStudentIdsReceived(it)
                    backStackEntry.savedStateHandle.remove<List<Int>>("selectedStudentIds")
                }
            }
            AssignStudentDivisionScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
                onSelectStudents = {
                    navController.navigate(
                        UsersDestination.SearchStudent.route + "?isSelectable=${true}"
                    )
                }
            )
        }

        composable(
            route = UsersDestination.AssignStudentToBatch.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<AssignStudentBatchViewModel>()
            val backStackEntry = navController.currentBackStackEntryAsState().value
            val selectedIds = backStackEntry?.savedStateHandle?.get<List<Int>>("selectedStudentIds")

            // Trigger only once when value is received
            LaunchedEffect(selectedIds) {
                selectedIds?.let {
                    viewModel.onStudentIdsReceived(it)
                    backStackEntry.savedStateHandle.remove<List<Int>>("selectedStudentIds")
                }
            }
            AssignStudentBatchScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
                onSelectStudents = {
                    navController.navigate(
                        UsersDestination.SearchStudent.route + "?isSelectable=${true}"
                    )
                }
            )
        }

        composable(
            route = UsersDestination.RemoveStudentFromSemester.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<RemoveStudentSemesterViewModel>()
            RemoveStudentSemesterScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
            )
        }

        composable(
            route = UsersDestination.ModifyStudentDivision.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<ModifyStudentDivisionViewModel>()
            ModifyStudentDivisionScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(
            route = UsersDestination.ModifyStudentBatch.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<ModifyStudentBatchViewModel>()
            ModifyStudentBatchScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }

        composable(
            route = UsersDestination.AssignSubjectToTeacher.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<AssignSubjectTeacherViewModel>()
            val backStackEntry = navController.currentBackStackEntryAsState().value
            val selectedIds = backStackEntry?.savedStateHandle?.get<Int>("staffId")

            // Trigger only once when value is received
            LaunchedEffect(selectedIds) {
                selectedIds?.let {
                    viewModel.onStaffIdReceived(it)
                    backStackEntry.savedStateHandle.remove<Int>("staffId")
                }
            }
            AssignSubjectTeacherScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
                onSelectStaffMember = {
                    navController.navigate(UsersDestination.SearchStaff.route + "?isSelectable=${true}")
                }
            )
        }

        composable(
            route = UsersDestination.UnassignSubjectToTeacher.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<UnassignSubjectTeacherViewModel>()
            val backStackEntry = navController.currentBackStackEntryAsState().value
            val selectedIds = backStackEntry?.savedStateHandle?.get<Int>("staffId")

            // Trigger only once when value is received
            LaunchedEffect(selectedIds) {
                selectedIds?.let {
                    viewModel.onStaffIdReceived(it)
                    backStackEntry.savedStateHandle.remove<Int>("staffId")
                }
            }
            UnassignSubjectTeacherScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
                onSelectStaffMember = {
                    navController.navigate(UsersDestination.SearchStaff.route + "?isSelectable=${true}")
                }
            )
        }

        composable(
            route = UsersDestination.StudentDetails.route + "?studentId={studentId}",
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<StudentDetailsViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            StudentDetailsScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onEditStudentDetails = {
                    navController.navigate(
                        UsersDestination.AddStudent.route + "?studentId=${state.studentId}"
                    )
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )

        }


        composable(
            route = UsersDestination.AddStudentToDropout.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<AddToDropoutViewModel>()
            val backStackEntry = navController.currentBackStackEntryAsState().value
            val selectedIds = backStackEntry?.savedStateHandle?.get<List<Int>>("selectedStudentIds")

            // Trigger only once when value is received
            LaunchedEffect(selectedIds) {
                selectedIds?.let {
                    viewModel.onStudentIdsReceived(it)
                    backStackEntry.savedStateHandle.remove<List<Int>>("selectedStudentIds")
                }
            }
            AddToDropoutScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
                onSelectStudents = {
                    navController.navigate(UsersDestination.SearchStudent.route + "?isSelectable=${true}")
                }
            )
        }

        composable(
            route = UsersDestination.RemoveStudentFromDropout.route,
            enterTransition = {
                fadeIn() + slideInHorizontally { it / 2 }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { -it / 2 }
            },
            popEnterTransition = {
                fadeIn() + slideInHorizontally { -it / 2 }
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally { it / 2 }
            }
        ) {
            val viewModel = hiltViewModel<RemoveFromDropoutViewModel>()
            val backStackEntry = navController.currentBackStackEntryAsState().value
            val selectedIds = backStackEntry?.savedStateHandle?.get<List<Int>>("selectedStudentIds")

            // Trigger only once when value is received
            LaunchedEffect(selectedIds) {
                selectedIds?.let {
                    viewModel.onStudentIdsReceived(it)
                    backStackEntry.savedStateHandle.remove<List<Int>>("selectedStudentIds")
                }
            }

            RemoveFromDropoutScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
                onSelectStudents = {
                    navController.navigate(UsersDestination.SearchStudent.route + "?isSelectable=${true}")
                }
            )
        }
    }
}