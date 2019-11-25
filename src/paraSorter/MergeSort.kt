package paraSorter

import kotlin.math.floor

class MergeSort(inputData: MutableList<Int>) : Sorter(inputData) {
    override var sortType: String
        get() = "MergeSort"
        set(value) {}

    override fun startSort() {
        val data: MutableList<Int> = inputData
        sortArrayPiece(data, 0, data.size - 1)
        inputData = data
    }

    private fun sortArrayPiece(data: MutableList<Int>, startIndex: Int, endIndex: Int) {
        val pieceSize = endIndex - startIndex + 1
        if (pieceSize == 1) {
            return
        }
        val middleElementIndex = floor((startIndex + endIndex) / 2.0).toInt()
        sortArrayPiece(data, startIndex, middleElementIndex)
        sortArrayPiece(data, middleElementIndex + 1, endIndex)
        merge(data, startIndex, middleElementIndex, endIndex)
    }

    private fun merge(data: MutableList<Int>, startIndex: Int, middleIndex: Int, endIndex: Int) {
        val leftArray = data.subList(startIndex, middleIndex + 1)
        val rightArray = data.subList(middleIndex + 1, endIndex + 1)
        var i = 0
        var j = 0
        for (k in startIndex..endIndex) {
            if ((i <= leftArray.size - 1) && ((j >= rightArray.size) || (leftArray[i] <= rightArray[j]))) {
                data[k] = leftArray[i]
                i++
            } else {
                data[k] = rightArray[j]
                j++
            }
        }
    }
}