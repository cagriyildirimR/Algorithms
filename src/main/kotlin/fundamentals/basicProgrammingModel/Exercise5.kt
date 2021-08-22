package fundamentals.basicProgrammingModel

fun betweenZeroAndOne(a: Double, b: Double) {
    if (a in 0.0..1.0 && b in 0.0..1.0) println("true") else println("false")
}

fun main() {
    betweenZeroAndOne(0.2,0.5) // Should print true
    betweenZeroAndOne(2.0,0.1) // Should print false
    betweenZeroAndOne(0.1,2.0) // Should print false
}