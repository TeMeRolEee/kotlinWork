package paraSorter

import java.util.Collections.swap

class BubbleSort(inputData: MutableList<Int>) : Sorter(inputData) {
    override var sortType: String
        get() = "BubbleSort"
        set(value) {}

    override fun startSort() {
        val data : MutableList<Int> = inputData
        var swapped : Boolean
        do {
            swapped = false
            for (i in 0..data.size - 2){
                if (stopSorting) {
                    inputData = data
                    return
                }
                if (data[i] > inputData[i + 1]){
                    swap(data, i, i + 1)
                    swapped = true
                }
            }
        } while (swapped && !stopSorting)
        inputData = data
    }
}