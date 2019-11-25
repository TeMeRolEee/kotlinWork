package paraSorter

import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.floor

class BucketSort(inputData: MutableList<Int>) : Sorter(inputData) {
    override var sortType: String
        get() = "BucketSort"
        set(value) {}

    override fun startSort() {
        val data: MutableList<Int> = inputData
        val numberOfBuckets = if (data.size > 100) floor(data.size / 10.0).toInt() else 10
        val sortArray = Array<LinkedList<Int>>(numberOfBuckets + 1) { i -> LinkedList() }
        println(sortArray.size)
        val maxArrayValue = data.max()

        for (i in 0 until data.size) {
            val value: Int = data[i]
            var valueIndex = floor((numberOfBuckets * (value / maxArrayValue!!)).toDouble()).toInt().absoluteValue
            if (valueIndex == data.size) {
                valueIndex = data.size - 1
            }
            sortArray[valueIndex].add(value)
            if (stopSorting) {
                return
            }
        }

        var outputArrayIndex = 0;
        for (list in sortArray) {
            if (list.size > 0) {
                val arrayFromList = Array<Int>(list.size) { i -> list[i] }
                val insertionSort = InsertionSort(arrayFromList.toMutableList())
                insertionSort.startSort()

                for (value in insertionSort.inputData) {
                    data[outputArrayIndex] = value
                    outputArrayIndex++
                    if (stopSorting) {
                        inputData = data
                        return
                    }
                }
            }
        }
    }
}