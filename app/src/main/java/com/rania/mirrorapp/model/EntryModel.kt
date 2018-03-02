package com.rania.mirrorapp.model

/**
 * Created by Admin on 17.2.2018..
 */
class EntryModel private constructor() {
    class Builder {
        val model = EntryModel()
        fun withNameOfPerson(nameOfPerson: String): Builder {
            model.nameOfPerson = nameOfPerson
            return this
        }
        fun withPriority(priority: Priority): Builder {
            model.priority = priority
            return this
        }

        fun withDayOfMonth(value: Int):Builder {
            model.dayOfMonth = value
            return this
        }

        fun withMonth(value: Int):Builder {
            model.month = value
            return this
        }

        fun withYear(value: Int):Builder {
            model.year = value
            return this
        }

        fun build(): EntryModel {
            return model
        }
    }

    lateinit var nameOfPerson: String
        private set
    lateinit var priority: Priority
        private set
    var dayOfMonth: Int = -1
        private set
    var month: Int = -1
        private set
    var year: Int = -1
        private set

}