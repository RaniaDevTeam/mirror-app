package com.rania.mirrorapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rania.mirrorapp.model.BirthdayModel
import com.rania.mirrorapp.model.DBContract.BirthdayContract
import com.rania.mirrorapp.model.Priority
import java.util.LinkedList

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VER) {
    private companion object {
        const val DB_NAME = "MirrorRelationships.db"
        const val DB_VER = 1

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${BirthdayContract.TABLE_NAME} (" +
                        "${BirthdayContract.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "${BirthdayContract.COLUMN_PERSON_NAME} TEXT," +
                        "${BirthdayContract.COLUMN_PRIORITY} TINYINT, " +
                        "${BirthdayContract.COLUMN_DAY_OF_MONTH} TINYINT, " +
                        "${BirthdayContract.COLUMN_MONTH} TINYINT, " +
                        "${BirthdayContract.COLUMN_YEAR} SMALLINT) "

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${BirthdayContract.TABLE_NAME}"
        private val SQL_SELECT_ALL = "SELECT * FROM ${BirthdayContract.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    fun cleanUp() {
        writableDatabase.execSQL("DELETE FROM ${BirthdayContract.TABLE_NAME}")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVer: Int, newVer: Int) {
        // replace with better migration strategy
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertBirthdayEntry(entry: BirthdayModel): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(BirthdayContract.COLUMN_PERSON_NAME, entry.nameOfPerson)
            put(BirthdayContract.COLUMN_PRIORITY, entry.priority.ordinal)
            put(BirthdayContract.COLUMN_DAY_OF_MONTH, entry.dayOfMonth)
            put(BirthdayContract.COLUMN_MONTH, entry.month)
            put(BirthdayContract.COLUMN_YEAR, entry.year)
        }

        // Insert the new row, returning the primary key value of the new row
        return -1L != db.insert(BirthdayContract.TABLE_NAME, null, values)
    }

    fun retrieveAll(): Array<LinkedList<BirthdayModel>> {
        val result = Array(Priority.values().size) {
            LinkedList<BirthdayModel>()
        }

        val db = readableDatabase
        val cursor = db.rawQuery(SQL_SELECT_ALL, null)

        if (cursor.moveToFirst()) {
            do {
                val priority = cursor.getIntFor(BirthdayContract.COLUMN_PRIORITY)
                result[priority].add(
                        BirthdayModel.Builder()
                                .withNameOfPerson(
                                        cursor.getStringFor(BirthdayContract.COLUMN_PERSON_NAME))
                                .withPriority(
                                        Priority.from(priority))
                                .withDayOfMonth(
                                        cursor.getIntFor(BirthdayContract.COLUMN_DAY_OF_MONTH))
                                .withMonth(
                                        cursor.getIntFor(BirthdayContract.COLUMN_MONTH))
                                .withYear(
                                        cursor.getIntFor(BirthdayContract.COLUMN_YEAR))
                                .build())
            } while (cursor.moveToNext())

        }
        return result
    }
}
