package fundamentals.dataAbstraction

class VisualCounter(val N: Int, val max: Int) {
    private var counter = 0
    private var operations = 0

    fun increment() {
        if (counter < max && operations < N) {
            counter++
            operations++
        }
        println(counter)
        Thread.sleep(1000L)
    }

    fun decrement() {
        if (counter < max && operations < N) {
            counter--
            operations++
        }
        println(counter)
        Thread.sleep(1000L)
    }
}

fun main() {
    val c = VisualCounter(6, 6)

    c.increment()
    clearScreen()
    c.increment()
    c.increment()
    c.decrement()
    c.decrement()
    c.increment()
}

fun clearScreen() {
    print("\u001b[H\u001b[2J")
    System.out.flush()
}