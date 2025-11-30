package com.presencify_admin.home.feature_academics.navigation

sealed class AcademicsDestination(val route: String) {
    data object AcademicsDashboard : AcademicsDestination("academics_dashboard")

    // Curriculum Structure
    data object ManageBranch : AcademicsDestination("manage_branch")
    data object AddBranch : AcademicsDestination("add_branch")
    data object BranchDetails : AcademicsDestination("branch_details")

    data object ManageCourse : AcademicsDestination("manage_course")
    data object AddCourse : AcademicsDestination("add_course")
    data object CourseDetails : AcademicsDestination("course_details")

    data object ManageScheme : AcademicsDestination("manage_scheme")
    data object AddScheme : AcademicsDestination("add_scheme")
    data object SchemeDetails : AcademicsDestination("scheme_details")

    data object ManageUniversity : AcademicsDestination("manage_university")
    data object AddUniversity : AcademicsDestination("add_university")
    data object UniversityDetails : AcademicsDestination("university_details")

    // Academic Grouping
    data object ManageSemester : AcademicsDestination("manage_semester")
    data object AddSemester : AcademicsDestination("add_semester")
    data object SemesterDetails : AcademicsDestination("semester_details")

    data object ManageDivision : AcademicsDestination("manage_division")
    data object AddDivision : AcademicsDestination("add_division")
    data object DivisionDetails : AcademicsDestination("division_details")

    data object ManageBatch : AcademicsDestination("manage_batch")
    data object AddBatch : AcademicsDestination("add_batch")
    data object BatchDetails : AcademicsDestination("batch_details")
}

