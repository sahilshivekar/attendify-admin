package com.attendify_admin.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

object DateTimeUtil {
    fun getPastTenYears(): ImmutableList<String> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        val pastTenYears = List(10) { i ->
            (currentYear - i).toString()
        }.toImmutableList()

        return pastTenYears
    }

    fun getDateInDDMMYYYYFromYYYYMMDD(date: String): String {
        return date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0,4)
    }

    fun getDateInYYYYMMDDFromDDMMYYYY(date: String): String {
        return date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0,2)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun datePickerMillisToYYYYMMDD(millis: Long): String {
        val selectedLocalDate = Instant
            .ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        val formattedDate = selectedLocalDate.format(formatter)
        return formattedDate
    }
}