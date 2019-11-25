package paraSorter

import java.util.Collections.swap

class QuickSort(inputData: MutableList<Int>) : Sorter(inputData) {
    override var sortType: String
        get() = "QuickSort"
        set(value) {}

    override fun startSort() {
        val data: MutableList<Int> = inputData
        sortArrayPart(data, 0, data.size - 1)
        inputData = data
    }

    private fun partition(data: MutableList<Int>, fromIndex: Int, toIndex: Int): Int {
        val lastElementValue = data[toIndex]
        var i = fromIndex - 1
        for (j in fromIndex until toIndex) {
            if (data[j] <= lastElementValue) {
                i++
                swap(data, i, j)
            }
        }

        swap(data, i + 1, toIndex)
        return i + 1
    }

    private fun sortArrayPart(arr: MutableList<Int>, fromIndex: Int, toIndex: Int) {
        if (fromIndex < toIndex) {
            val middleIndex = partition(arr, fromIndex, toIndex)
            sortArrayPart(arr, fromIndex, middleIndex - 1)
            sortArrayPart(arr, middleIndex + 1, toIndex)
        }
    }
}
