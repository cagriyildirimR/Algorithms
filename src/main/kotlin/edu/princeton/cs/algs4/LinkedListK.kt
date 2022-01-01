package edu.princeton.cs.algs4

sealed class LinkedListK
object Empty: LinkedListK() {
    override fun toString(): String {
        return "Empty"
    }
}
data class Node<Item>(val item: Item, val node: LinkedListK): LinkedListK()
// data LinkedListK = Empty | Node a LinkedListK

class LinkedListWithSealedClass<Item>(): Iterable<Item> {
    var head: LinkedListK = Empty
    var size = 0

    fun add(item: Item) {
        head = when(head) {
            is Empty -> {
                Node(item, Empty)
            }
            is Node<*> -> {
                val newNode = Node(item, head)
                newNode
            }
        }
        size++
    }

    override fun iterator(): Iterator<Item> {
        return object : Iterator<Item>{
            var p: LinkedListK = head

            override fun hasNext(): Boolean {
                return p != Empty
            }

            override fun next(): Item {
                val v = (p as Node<Item>).item
                p = (p as Node<Item>).node
                return (v as Item)
            }

        }
    }

    override fun toString(): String {
        var result = ""
        for (i in this){
            result += "$i -> "
        }
        return "head -> " + result + "Empty"
    }
}

fun main() {
    val ll = LinkedListWithSealedClass<Int>()
    for (i in 1..10) {
        ll.add(i)
    }

    println(ll)
    println(ll.head)
}