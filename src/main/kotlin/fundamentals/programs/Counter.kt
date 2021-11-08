package fundamentals.programs

import java.util.*

class Counter(val id: String) : Comparable<Counter> {

    private var count = 0

    /**
     * Increments the counter value by 1
     */
    fun increment(){
        count++
    }

    /**
     * Returns the current value of counter
     *
     * @return the current value of counter
     */
    fun tally() = count

    /**
     * Returns a string representation of this counter
     *
     * @return a string representation of this counter
     */
    override fun toString() = "$count $id"

    /**
     * Compares this counter the other counter
     *
     * @param other a counter object
     * @return 0 if both counter has same count value
     *         -1 if count value of this counter is less
     *         than value of other counter's count
     *         1 if count value of this counter is more
     *         than the value of other counter's count
     */
    override fun compareTo(other: Counter): Int {
        return when {
            this.count < other.count -> -1
            this.count > other.count ->  1
            else                     ->  0
        }
    }
}

/**
 * Reads two cl integers n and trials;
 * creates n counters;
 * increments randomly selected counters trials time
 */
fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val trials = scanner.nextInt()

    val hits = Array(n) { i -> Counter("counter $i") }

    for (trial in 0 until trials) {
        hits[(0 until  n).random()].increment()
    }

    for (c in hits) {
        println(c)
    }
}
