package algs4

import kotlin.math.sqrt

/**
 * [Accumulator] class is used for computing running mean, sample standard deviation and
 * sample variance of a stream of real numbers
 *
 * This implementation uses one-pass algorithm. Amount of memory is constant because values are not stored.
 */
class Accumulator() {
    var n = 0  	// number of data values
    var sum = 0.0  // sample variance * (n - 1)
    var mu = 0.0   // sample mean

    /**
     * Adds the specified data value to the accumulator.
     *
     * @param x the data value
     */
    fun addDataValue(x: Double) {
        n++
        val delta = x - mu
        mu += delta / n
        sum += (n-1).toDouble() / n * delta * delta
    }

    /**
     * Returns the mean of the data values
     *
     * @return the mean of the data values
     */
    fun mean():Double {
        return mu
    }

    /**
     * Returns the sample variance of the data values.
     *
     * @return the sample variance of the data values
     */
    fun variance(): Double {
        if (n <= 1) return Double.NaN
        return sum / (n - 1)
    }

    /**
     * Returns the sample standard deviation of the data values.
     *
     * @return the sample standard deviation of the data values
     */
    fun stddev(): Double {
        return sqrt(variance())
    }

    /**
     * Returns the number of data values.
     *
     * @return the number of data values
     */
    fun count(): Int {
        return n
    }

    /**
     * Returns a string representation of this accumulator.
     *
     * @return a string representation of this accumulator
     */
    override fun toString():String {
        return "n = $n, mean = ${mean()}, stddev = ${stddev()}"
    }
}

