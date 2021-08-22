package fundamentals.basicProgrammingModel

fun lg(N: Int): Int {
    var r = 0
    var w: Int = N
    val e = 2.71
    while (w > e) {
        r++
        w = (w / e).toInt()
    }
    return r
}