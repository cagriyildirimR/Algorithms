package fundamentals.dataAbstraction

import java.io.File
import java.util.*


private fun rank2(key: Int, a: Array<Int>): Int {  // Array must be sorted.
    var lo = 0
    var hi = a.size - 1
    var counter = 0
    while (lo <= hi) {  // Key is in a[lo..hi] or not present.
        counter++
        val mid = lo + (hi - lo) / 2
        println("mid is $mid")
        println("mid has ${a[mid]}")
        when {
            key < a[mid] -> hi = mid - 1
            key > a[mid] -> lo = mid + 1
            else -> {
                println(counter)
                return mid
            }
        }
    }
    println("Counter is $counter")
    return -1
}

fun main(args: Array<String>) {
    val filePath = File("C:\\Users\\CAGRI\\IdeaProjects\\Algorithms\\src\\fundamentals.basicProgrammingModel.main\\resources\\largeT.txt")
    val scanner = Scanner(filePath)
    val fileSize = 1_000_000
    val whitelist: Array<Int> = Array (fileSize) { 0 }
    var i = 0
    while (scanner.hasNext()) {
        whitelist[i] = scanner.nextInt()
        i++
    }
    println("i is $i")

    whitelist.sort()
    println(whitelist.slice(0..10))
    println(whitelist[500001])

//        val readKey = Scanner(System.`in`)
    val key = 500449
    rank2(key,whitelist)

//    val key2 = 192618
//    rank2(key2,whitelist)
//
//    val key3 = 500451
//    rank2(key3,whitelist)
//
//    val key4 = 500452
//    rank2(key4,whitelist)
//
//    rank2(0, whitelist)

}
