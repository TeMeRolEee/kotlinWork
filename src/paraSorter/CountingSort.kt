package paraSorter

import kotlin.math.max

class CountingSort(inputData: MutableList<Int>) : Sorter(inputData) {
    override var sortType: String
        get() = "CountingSort"
        set(value) {}

    override fun startSort() {
        val data: MutableList<Int> = inputData
        val countsArray = Array<Int>(data.max()!!) { i -> 0 }

        for (i in 0 until data.size) {
            val countsArrayIndex = data[i] - 1
            countsArray[countsArrayIndex] = countsArray[countsArrayIndex] + 1
        }

        for (j in 1 until countsArray.size) {
            countsArray[j] = countsArray[j] + countsArray[j - 1]
        }

        val outputArray = Array<Int>(data.size) { i -> 0 }
        var k = data.size - 1
        while (k >= 0) {
            val countsArrayIndex = data[k] - 1
            outputArray[countsArray[countsArrayIndex] - 1] = data[k]
            countsArray[countsArrayIndex]--
            k--
        }
        inputData = data
    }
}