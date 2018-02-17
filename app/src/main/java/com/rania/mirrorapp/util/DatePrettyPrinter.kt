package com.rania.mirrorapp.util

import android.content.Context
import com.rania.mirrorapp.R
import com.rania.mirrorapp.model.BirthdayModel

class DatePrettyPrinter(context: Context) {
    private val months = context.resources.getStringArray(R.array.months)

   fun prettify(birthday: BirthdayModel)
    = "${months[birthday.month - 1]} ${birthday.dayOfMonth}, ${birthday.year}"


}