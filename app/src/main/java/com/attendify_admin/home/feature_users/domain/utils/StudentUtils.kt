package com.attendify_admin.home.feature_users.domain.utils

object StudentUtils {
    fun getCurrentYearFromSem(semesterNumber: Int): String {
        return when (semesterNumber) {
            1 -> "FE"
            2 -> "FE"
            3 -> "SE"
            4 -> "SE"
            5 -> "TE"
            6 -> "TE"
            7 -> "BE"
            8 -> "BE"
            else -> ""
        }
    }

//    fun getCurrentDivision()
}