package strings.webExercises


/**
 * Removes adjacent spaces if there are more than one
 *
 * e.g: "this   is   a  test"
 *      "this is a test"
 */
fun main() {
    val ss = "  Lorem    ipsum  dolor sit     amet    ,  consectetur   adipiscing    elit. " +
            "Morbi       vel         egestas       tellus, vel      eleifend      enim.     Pellentesque " +
            "non          aliquet       magna. Sed       malesuada      nibh      massa, vel.\n"

    val ns =
        ss.filterIndexed { index, c -> if (index < ss.length - 1) c != ' ' || ss[index + 1] !in " ,.!" else c != ' ' }
    println(ns)

    val sb = StringBuilder(ss.length)
    ss.forEachIndexed{index, c ->  if (index < ss.length-1 && c == ' ' && ss[index + 1] in " ,.!") else sb.append(c) }
    println(sb)
}