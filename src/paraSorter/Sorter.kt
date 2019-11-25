package paraSorter

import java.io.File
import java.sql.Time
import java.time.Instant

public abstract class Sorter(var inputData: MutableList<Int>) {
    abstract var sortType : String
    public var stopSorting : Boolean = false

    abstract fun startSort()

    fun writeResultToFile() {
        File(sortType + "_" + Time.from(Instant.now()) + ".txt").bufferedWriter().use { out ->
            inputData.forEach {
                out.write(it)
                out.newLine()
            }
        }
    }
}