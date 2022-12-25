package LeetCodeHackerRank

import print

class LongestCommonPrefix {
    fun longestCommonPrefix(strs: Array<String>): String {
        val c = mutableListOf<Char>()
        val ref = strs.first()

        inner@for (i in ref.indices) {
            val isRepeating = strs.fold(true) { acc: Boolean, s: String ->
                if (s.lastIndex >= i) acc && (s[i] == ref[i]) else false
            }
            if (!isRepeating) break@inner
            c.add(ref[i])
        }
        return c.joinToString("")
    }
}

fun main() {
    LongestCommonPrefix().longestCommonPrefix(arrayOf("ab", "a")).print()
}

object RomanNumerals {
    fun romanToInt(s: String): Int {
        var result = 0
        for (i in 0 until s.lastIndex) {
            val n = getRomanValue(s[i])
            val f = getRomanValue(s[i + 1])

            println("current value is: ${s[i]} and it's int value: $n. next value is ${s[i + 1]} and it's value $f")
            if (n < f) {
                println("n is smaller than f so substracting")
                result -= n
            } else {
                result += n
            }

        }
        result += getRomanValue(s.last())
        return result
    }

    private fun getRomanValue(c: Char) = when (c) {
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

fun minimumBribes(q: Array<Int>): Unit {
    // Write your code here
    println(q.toList())
    q.forEachIndexed { index, i -> q[index]-- }
    println(q.toList())

    var b = 0
    for (i in q.lastIndex downTo 1) {

        if (i > 1 && i == q[i - 2]) {
            q[i - 1] = q[i - 2].also { q[i - 2] = q[i - 1] }
            b++
        }
        if (i == q[i - 1]) {
            q[i] = q[i - 1].also { q[i - 1] = q[i] }
            b++
        }
        if (i != q[i]) {
            println("Too chaotic")
            return
        }
        println(q.toList())
    }
    println(b)
}

fun removeElement(nums: IntArray, v: Int): Int {
    "sasdf".length
    var s = 0
    var e = nums.lastIndex

    while (s < e) {
        if (nums[s] == v) {
            while (nums[e] == v) {
                e--
            }
            nums[s] = nums[e].also { nums[e] = nums[s] }
        }
        s++
    }
    return s
}

fun superDigit(n: String, k: Int): Int {
    val m = mapOf<Char, Int>(
        '0' to 0,
        '1' to 1,
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9
    )
    // Write your code here
    if (n.length == 1) return n.toInt()
    val result = Array<Long>(n.length) { m[n[it]]!!.toLong() }
    return superDigit("${result.reduce { a, v -> a + v } * k}", 1)
}
