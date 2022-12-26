package LeetCodeHackerRank.dynamicProgramming

import org.junit.jupiter.api.Test


private typealias Input = Int
private typealias Want = Long

class FibonacciTest {
    data class TestCase(val input: Input, val run: Boolean = true, val want: Want)

    val tests = listOf<TestCase>(
        TestCase(input = 1, want = 1L),
        TestCase(input = 3, want = 2L),
        TestCase(input = 5, want = 5L),
        TestCase(input = 6, want = 8L),
        TestCase(input = 50, run = false, want = 12_586_269_025L),
    )

    @Test
    fun fibonacciNaiveTest() {
        for (test in tests) {
            if (test.run) assert(Fibonacci.naiveFibonacci(test.input) == test.want)
        }
    }

    @Test
    fun memoFibonacciTest() {
        for (test in tests) {
            assert(Fibonacci.memoFibonacci(test.input) == test.want)
        }
    }
}