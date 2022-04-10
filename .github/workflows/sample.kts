#!/usr/bin/env kotlin

import java.io.File

// val input = File("README.md") // Assuming you ran checkout before
// val output = File("result.txt")
// val readmeFirstLine = input.readLines().first()
// output.writeText(readmeFirstLine)

val command = "find . -name *.pu"

val result = Runtime.getRuntime().exec(command).let { process ->
    process.inputStream.use { stream ->
        stream.bufferedReader().use { reader ->
            reader.lineSequence()
                .filter {
                    hasIncludeFunction(it)
                }
                .toList()
        }
    }
}

println(result)


fun hasIncludeFunction(filePath: String): Boolean {
    return File(filePath)
        .useLines { lineSequences: Sequence<String> ->
            lineSequences
                .map {
                    it.contains("\$include")
                }
                .contains(true)
        }
}
