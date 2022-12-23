package LeetCodeHackerRank

import print

class PalindromeIndex {
    fun palindromeIndex(s: String): Int {
        // Write your code here
        var start = 0
        var end = s.lastIndex

        while (start < end) {
            when {
                s[start] == s[end] -> {
                    start++
                    end--
                }

                s[start + 1] == s[end] && s[start] == s[end - 1] -> {
                    return if (isPalindrome(s, start + 1, end)) start else if (isPalindrome(s, start, end - 1)) end else -1
                }

                s[start + 1] == s[end] || s[start] != s[end - 1] -> {
                    return if (isPalindrome(s, start+1, end)) start else -1
                }

                s[start + 1] != s[end] || s[start] == s[end - 1] -> {
                    return if (isPalindrome(s, start, end - 1)) end else -1
                }

                else -> {
                    return -1
                }
            }
        }
        return -1
    }

    private fun isPalindrome(s: String, start: Int, end: Int): Boolean {
        var x = start
        var y = end
        while (x < y) {
            if (s[x] != s[y]) return false else {
                x++; y--
            }
        }
        return true
    }
}

fun main() {
    PalindromeIndex().palindromeIndex("bcbc").print()
}