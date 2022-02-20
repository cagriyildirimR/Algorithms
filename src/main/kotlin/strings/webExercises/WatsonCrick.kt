package strings.webExercises

private typealias DNA = String

private fun check(f: Char, s: Char): Boolean {
    return when {
        f == 'A' && s == 'T' -> true
        f == 'T' && s == 'A' -> true
        f == 'C' && s == 'G' -> true
        f == 'G' && s == 'C' -> true
        else -> false
    }
}

private fun watsonCrick(dna: DNA): Boolean {
    val lo = 0
    val hi = dna.lastIndex
    return watsonCrick(dna, lo, hi)
}

private fun watsonCrick(dna: DNA, lo: Int, hi: Int): Boolean {
    return when {
        lo == hi -> true // odd number
        lo > hi -> true // there is nothing to check
        check(dna[lo], dna[hi]) -> watsonCrick(dna, lo + 1, hi - 1) // if true go to next comparison
        else -> false // doesn't Watson-Crick complemented palindrome
    }
}

fun main() {
    println(watsonCrick("AGCGCT"))

    // Watson Crick complement
    val dna = "ACGGAT"
    val comp = dna.reversed().map {
        when(it) {
            'A' -> 'T'
            'T' -> 'A'
            'C' -> 'G'
            'G' -> 'C'
            else -> ' '
        }
    }.joinToString("")
    println(comp) // should print ATCCGT
}
