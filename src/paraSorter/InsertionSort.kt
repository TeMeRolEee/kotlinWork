package paraSorter

class InsertionSort(inputData: MutableList<Int>) : Sorter(inputData) {
    override var sortType: String
        get() = "InsertionSort"
        set(value) {}

    override fun startSort() {
        val data: MutableList<Int> = inputData
        for (j in 1 until data.size) {
            var i = j - 1
            val processedValue = data[j]
            while ((i >= 0) && (data[i] > processedValue) && !stopSorting) {
                data[i + 1] = data[i]
                i--
            }
            if (stopSorting) {
                inputData = data
                return
            }
            data[i + 1] = processedValue
        }
        inputData = data
    }
}