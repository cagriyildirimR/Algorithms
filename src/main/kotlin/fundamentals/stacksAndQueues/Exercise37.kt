package fundamentals.stacksAndQueues

import edu.princeton.cs.algs4.LinkedQueueK
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val m = scanner.nextInt()
    val n = scanner.nextInt()

    val queue = LinkedQueueK<Int>()

    for (i in 0 until n){
        queue.enqueue(i)
    }

    while (!queue.isEmpty()) {
        for (j in 0 until m-1) {
            queue.enqueue(queue.dequeue())
        }
        println(queue.dequeue())
    }
}