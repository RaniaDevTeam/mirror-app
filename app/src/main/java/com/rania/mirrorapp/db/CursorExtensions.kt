package com.rania.mirrorapp.db

import android.database.Cursor

fun Cursor.getStringFor(columnName: String) = getFor(this::getString, columnName)
fun Cursor.getIntFor(columnName: String) = getFor(this::getInt, columnName)

private fun <T> Cursor.getFor(accessor: (Int) -> T, columnName: String): T {
    return accessor(getColumnIndex(columnName))
}