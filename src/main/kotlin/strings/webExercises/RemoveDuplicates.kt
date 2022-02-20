package strings.webExercises

fun main() {
    val ss = "ABBCCCCCBBAB"
    val expected = "ABCBAB"

    val result = ss.filterIndexed { index, c -> if (index < ss.length - 1) c != ss[index + 1] else true}

    println(result == expected)
    println(result)
}