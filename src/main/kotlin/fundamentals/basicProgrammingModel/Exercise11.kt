package fundamentals.basicProgrammingModel

fun starsAndSpaces(l: List<List<Boolean>>): String {
    var s = ""
    l.forEach { out -> out.forEach { s += if (it) "*" else " " }; s += "\n" }
    return s
}

fun main() {
    val bs = listOf(listOf(true, false, true),
                    listOf(false, true, false),
                    listOf(true, false, true))

    println(starsAndSpaces(bs))

    val bs2: List<List<Boolean>> = List(10) { row -> List(10) { column -> (row + column) % 2 == 0 } }
    println(starsAndSpaces(bs2))
}