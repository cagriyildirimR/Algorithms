package fundamentals.analysisOfAlgorithms

import print
import kotlin.math.log
import kotlin.system.measureTimeMillis


private fun threeSum(arr:Array<Int>): Int {
    var counter = 0
    for (i in 0..arr.lastIndex-2) {
        for (j in i+1 until arr.lastIndex){
            for (k in j+1..arr.lastIndex) {
                if (arr[i] + arr[j] + arr[k] == 0) counter++
            }
        }
    }
    return counter
}

private fun createArrayOfRandomIntegers(size: Int):Array<Int> {
    return Array(size){(-1_000_000..1_000_000).random()}
}

fun main() {
    var size:Int = 1024

    for (i in 1..4){
        val arr = createArrayOfRandomIntegers(size)
        val time = measureTimeMillis {
            threeSum(arr)
        }
        println("For size $size array three sum is completed in ${time / 1000.0} seconds. " +
                "(size: $size vs lgN: ${log(time.toFloat(), 10f)})")
        size *= 2
    }

}