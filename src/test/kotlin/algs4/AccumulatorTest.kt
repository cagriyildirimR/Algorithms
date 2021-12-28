package algs4

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.math.sqrt
import kotlin.random.Random

internal class AccumulatorTest {

    /**
     * Calculates mean
     *
     * @param list list of Double values
     * @return mean value of the [list]
     */
    private fun mean(list: List<Double>): Double {
        return list.sum() / list.size
    }

    /**
     * Calculates standard deviation using formula of sqrt(sum(x - mx)ˆ2 / n - 1)
     *
     * @param list list of Double values
     * @return standard deviation of the [list]
     */
    private fun std(list: List<Double>): Double {
        val m = mean(list)
        return sqrt(list.sumOf { (it - m) * (it - m) } / (list.size - 1))
    }

    /**
     * Testing [Accumulator] with different sized [list] on each loop with randomly generated Double values
     */
    @Test
    fun fuzzingAccumulator() {
        val epsilon = 0.0000001 // for numerical instability
        for (i in 0..100) {
            val l = List<Double>((10..9000).random()) { (Random.nextDouble() - 1) * 1000 }

            val acc = Accumulator()
            l.forEach { acc.addDataValue(it) }

            assertEquals(mean(l), acc.mu, epsilon)
            assertEquals(std(l), acc.stddev(), epsilon)
        }
    }

    /**
     * Testing if [Accumulator] with single value gives [Double.NaN] or not
     */
    @Test
    fun testSingleValue_NoVariance() {
        val l = 1.0
        val acc = Accumulator()
        acc.addDataValue(l)

        assertEquals(1.0, acc.mean())
        assertEquals(Double.NaN, acc.stddev())
    }
}