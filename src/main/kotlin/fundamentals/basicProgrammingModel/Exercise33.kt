package fundamentals.basicProgrammingModel

import printArray

object Matrix {
    fun dot(xs: Array<Double>, ys: Array<Double>): Double {
        return xs.zip(ys) { x, y -> x * y }.sum()
    }

    fun mult(xss: Array<Array<Double>>, yss: Array<Array<Double>>): Array<Array<Double>> {
        val n = xss.size
        val m = yss[0].size
        val result = Array(n) { Array(m) { 0.0 } }

        val yssTr = transpose(yss)

        for (i in 0 until n)
            for (j in 0 until m) result[i][j] = dot(xss[i], yss[j])
        return result
    }

    fun transpose(xss: Array<Array<Double>>): Array<Array<Double>> {
        if (xss[0].isEmpty()) return emptyArray<Array<Double>>()
        return arrayOf(xss.map { it.first() }.toTypedArray()) + transpose(xss.map { it.drop(1).toTypedArray() }
            .toTypedArray())
    }

    fun mult(xss: Array<Array<Double>>, ys: Array<Double>): Array<Double> {
        return transpose(mult(xss, transpose(arrayOf(ys))))[0]
    }

    fun mult(ys: Array<Double>, xss: Array<Array<Double>>): Array<Double> {
        return transpose(mult(xss, transpose(arrayOf(ys))))[0]
    }
}



fun main() {
    val xs = arrayOf(4.0, 5.0, 8.0)
    val ys = arrayOf(10.0,2.0, 1.0)

    val xss = arrayOf(arrayOf(1.0,2.0,3.0),
                      arrayOf(4.0,5.0,6.0))
    val yss = arrayOf(arrayOf(5.0,5.0,5.0),
                      arrayOf(5.0,5.0,5.0),
                      arrayOf(5.0,5.0,5.0))

    Matrix.dot(xs,ys).also(::println)
    Matrix.transpose(arrayOf(xs,ys)).also(::printArray)
    Matrix.mult(xss,yss).also(::printArray)
    Matrix.mult(xss,xs).toList().also(::println)
    Matrix.mult(xs,xss).toList().also(::println)
}

