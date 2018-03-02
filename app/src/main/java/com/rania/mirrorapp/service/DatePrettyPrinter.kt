package com.rania.mirrorapp.service

import android.content.Context
import com.rania.mirrorapp.R
import com.rania.mirrorapp.model.EntryModel

class DatePrettyPrinter(context: Context) {
    private val months = context.resources.getStringArray(R.array.months)

   fun prettify(entry: EntryModel)
    = "${months[entry.month - 1]} ${entry.dayOfMonth}, ${entry.year}"


}