package LeetCodeHackerRank

object RomanToInteger {
    fun romanToInt(s: String): Int {
        var result = 0
        for (i in 0 until s.lastIndex) {
            val n = getRomanValue(s[i])
            val f = getRomanValue(s[i+1])
            if (n < f) {
                result -= n
            } else {
                result += n
            }
        }
        result += getRomanValue(s.last())
        return result
    }

    private fun getRomanValue(c: Char) = when(c) {
        'I' -> 1
        'V' -> 5
        'X' -> 10
        'L' -> 50
        'C' -> 100
        'D' -> 500
        'M' -> 1000
        else -> 0
    }
}
