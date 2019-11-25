package paraSorter

import java.util.Collections.swap

class BubbleSort(inputData: Array<Int>) : Sorter(inputData) {
    override var sortType: String
        get() = sortType
        set(value) {
            sortType = value
        }

    override fun startSort() {
        var swapped : Boolean
        val data : MutableList<Int> = inputData.toMutableList()
        do {
            swapped = false
            for (i in 0..data.size - 2){
                if (data[i] > inputData[i + 1]){
                    swap(data, i, i + 1)
                    swapped = true
                }
            }
        } while (swapped && !stopSorting)
    }

    override fun stopSort() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}