package LeetCodeHackerRank.dynamicProgramming

import org.junit.jupiter.api.Test

class GridTravelTest {
    data class TestCase(val n: Long, val m: Long, val run: Boolean = true, val want: Long)
    val tests = listOf<TestCase>(
        TestCase(n = 1, m = 1, want = 1),
        TestCase(n = 2, m = 3, want = 3),
        TestCase(n = 3, m = 2, want = 3),
        TestCase(n = 18, m = 18, run = false, want = 2333606220), // won't run it for naive case
        )
    @Test
    fun naiveGridTravelTest(){
        for (test in tests) {
            if (test.run) assert(GridTravel.naiveGridTravel(test.n, test.m) == test.want)
        }
    }

    @Test
    fun memoGridTravelTest(){
        for (test in tests) {
            assert(GridTravel.memoGridTravel(test.n, test.m) == test.want)
        }
    }
}