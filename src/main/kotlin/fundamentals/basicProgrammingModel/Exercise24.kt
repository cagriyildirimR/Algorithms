package fundamentals.basicProgrammingModel

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val p = scanner.nextInt()
    val q = scanner.nextInt()

    val result = gcd(p,q)

    println("Greatest Common Divisor for p = $p, q = $q is $result")
}

private fun gcd(p: Int, q: Int): Int {
    println("p: $p | q: $q")
    if (p == 0) return q
    if (q == 0) return p
    if (p == q) return p
    if (p >  q) return gcd(p % q,q)
    return gcd(p, q % p)
}

//fun gcd(p: Int, q: Int, iter: Int = 0): Int {
//    println("Iteration $iter | p: $p | q: $q")
//    if (p == 0) return q
//    if (q == 0) return p
//    if (p == q) return p
//    if (p >  q) return gcd(p % q,q, iter + 1)
//    return gcd(p, q % p, iter + 1)
//}