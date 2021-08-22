package fundamentals.basicProgrammingModel

/**
 * Write  a  program  that  takes  three  integer  command-line  arguments  and  prints
 * equal if all three are equal, and not equal otherwise.
 */

fun <T> allSame(listOfThings: List<T>): Boolean {
    val fst = listOfThings[0]
    val rest = listOfThings.drop(1)
    return rest.all { it == fst }
}

fun main() {
    val read = readLine() ?: ""
    val listOfInts = read.split(" ").map { it.toInt() }
    if (allSame(listOfInts)) println("equal") else println("not equal")
}

