package fundamentals.dataAbstraction

import print
import java.util.*
import kotlin.math.abs
import kotlin.math.min

class Interval2D(val x: Interval1D, val y: Interval1D) {
    fun area() = x.length() * y.length()

    fun contains(p: Point2D) = x.contains(p.x) && y.contains(p.y)

    fun intersects(that: Interval2D) = x.intersects(that.x) && y.intersects(that.y)

    // fun draw() = TODO()

    override fun toString(): String {
        return "[x:$x, y:$y]"
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val min = scanner.nextDouble()
    val max = scanner.nextDouble()

    val result = mutableListOf<Interval2D>()

    for (i in 0 until n) {
        val p1 = listOf(randomInterval(min, max), randomInterval(min,max)).sorted()
        val p2 = listOf(randomInterval(min, max), randomInterval(min,max)).sorted()

        result.add(Interval2D(Interval1D(p1[0], p1[1]),Interval1D(p2[0],p2[1])))
    }

    result.print()
}

fun randomInterval(min: Double, max: Double) = Math.random() * (max + min) - min