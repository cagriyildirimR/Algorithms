
fun <T> T.print() = println(this)

fun <T> printArray(xss: Array<Array<T>>) {
    println("array begin")
    for (xs in xss) {
        print("[ ")
        for (x in xs) print("$x ")
        println("]")
    }
    print("array end")
}