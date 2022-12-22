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
        var current1 = l1
        var current2 = l2
        var carry = 0
        val dummyHead = ListNode(0)
        var currentResultNode = dummyHead
        while (current1 != null || current2 != null) {
            val x = current1?.`val` ?: 0
            val y = current2?.`val` ?: 0
            val sum = x + y + carry
            carry = if (sum >= 10) 1 else 0
            currentResultNode.`val` = sum % 10
            current1 = current1?.next
            current2 = current2?.next
            if (current1 != null || current2 != null) {
                currentResultNode.next = ListNode(0)
                currentResultNode = currentResultNode.next!!
            }
        }
        if (carry == 1) {
            currentResultNode.next = ListNode(1)
        }
        return dummyHead
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