package searching.symbolTables

class OrderedSequentialSearchST<Key: Comparable<Key>, Value> {

    inner class Node(val key: Key, var value: Value, var next: Node? = null)

    var head: Node? = null
    private var size = 0

    /**
     * Put operation for key and value. If head is null, new node is added to head
     *
     * @param key key value to add to the table
     * @param value value to add to the corresponding key
     */
    fun put(key: Key, value: Value) {
        when {
            isEmpty() -> { // initialize table
                head = Node(key, value)
                size++
                return
            }
            key < head!!.key -> { // key is smaller that first key in linked list
                head = Node(key, value, head)
                size++
                return
            }
            else -> { // iterate over linked list
                var tmp = head
                while (tmp != null) {
                    when {
                        key == tmp.key -> { // if key already in table
                            tmp.value = value
                            return
                        }
                        tmp.next == null -> { // if we are at last element of linked list
                            tmp.next = Node(key, value)
                            size++
                            return
                        }
                        tmp.key < key && key < tmp.next!!.key -> { // key is between the elements
                            tmp.next = Node(key, value, tmp.next)
                            size++
                            return
                        }
                    }
                    tmp = tmp.next
                }
            }
        }
    }

    /**
     * Returns the size of table
     * @return size of table
     */
    fun size() = size

    /**
     * Returns true if table is empty
     * @return true if table has no elements
     *         false if table has at least one element
     */
    fun isEmpty() = size == 0

    /**
     * Checks if key is in table
     * @param key the key to compare against all keys in table
     * @return true if key is in the table
     *         false if key isn't in the table
     */
    fun contains(key: Key): Boolean {
        return get(key) != null
    }


    /**
     * Get value of key
     * @param key the key
     * @return value that corresponds to the key
     *         null if key isn't in the table
     */
    fun get(key: Key): Value? {
        var tmp = head

        while (tmp != null) {
            if (tmp.key == key) {
                return tmp.value
            }
            tmp = tmp.next
        }
        return null
    }

    /**
     * If key exist, deletes from the table
     * @param key the key
     */
    fun delete(key: Key) {
        if (contains(key)) {
            if (head!!.key == key) {
                head = head!!.next.also { head!!.next = null }
                size--
                return
            } else {
                var tmp = head
                while (tmp!!.next != null) {
                    if (tmp.next!!.key == key) {
                        tmp.next = tmp.next!!.next.also { tmp!!.next!!.next = null }
                        size--
                        return
                    }
                    tmp = tmp.next
                }
            }
        }
    }

    override fun toString(): String {
        var r = " "
        if (isEmpty()){
            r = "Table is empty"
        }
        else{
            var tmp = head
            while (tmp != null) {
                r += "${tmp.key} : ${tmp.value} -> "
                tmp = tmp.next
            }

            r = "head ->" + r + "null"
        }
        return r
    }
}

fun main() {
    val st = OrderedSequentialSearchST<Char, Int>()
    var i = 0
    for (s in "loremipsumm") {
        st.put(s, i++)
    }

    println(st)
    println("size of the table is ${st.size()}")


    st.delete('e')
    st.delete('l')
    st.delete('l')
    st.delete('u')
    st.delete('r')
    println(st)

    st.delete('p')
    st.delete('s')
    st.delete('m')
    println(st.size())

    st.delete('i')
    st.delete('o')
    println(st)
    println("size of the table is ${st.size()}")

}
