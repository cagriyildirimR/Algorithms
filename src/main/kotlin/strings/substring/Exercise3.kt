package strings.substring


fun buildDfa(pattern: String, radix: Int = 128): Array<IntArray> {
    val m = pattern.length
    val dfa = Array(radix){ IntArray(m) }
    dfa[pattern[0].at()][0] = 1
    var x = 0
    for (currentState in 1..pattern.lastIndex) {
        for (r in 0 until radix){
            dfa[r][currentState] = dfa[r][x]
        }
        dfa[pattern[currentState].at()][currentState] = currentState+1
        x = dfa[pattern[currentState].at()][x]
    }
    return dfa
}

private fun Char.at(): Int {
    return this.code
}

fun main() {
    println(buildDfa("ABABAC").map { it.toList() }.toList())
//    println(buildDfa("AAAAAAAB", radix = 2).map { it.toList() }.toList())
//    println(buildDfa("AACAAAB", radix = 3).map { it.toList() }.toList())
//    println(buildDfa("ABABABAB", radix = 2).map { it.toList() }.toList())
//    println(buildDfa("ABAABAAABAAAB", radix = 2).map { it.toList() }.toList())
//    println(buildDfa("ABAABCABAABCB", radix = 3).map { it.toList() }.toList())
}
