package com.codelabs.agrimate.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    fun convertLongToTime(
        time: Long,
        locale: Locale = Locale.getDefault(),
        formatPattern: String = "dd/MM/yyyy",
    ): String {
        val date = Date(time)
        val format = SimpleDateFormat(formatPattern, locale)
        return format.format(date)
    }

    fun convertDateToLong(
        date: String,
        locale: Locale = Locale.getDefault(),
        formatPattern: String = "dd/MM/yyyy",
    ): Long {
        val dateFormat = SimpleDateFormat(formatPattern, locale)
        return dateFormat.parse(date)?.time ?: 0
    }

    fun currentTime(): Long {
        return System.currentTimeMillis()
    }
}