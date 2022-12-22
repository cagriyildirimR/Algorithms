package strings.stringSort

import java.io.File
import java.util.Scanner

fun main() {
    val file = File("src/datasets/nameAndSection.txt")
    val scanner = Scanner(file)
    val numberOfInputs = scanner.nextInt()
    val numberOfKeys = scanner.nextInt() + 1

    val values = Array<String>(numberOfInputs) { "" }
    val result = Array<String>(numberOfInputs) { "" }
    val keys =  IntArray(numberOfInputs)

    val count = IntArray(numberOfKeys)

    repeat(numberOfInputs) {
        values[it] = scanner.next()
        keys[it] = scanner.nextInt()
    }

    repeat(numberOfInputs){
        count[keys[it]]++
    }

    repeat(numberOfKeys) {
        if (it > 0) count[it] += count[it-1]
    }

    repeat(numberOfInputs) {
        result[count[keys[it]-1]++] = values[it]
    }

    println(result.toList())
}