package fundamentals.stacksAndQueues

private class Range(val start: Int = 0, val end: Int) : Iterable<Int> {
    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int>{
            var tmp = 0
            override fun hasNext(): Boolean {
                return tmp in start..end
            }

            override fun next(): Int {
                return tmp++
            }
        }
    }
}

fun main() {

    // while hasNext(); v = next()
    for (v in Range(end = 10))
        println(v)
}