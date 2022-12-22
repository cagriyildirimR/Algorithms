package strings.substring

import print

private fun bruteSubstring(txt: String, pattern: String): Int {
    var k = 0
    val m = pattern.length
    for (i in txt.indices) {
        inner@for (j in pattern.indices) {
            if (txt[i+j] != pattern[j]) {
                k = 0
                break@inner
            } else k++
        }
        if (k == m) return i
    }
    return -1
}

fun main() {
    val txt = "AAACBAAAAAAAAAAAAAABB"
    val pattern = "AAAB"
    bruteSubstring(txt,  pattern).print()
}
