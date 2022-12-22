package LeetCodeHackerRank

import print

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

data class ListNode(var `val`: Int, var next: ListNode? = null)
class LinkedListSumTwo {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        var r1 = l1
        var r2 = l2

        var r = 0

        val l = ListNode(0)

        var p = l

        while (r1 != null || r2 != null) {
            val x = r1?.`val` ?: 0
            val y = r2?.`val` ?: 0

            var z = x + y + r
            r=0

            println("$x $y is $z")

            if (z >= 10) {
                z %= 10
                r = 1
            }

            p.`val` = z

            if (r1 != null) {
                r1 = r1.next
            }
            if (r2 != null) {
                r2 = r2.next
            }

            if (r1 != null || r2 != null) {
                p.next = ListNode(0)
                p = p.next!!
            }
        }

        if (r == 1) {
            p.next = ListNode(1)
        }

        return l
    }
}

fun main() {
    val r1 = ListNode(2)
    r1.next = ListNode(4)
    r1.next!!.next = ListNode(3)

    val r2 = ListNode(5)
    r2.next = ListNode(6)
    r2.next!!.next = ListNode(4)

    LinkedListSumTwo().addTwoNumbers(r1, r2).print()

}