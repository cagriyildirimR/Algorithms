package LeetCodeHackerRank.dynamicProgramming

import org.junit.jupiter.api.Test


private typealias Input = Int
private typealias Want = Int

data class TestCase(val input: Input, val want: Want)
class FibonacciTest {
    val tests = listOf<TestCase>(
        TestCase(input = 1, want = 1),
        TestCase(input = 3, want = 2),
        TestCase(input = 5, want = 5),
        TestCase(input = 6, want = 8),
        )

    @Test
    fun fibonacciNaiveTest(){
        for (test in tests) {
            assert(Fibonacci.fibonacciNaive(test.input)==test.want)
        }
    }
}