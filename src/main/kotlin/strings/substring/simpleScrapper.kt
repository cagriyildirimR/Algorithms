package strings.substring

private fun search(txt: String, pattern: String, dfa:Array<IntArray>): Int {
    val m = pattern.length
    var state = 0
    for (t in txt.indices){
        if (state == m) return t - m
        state = dfa[txt[t].code][state]
    }
    return txt.length // search miss
}

fun main() {
    val pattern = "hello"
    val dfa = buildDfa(pattern)
    val txt = "abdfsdf hellokdowehhd"
    println(search(txt,pattern,dfa))
}