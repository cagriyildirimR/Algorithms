package fundamentals.dataAbstraction

private fun mystery(s: String): String {
    /**
     * mystery function reverses String s
     * e.g when s = "Hello"
     * return "olleH"
     */
    val n = s.length
    if (n <= 1) return s

    val a = s.substring(0,n/2)
    val b = s.substring(n/2,n)
    return mystery(b) + mystery(a)
}

fun main() {
    println(mystery("Hello"))
    // olleH

    assert(mystery("Hello") == "olleH2")
}