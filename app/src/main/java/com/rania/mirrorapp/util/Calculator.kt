package com.rania.mirrorapp.util

import com.rania.mirrorapp.model.BirthdayModel

object Calculator {
    fun personalityCard(birthdayModel: BirthdayModel)
            = theoReduce(sumDateParts(birthdayModel))

    fun relationshipCard(vararg birthdayModels: BirthdayModel): Int {
        var sum = 0
        birthdayModels.forEach { sum += sumDateParts(it) }
        return theoReduce(sum)
    }

    private fun sumDateParts(birthdayModel: BirthdayModel) =
            birthdayModel.dayOfMonth + birthdayModel.month + birthdayModel.year

    private fun theoReduce(num: Int): Int {

        var numToReduce = num
        do {
            var tot = 0
            while (numToReduce > 0) {
                tot += numToReduce % 10
                numToReduce /= 10
            }
            numToReduce = tot
        } while (numToReduce > 22)

        return numToReduce
    }
}