package com.attendify_admin.home.feature_users.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.attendify_admin.home.feature_users.presentation.add_staff.AddStaffScreen
import com.attendify_admin.home.feature_users.presentation.add_staff.AddStaffViewModel
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentScreen
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentViewModel
import com.attendify_admin.home.feature_users.presentation.assign_student_to_batch.AssignStudentBatchScreen
import com.attendify_admin.home.feature_users.presentation.assign_student_to_batch.AssignStudentBatchViewModel
import com.attendify_admin.home.feature_users.presentation.assign_student_to_division.AssignStudentDivisionScreen
import com.attendify_admin.home.feature_users.presentation.assign_student_to_division.AssignStudentDivisionViewModel
import com.attendify_admin.home.feature_users.presentation.assign_student_to_semester.AssignStudentSemesterScreen
import com.attendify_admin.home.feature_users.presentation.assign_student_to_semester.AssignStudentSemesterViewModel
import com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher.AssignSubjectTeacherScreen
import com.attendify_admin.home.feature_users.presentation.assign_subject_to_teacher.AssignSubjectTeacherViewModel
import com.attendify_admin.home.feature_users.presentation.modify_student_division.ModifyStudentDivisionScreen
import com.attendify_admin.home.feature_users.presentation.modify_student_division.ModifyStudentDivisionViewModel
import com.attendify_admin.home.feature_users.presentation.search_staff.SearchStaffScreen
import com.attendify_admin.home.feature_users.presentation.search_staff.SearchStaffViewModel
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
            },
            exitTransition = {
                val previousDestinationRoute =
                    navController.previousBackStackEntry?.destination?.route
                // following logic will work bcz we are popping the entire back stack on tab switch
                if (previousDestinationRoute == UsersDestination.UsersDashboard.route) {
                    fadeOut() + slideOutHorizontally { -it / 2 }
                } else {
                    fadeOut()
                }
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
            route = UsersDestination.SearchStudent.route,
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

            val viewModel = hiltViewModel<SearchStudentViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            SearchStudentScreen(
                onEvent = viewModel::onEvent,
                state = state,
                onStudentCardClick = { studentId ->

                    navController.navigate(
                        UsersDestination.StudentDetails.route + "?studentId=$studentId"
                    )
                },
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
            Log.d("navgraph", "staff details before viewmodel created")
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
            route = UsersDestination.SearchStaff.route,
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
            SearchStaffScreen(
                onEvent = viewModel::onEvent,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onStaffCardClick = { staffId ->
                    Log.d("navgraph", "called navigate to staffdetails id: $staffId")
                    navController.navigate(
                        UsersDestination.StaffDetails.route + "?staffId=$staffId"
                    )
                    Log.d("navgraph", "after calling navigate id: $staffId")

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
            AssignStudentSemesterScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
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
            AssignStudentDivisionScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
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
            AssignStudentBatchScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
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
            val viewModel = hiltViewModel<AssignStudentSemesterViewModel>()
            AssignStudentSemesterScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
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
            val viewModel = hiltViewModel<AssignStudentBatchViewModel>()
            AssignStudentBatchScreen(
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
            AssignSubjectTeacherScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
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
            UnassignSubjectTeacherScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
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
    }
}