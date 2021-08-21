package fundamentals.dataAbstraction

import java.util.*
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class Point2D(val x: Double, val y: Double) {
    val r = sqrt( x * x + y * y)
    val theta = atan2(y,x) * 180 / Math.PI

    fun distTo(other: Point2D) = sqrt((this.x - other.x).pow(2) +
                                         (this.y - other.y).pow(2) )

    override fun toString(): String {
        return "Point2D($x,$y)"
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val l = List(n) { Point2D(randomUnitSquare(), randomUnitSquare()) }

    // most separated points on unit square; used for initialization
    var result = Pair<Point2D,Point2D>(Point2D(0.0,0.0), Point2D(1.0,1.0))
    var min = result.first.distTo(result.second)


    for (i in 0 until n - 1) {
        for (j in i+1 until n) {
            val d = l[i].distTo(l[j])
            if ( d < min) {
                result = Pair(l[i], l[j])
                min = d
            }
        }
    }

    println("Closest points are $result, distance between the points is $min")
}

fun randomUnitSquare() = Math.random()


