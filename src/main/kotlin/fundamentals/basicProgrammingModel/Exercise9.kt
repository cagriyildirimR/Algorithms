package fundamentals.basicProgrammingModel

fun Int.toBinaryString():String {
    var s = ""
    var n: Int = this
    while (n > 0) {
        s += (n % 2)
        n /= 2
    }
    return s.reversed()
}


fun main() {
    val n1: Int = 127.also { println(it.toBinaryString()) }
    val n2: Int = 71.also { println(it.toBinaryString()) }
    val n3: Int = 64.also { println(it.toBinaryString()) }
}