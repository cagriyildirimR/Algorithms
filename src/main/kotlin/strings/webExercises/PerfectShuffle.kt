package strings.webExercises

private fun mystery(s: String, t: String): String{
    require(s.length == t.length) {
        "s and t must have same length"
    }
    val N = s.length
    if (N <= 1) return s + t
    val a = mystery(s.substring(0, N/2), t.substring(0, N/2))
    val b = mystery(s.substring(N/2, N), t.substring(N/2, N))
    return a + b
}

fun main() {
    println(mystery("computer", "12345678"))
}
