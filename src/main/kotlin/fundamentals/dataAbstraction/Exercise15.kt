package fundamentals.dataAbstraction

fun main() {
    val read = readInts()
    println(read)

}

fun readInts(): List<Int> {
    val input = readLine()
    val words = input?.split(" ") ?: emptyList<String>()

    return words.map { it.toInt() }
}