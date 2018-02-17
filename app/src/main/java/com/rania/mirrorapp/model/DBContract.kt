package com.rania.mirrorapp.model

import android.provider.BaseColumns

object DBContract {
    object BirthdayContract {
        const val TABLE_NAME = "bdays"
        const val COLUMN_ID = "_id"
        const val COLUMN_PERSON_NAME = "person_name"
        const val COLUMN_PRIORITY = "priority"
        const val COLUMN_DAY_OF_MONTH = "day"
        const val COLUMN_MONTH = "month"
        const val COLUMN_YEAR = "year"
    }
}






