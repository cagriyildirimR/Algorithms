package fundamentals.dataAbstraction

import java.util.*

class Interval1D(val lo: Double, val hi: Double) {
    fun length() = hi - lo

    fun contains(x: Double) = x in (lo..hi)

    fun intersects(that: Interval1D) = ((that.lo).toInt()..(that.hi).toInt()).any { it in ((lo.toInt())..(hi.toInt())) }

//    fun draw() = TODO()

    override fun toString(): String {
        return "1D($lo,$hi)"
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val arr = Array<Interval1D>(n) { Interval1D(0.0,0.0) }

    for (i in 0 until n) {
        val lo = scanner.nextDouble()
        val hi = scanner.nextDouble()
        arr[i] = Interval1D(lo, hi)
    }

    val result = mutableListOf<Pair<Interval1D, Interval1D>>()

    for (i in 0 until n-1)
        for (j in i+1 until n) {
            if (arr[i].intersects(arr[j])) {
                result.add(Pair(arr[i], arr[j]))
            }
        }

    println(result)

}

