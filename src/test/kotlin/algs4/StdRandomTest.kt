package algs4

import org.junit.Before
import org.junit.Test
import algs4.StdRandom as std

const val MILLION = 1_000_000

class StdRandomTest {

    @Test
    fun uniformTest() {
        val list = List(MILLION) { std.uniform() }
        list.forEach { assert(it >= 0.0 || it < 1.0) }
    }

    @Test
    fun uniformIntegerTestBetweenZeroAndN() {
        val n = 42
        val list = List(MILLION) { std.uniform(n) }
        list.forEach { assert(it in 0 until n) }
    }

    @Test
    fun uniformLongTestBetweenZeroAndN() {
        val n = 42_000_000_000
        val list = List(MILLION) { std.uniform(n) }
        list.forEach { assert(it in 0 until n) }
    }
}