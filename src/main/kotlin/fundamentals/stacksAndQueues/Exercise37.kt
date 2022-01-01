package fundamentals.stacksAndQueues

import edu.princeton.cs.algs4.Queue
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val m = scanner.nextInt()
    val n = scanner.nextInt()

    val queue = Queue<Int>()

//    for (i in 0 until n){
//        queue.enqueue(i)
//    }
//
//    while (!queue.isEmpty) {
//        for (j in 0 until m) {
//            queue.enqueue(queue.dequeue())
//        }
//        println(queue.dequeue())
//    }

    queue.enqueue(m)
    queue.enqueue(n)

}