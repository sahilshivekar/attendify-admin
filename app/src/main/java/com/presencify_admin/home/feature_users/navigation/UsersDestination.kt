package com.presencify_admin.home.feature_users.navigation

sealed class UsersDestination(
    val route: String
) {
    data object UsersDashboard : UsersDestination("users_dashboard")
    data object AddStudent : UsersDestination("add_student")
    data object SearchStudent: UsersDestination("search_student")
    data object AddStaff : UsersDestination("add_staff")
    data object SearchStaff : UsersDestination("search_staff")
    data object AssignStudentToSemester : UsersDestination("assign_student_to_semester")
    data object RemoveStudentFromSemester : UsersDestination("remove_student_from_semester")
    data object AssignStudentToDivision : UsersDestination("assign_student_to_division")
    data object ModifyStudentDivision : UsersDestination("modify_student_division")
    data object AssignStudentToBatch : UsersDestination("assign_student_to_batch")
    data object ModifyStudentBatch : UsersDestination("modify_student_batch")
    data object AssignSubjectToTeacher: UsersDestination("assign_subject_to_teacher")
    data object UnassignSubjectToTeacher: UsersDestination("unassign_subject_to_teacher")
    data object StudentDetails : UsersDestination("student_details")
    data object StaffDetails : UsersDestination("staff_details")
    data object AddStudentToDropout: UsersDestination("add_student_to_dropout")
    data object RemoveStudentFromDropout: UsersDestination("remove_student_from_dropout")
}