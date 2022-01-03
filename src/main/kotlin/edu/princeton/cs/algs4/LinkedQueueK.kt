package edu.princeton.cs.algs4

import java.lang.Exception

sealed class LinkedListK
object Empty: LinkedListK() {
    override fun toString(): String {
        return "Empty"
    }
}
data class Node<Item>(val item: Item, var node: LinkedListK): LinkedListK()
// data LinkedListK = Empty | Node a LinkedListK

class LinkedQueueK<Item>(): Iterable<Item> {
    var last: LinkedListK = Empty
    var first: LinkedListK = Empty
    var size = 0

    fun enqueue(item: Item) {
        when(first) {
            is Empty -> {
                first = Node(item, Empty)
                last = first
            }
            is Node<*> -> {
                val newNode = Node(item, Empty)
                (last as Node<*>).node = newNode
                last = newNode
            }
        }
        size++
    }

    fun dequeue(): Item{
        if (first == Empty) throw Exception("Linked List is empty")
        val item = (first as Node<*>).item as Item
        first = (first as Node<*>).node
        --size
        return item
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    override fun iterator(): Iterator<Item> {
        return object : Iterator<Item>{
            var p: LinkedListK = first

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
