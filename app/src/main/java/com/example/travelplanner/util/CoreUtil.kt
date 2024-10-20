package com.example.travelplanner.util

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar
import java.util.Date

object CoreUtil {
    @RequiresApi(Build.VERSION_CODES.O)
    fun Context.ldtToDate(localDateTime: LocalDateTime?): Date {
        val zone = ZoneId.systemDefault()
        val zonedDateTime = ZonedDateTime.of(localDateTime, zone)
        val instant = zonedDateTime.toInstant()
        return Date.from(instant)
    }

    /**
    * 今日の日付を時刻等を0にしたDateを返す。
    **/
    fun Context.resetTodayWithResetTIme(): Date{
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time
    }
}