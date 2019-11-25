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
import kotlin.coroutines.*
import kotlin.experimental.*

class CLIParser : CliktCommand() {
    val fileInput : String by argument(help = "Path of the file that will be processed")
    val bubbleSort : Boolean by option("-b", "--bubble", help = "Bubble sort").flag(default = false)
    val mergeSort : Boolean by option("-m", "-merge", help = "Mergesort").flag(default = false)
    val insertionSort : Boolean by option("-i", "--insertion").flag(default = false)

    init {
        versionOption("0.2")
    }

    override fun run() {
        echo("Hello world!")
    }

}

fun main(args : Array<String>) {
    val cliParser : CLIParser = CLIParser()
    cliParser.main(args)
}
