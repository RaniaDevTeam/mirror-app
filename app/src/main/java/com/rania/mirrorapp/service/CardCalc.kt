package com.rania.mirrorapp.service

import com.rania.mirrorapp.model.EntryModel

object CardCalc {
    fun personal(entryModel: EntryModel)
            = theoReduce(sumDateParts(entryModel))

    fun relationship(vararg entryModels: EntryModel): Int {
        var sum = 0
        entryModels.forEach { sum += sumDateParts(it) }
        return theoReduce(sum)
    }

    private fun sumDateParts(entryModel: EntryModel) =
            entryModel.dayOfMonth + entryModel.month + entryModel.year

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