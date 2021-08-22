package fundamentals.basicProgrammingModel

import print


private fun gcd(p: Int, q: Int): Int {
    if (p == 0) return q
    if (q == 0) return p
    if (p == q) return p
    if (p >  q) return gcd(p % q,q)
    return gcd(p, q % p)
}

fun relativelyPrimeArray(n:Int, m: Int):Array<Array<Boolean>> {
    return Array(n) { i -> Array(m) { j -> gcd(i,j)==1 } }
}

fun main() {
    val (n,m) = listOf(9,13)
    val a = relativelyPrimeArray(n,m)

    a.map { it.toList() }.toList().print()
}