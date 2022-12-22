package LeetCodeHackerRank

fun main() {
    searchInsert(intArrayOf(1,3, 5,6), 0)
}

private fun searchInsert(nums: IntArray, target: Int): Int {
    var lo = 0
    var hi = nums.lastIndex

    while (lo <= hi) {
        val mid = lo + ((hi-lo)/2)
        when {
            nums[mid] == target -> {
                println("target is reached")
                return mid
            }
            nums[mid] > target -> {
                println("hi is $hi")
                hi = mid-1
                println("lowering hi to $hi. lo: $lo")
            }
            else -> {
                println("adding one to lo")
                lo = mid+1
            }
        }
    }
    return hi + 1
}
