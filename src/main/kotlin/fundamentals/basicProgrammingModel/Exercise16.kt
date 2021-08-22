package fundamentals.basicProgrammingModel

fun exR1(n: Int):String {
    if (n <= 0) return ""
    return exR1(n - 3) + n + exR1(n - 2) + n
}