package com.attendify_admin.common.utils

import java.util.Calendar

object TimeUtil {
    fun getPastTwentyYears(): List<String> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val pastTwentyYears = mutableListOf<String>()
        repeat(20) {
            pastTwentyYears.add((currentYear - it).toString())
        }
        return pastTwentyYears
    }
}