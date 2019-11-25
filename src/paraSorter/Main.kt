package paraSorter

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption
import com.github.ajalt.clikt.parameters.types.int
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.Time
import java.time.Instant
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.random.Random


class CLIParser : CliktCommand() {
    public val insertionSort: Boolean by option("-i", "--insert", help = "Enable / Disable insertionSort").flag(default = false)
    public val bubbleSort: Boolean by option("-bb", "--bubble", help = "Enable / Disable bubbleSort").flag(default = false)
    public val heapSort: Boolean by option("-hp", "--heap", help = "Enable / Disable heapSort").flag(default = false)
    public val quickSort: Boolean by option("-q", "--quick", help = "Enable / Disable quickSort").flag(default = false)
    public val bucketSort: Boolean by option("-bk", "--bucket", help = "Enable / Disable bucketSort").flag(default = false)

    public val dataSize : Int by option("-s", "--size", help = "Size of the array that will be created and processed").int().default(100000)

    init {
        versionOption("1.0")
    }

    override fun run() {
        echo("Starting up!\n")
        echo("Data size is:\t" + dataSize)
    }
}

fun main(args: Array<String>) {
    val cliParser: CLIParser = CLIParser()
    cliParser.main(args)
    val random = Random(Instant.now().toEpochMilli())
    val inputData: List<Int> = (1..cliParser.dataSize).map { random.nextInt() }
    val path: String = Paths.get("").toAbsolutePath().toString() + File.separator + "results" + File.separator
    val fileName: String = "ORIGINAL_" + Time.from(Instant.now()).toInstant().toEpochMilli() + ".txt"
    if (!Files.exists(Paths.get(path).toAbsolutePath())) {
        Files.createDirectories(Paths.get(path).toAbsolutePath())
    }
    File(path + fileName).bufferedWriter().use { out ->
        inputData.toMutableList().forEach {
            out.write(it.toString())
            out.newLine()
        }
    }






    if (cliParser.bubbleSort) {
        val bubbleSort = BubbleSort(inputData.toMutableList())
        val bubbleJob = thread() {
            val begin : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            bubbleSort.startSort()
            val end : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            println("JOB:\tBUBBLE\t TIME:\t" + (end - begin) + "ms\t START TIME:\t" + begin + "\t END TIME\t" + end)
            bubbleSort.writeResultToFile()
        }
    }
    if (cliParser.insertionSort) {
        val insertionSort = InsertionSort(inputData.toMutableList())
        val insertionJob = thread() {
            val begin : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            insertionSort.startSort()
            val end : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            println("JOB:\tINSERTION\t TIME:\t" + (end - begin) + "ms\t START TIME:\t" + begin + "\t END TIME\t" + end)
            insertionSort.writeResultToFile()
        }
    }

    if (cliParser.bucketSort) {
        val bucketSort = BucketSort(inputData.toMutableList())
        val bucketJob = thread() {
            val begin : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            bucketSort.startSort()
            val end : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            println("JOB:\tBUCKET\t TIME:\t" + (end - begin) + "ms\t START TIME:\t" + begin + "\t END TIME\t" + end)
            bucketSort.writeResultToFile()
        }
    }
    if (cliParser.heapSort) {
        val heapSort = HeapSort(inputData.toMutableList())
        val heapJob = thread() {
            val begin : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            heapSort.startSort()
            val end : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            println("JOB:\tHEAP\t TIME:\t" + (end - begin) + "ms\t START TIME:\t" + begin + "\t END TIME\t" + end)
            heapSort.writeResultToFile()
        }
    }

    if (cliParser.quickSort) {
        val quickSort = QuickSort(inputData.toMutableList())
        val quickJob = thread() {
            val begin : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            quickSort.startSort()
            val end : Long = Time.from(Instant.now()).toInstant().toEpochMilli()
            println("JOB:\tQUICK\t TIME:\t" + (end - begin) + "ms\t START TIME:\t" + begin + "\t END TIME\t" + end)
            quickSort.writeResultToFile()
        }
    }
}
