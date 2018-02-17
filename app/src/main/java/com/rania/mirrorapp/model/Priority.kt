package com.rania.mirrorapp.model

enum class Priority {
    APP_USER,
    FAVORITE,
    NORMAL;

    companion object {
        fun from(anOrdinal: Int) =
                values().getOrElse(anOrdinal) { NORMAL }
    }
}
