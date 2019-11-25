package paraSorter

import java.util.Collections.swap
import kotlin.math.floor

class HeapSort(inputData: MutableList<Int>) : Sorter(inputData) {
    override var sortType: String
        get() = "HeapSort"
        set(value) {}

    private var heapSize = 0

    override fun startSort() {
        val data: MutableList<Int> = inputData
        buildMaxHeap(data)
        var i: Int = data.size - 1
        while (i >= 1){
            swap(data, i, 0)
            heapSize--
            maxHeapify(data, 0)
            i--
        }
    }

    private fun buildMaxHeap(data: MutableList<Int>){
        heapSize = data.size
        var i: Int = floor(data.size / 2.0).toInt()
        while (i >= 0){
            maxHeapify(data, i)
            i--
        }
    }

    private fun maxHeapify(data: MutableList<Int>, i: Int){
        val leftElementIndex = left(i)
        val rightElementIndex = right(i)
        var largestElementIndex : Int = i

        if ( (leftElementIndex <= heapSize - 1) && (data[leftElementIndex] > data[i]) ){
            largestElementIndex = leftElementIndex
        }

        if ( (rightElementIndex <= heapSize - 1) && (data[rightElementIndex] > data[largestElementIndex]) ){
            largestElementIndex = rightElementIndex
        }

        if (largestElementIndex != i){
            swap(data, i, largestElementIndex)
            maxHeapify(data, largestElementIndex)
        }
    }

    private fun left(i: Int) : Int{
        return 2 * i + 1
    }

    private fun right(i: Int) : Int{
        return 2 * i + 2
    }
}