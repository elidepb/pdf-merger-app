package com.app.fusionarpdfs.core.utils

import java.text.DateFormat
import java.util.Date

fun formatMergeDate(timestamp: Long): String {
    val formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
    return formatter.format(Date(timestamp))
}
