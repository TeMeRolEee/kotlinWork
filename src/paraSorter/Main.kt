package paraSorter

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.output.TermUi
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.file
import com.sun.tools.javac.Main
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.Time
import java.time.Instant
import kotlin.coroutines.*
import kotlin.experimental.*
import kotlin.random.Random

class CLIParser : CliktCommand() {
    val fileInput: String by argument(help = "Path of the file that will be processed")
    val insertionSort: Boolean by option(
        "-i",
        "--insertion",
        help = "Enable / Disable insertion sort"
    ).flag(default = false)
    val countingSort: Boolean by option("-c", "-counting", help = "Enable / Disable countingSort").flag(default = false)
    val bubbleSort: Boolean by option("-b", "--bubble", help = "Enable / Disable bubbleSort").flag(default = false)
    val mergeSort: Boolean by option("-m", "--merge", help = "Enable / Disable mergeSort").flag(default = false)
    val quickSort: Boolean by option("-q", "--quick", help = "Enable / Disable quickSort").flag(default = false)
    val bucketSort: Boolean by option("-bu", "--bucket", help = "Enable / Disable bucketSort").flag(default = false)

    init {
        versionOption("0.2")
    }

    override fun run() {
        echo("Hello world!")
    }

}

fun main(args: Array<String>) {
    val cliParser: CLIParser = CLIParser()
    cliParser.main(args)
    val random = Random(Instant.now().toEpochMilli())
    val arr: List<Int> = (1..100000).map { random.nextInt() }
    val path : String = Paths.get("").toAbsolutePath().toString() + File.separator + "results" + File.separator
    val fileName : String = "ORIGINAL_" + Time.from(Instant.now()).toInstant().epochSecond + ".txt"
    if (!Files.exists(Paths.get(path).toAbsolutePath())) {
        Files.createDirectories(Paths.get(path).toAbsolutePath())
    }
    File(path + fileName).bufferedWriter().use { out ->
        arr.toMutableList().forEach {
            //println(it)
            out.write(it.toString())
            out.newLine()
        }
    }

    val bubbleSort = BubbleSort(arr.toMutableList())
    val bucketSort = BucketSort(arr.toMutableList())

    bubbleSort.startSort()
    bucketSort.startSort()

    println("DONE")

    bubbleSort.writeResultToFile()
    bucketSort.writeResultToFile()

}
