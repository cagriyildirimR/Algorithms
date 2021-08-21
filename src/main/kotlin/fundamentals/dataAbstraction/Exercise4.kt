package fundamentals.dataAbstraction

fun main() {
    var string1 = "hello"
    val string2 = string1
    string1 = "world" // is it same as val string1 = "world"
    // Java string objects are immutable, once created cannot be changed

    println(string1)
    // world

    println(string2)
    // hello

}
