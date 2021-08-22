package fundamentals.basicProgrammingModel

fun histogram(a: Array<Int>, M: Int): Array<Int> {
    val r = Array(M) { 0 }
    return r.mapIndexed { index, _ -> a.filter { it == index }.count() }.toTypedArray()
}