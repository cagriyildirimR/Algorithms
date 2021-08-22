package fundamentals.basicProgrammingModel

import kotlin.math.abs


fun first() {
    var t: Double = 9.0

    while (abs(t - 9.0/t) > .001)
        t = (9.0/t + t) / 2.0
    println("a. Result is %.5f".format(t))
}

fun second() {
    var sum = 0
    for (i in 1 until 1000)
        for (j in 0 until i)
            sum++

    println("b. Sum is $sum")
}

fun third() {
    var sum = 0
    var i = 1
    // Java's for (int i = 1; i < 1000; i *= 2) is really handy here. Is there an alternative?
    while (i < 1000) {
        for (j in 0 until 1000)
            sum++
        i *= 2
    }

    println("c. Sum is $sum")
}

fun main() {
    first()
    second()
    third()
}