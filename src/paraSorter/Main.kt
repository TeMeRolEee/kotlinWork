package paraSorter

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.Time
import java.time.Instant
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.random.Random

class CLIParser : CliktCommand() {
    val countingSort: Boolean by option("-c", "-counting", help = "Enable / Disable countingSort").flag(default = false)
    val bubbleSort: Boolean by option("-b", "--bubble", help = "Enable / Disable bubbleSort").flag(default = false)
    val mergeSort: Boolean by option("-m", "--merge", help = "Enable / Disable mergeSort").flag(default = false)
    val quickSort: Boolean by option("-q", "--quick", help = "Enable / Disable quickSort").flag(default = false)
    val bucketSort: Boolean by option("-bu", "--bucket", help = "Enable / Disable bucketSort").flag(default = false)

    init {
        versionOption("0.8")
    }

    override fun run() {
        echo("Hello world!")
    }
}

fun launch(context: CoroutineContext = EmptyCoroutineContext, block: suspend () -> Unit) =
    block.startCoroutine(Continuation(context) { result ->
        result.onFailure { exception ->
            val currentThread = Thread.currentThread()
            currentThread.uncaughtExceptionHandler.uncaughtException(currentThread, exception)
        }
    })


fun main(args: Array<String>) {
    val cliParser: CLIParser = CLIParser()
    cliParser.main(args)
    val random = Random(Instant.now().toEpochMilli())
    val inputData: List<Int> = (1..100000).map { random.nextInt() }
    val path: String = Paths.get("").toAbsolutePath().toString() + File.separator + "results" + File.separator
    val fileName: String = "ORIGINAL_" + Time.from(Instant.now()).toInstant().epochSecond + ".txt"
    if (!Files.exists(Paths.get(path).toAbsolutePath())) {
        Files.createDirectories(Paths.get(path).toAbsolutePath())
    }
    File(path + fileName).bufferedWriter().use { out ->
        inputData.toMutableList().forEach {
            //println(it)
            out.write(it.toString())
            out.newLine()
        }
    }

    val bubbleSort = BubbleSort(inputData.toMutableList())
    val bucketSort = BucketSort(inputData.toMutableList())
    //val countingSort = CountingSort(inputData.toMutableList())
    val mergeSort = MergeSort(inputData.toMutableList())
    val quickSort = QuickSort(inputData.toMutableList())

    bubbleSort.startSort()
    bucketSort.startSort()
    //countingSort.startSort()
    mergeSort.startSort()
    quickSort.startSort()

    println("DONE")

    bubbleSort.writeResultToFile()
    bucketSort.writeResultToFile()
    mergeSort.writeResultToFile()
    quickSort.writeResultToFile()

}
