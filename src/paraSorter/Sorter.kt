package paraSorter

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.sql.Time
import java.time.Instant

public abstract class Sorter(var inputData: MutableList<Int>) {
    abstract var sortType : String
    public var stopSorting : Boolean = false

    abstract fun startSort()

    fun writeResultToFile() {
        val path : String = Paths.get("").toAbsolutePath().toString() + File.separator + "results" + File.separator
        val fileName : String = sortType + "_" + Time.from(Instant.now()).toInstant().epochSecond + ".txt"
        if (!Files.exists(Paths.get(path).toAbsolutePath())) {
            Files.createDirectory(Paths.get(path).toAbsolutePath())
        }
        println("Writing to ${Paths.get("").toAbsolutePath().toString() + File.separator + "results" + File.separator + sortType + "_" + Time.from(Instant.now()).toInstant().epochSecond + ".txt"}")
        File(path + fileName).bufferedWriter().use { out ->
            inputData.forEach {
                out.write(it.toString())
                out.newLine()
            }
        }
    }
}