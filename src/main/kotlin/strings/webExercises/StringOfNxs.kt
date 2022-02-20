package strings.webExercises

/**
 * returns number of x's if given value is odd or first odd value when we divide it by 2 consecutively
 */
private fun Nxs(n: Int): String {
    var s = ""
    var N = n
    while (N > 0) {
        s += if (N % 2 == 1) s + "x" else s
        N /= 2
    }
    return s
}

fun main() {
    println(Nxs(15))
    println(Nxs(10))
}