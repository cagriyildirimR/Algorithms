package fundamentals.dataAbstraction

/**
 * Difference between IntArray and Array<Int>: https://stackoverflow.com/a/45094889/14067017
 *
 * Java int vs Integer: https://stackoverflow.com/a/8660812/14067017
 * int is primitive, Integer is class (wrapper class)
 */

fun main() {
    var a: IntArray = IntArray(1_000_000) { 1_000_000 - it }
    println(a)

    var b: IntArray = IntArray(1_000_000) // default 0 initialization
    println(b)
    a = b.also { b = a } // changes references
    println(a)
    println(b)

}