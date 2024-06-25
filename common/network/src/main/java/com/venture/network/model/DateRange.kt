package com.venture.network.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class DateRange {
    TODAY,
    WEEK,
    MONTH;

    fun getDateRange(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()

        return when (this) {
            TODAY -> {
                dateFormat.format(calendar.time)
            }

            WEEK -> {
                calendar.add(Calendar.DAY_OF_YEAR, -7)
                dateFormat.format(calendar.time)
            }

            MONTH -> {
                calendar.add(Calendar.DAY_OF_YEAR, -30)
                dateFormat.format(calendar.time)
            }
        }
    }
}
