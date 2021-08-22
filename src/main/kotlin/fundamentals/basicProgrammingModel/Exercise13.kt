package fundamentals.basicProgrammingModel

fun <T> tr(l: List<List<T>>): List<List<T>> {
    if (l[0].isEmpty()) return emptyList<List<T>>()
    return listOf(l.map { it.first() }) + tr(l.map { it.drop(1) })
}

fun main() {
    val ns = listOf(listOf(1, 2, 3),
                    listOf(4, 5, 6),
                    listOf(7, 8, 9))

    println(tr(ns)) // [[1, 4, 7], [2, 5, 8], [3, 6, 9]]
}
