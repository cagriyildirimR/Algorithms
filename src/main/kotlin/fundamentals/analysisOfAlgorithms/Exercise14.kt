package fundamentals.analysisOfAlgorithms

import kotlin.math.log
import kotlin.system.measureTimeMillis


private fun fourSum(arr:Array<Int>): Int {
    var counter = 0
    for (i in 0..arr.lastIndex-3) {
        for (j in i+1 .. arr.lastIndex-2){
            for (k in j+1 until arr.lastIndex) {
                for (t in k+1..arr.lastIndex) {
                    if (arr[i] + arr[j] + arr[k] + arr[t] == 0) counter++
                }
            }
        }
    }
    return counter
}

private fun createArrayOfRandomIntegers(size: Int):Array<Int> {
    return Array(size){(-1_000_000..1_000_000).random()}
}

fun main() {
    var size:Int = 128

    for (i in 1..4){
        val arr = createArrayOfRandomIntegers(size)
        val time = measureTimeMillis {
            fourSum(arr)
        }
        println("For size $size array three sum is completed in ${time / 1000.0} seconds. " +
                "(size: $size vs lgN: ${log(time.toFloat(), 10f)})")
        size *= 2
    }
    /**
     * For size 128 array three sum is completed in 0.04 seconds. (size: 128 vs lgN: 1.60206)
     * For size 256 array three sum is completed in 0.229 seconds. (size: 256 vs lgN: 2.3598354)
     * For size 512 array three sum is completed in 3.844 seconds. (size: 512 vs lgN: 3.5847833)
     * For size 1024 array three sum is completed in 60.87 seconds. (size: 1024 vs lgN: 4.7844033)
     */


}