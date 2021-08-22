package fundamentals.basicProgrammingModel

fun main() {
    var a = 2023
    var b = 42
    var c = 100

    if (a > b) { a = b.also { b = a } }
    if (a > c) { a = c.also { c = a } }
    if (b > c) { b = c.also { c = b } }

    listOf(a,b,c).also(::println)
}