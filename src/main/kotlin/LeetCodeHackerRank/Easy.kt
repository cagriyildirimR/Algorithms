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