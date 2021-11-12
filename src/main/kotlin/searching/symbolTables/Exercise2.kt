package searching.symbolTables

class ArrayST<Key, Value> {

    /**
     * Kotlin's arrays are mapped to JVM arrays. JVM arrays' type must be known at compile time.
     * more information at [this](https://stackoverflow.com/questions/41941102/instantiating-generic-array-in-kotlin/41946516#41946516) stackoverflow post
     */
    private var keys = Array<Any?>(2) { null }
    private var values = Array<Any?>(2) { null }

    private var waldo = 0 // where is waldo?

    /**
     * Puts [key] into [keys] and [value] into [values]. Both must share index, but they are stored in different lists
     *
     * @param key data represents key
     * @param value data represents value
     */
    fun put(key: Key, value: Value) {
        keys[waldo] = key
        values[waldo] = value
        if (keys.lastIndex == waldo) resize(Resize.UPSIZE)
        waldo++
    }

    /**
     * Resizes arrays [keys] and [values] upward or downward
     * @param s either [Resize.UPSIZE] or [Resize.DOWNSIZE]
     */
    private fun resize(s: Resize) {
        val nKeys:Array<Any?>
        val nValues:Array<Any?>

        when (s) {
            Resize.UPSIZE -> {
                nKeys = Array<Any?>(keys.size * 2) { null }
                nValues = Array<Any?>(nKeys.size) { null }

                keys.forEachIndexed { index, value -> nKeys[index] = value }
                values.forEachIndexed { index, value -> nValues[index] = value }
            }
            Resize.DOWNSIZE -> {
                nKeys = Array<Any?>(keys.size / 2) { null }
                nValues = Array<Any?>(nKeys.size) { null }

                nKeys.forEachIndexed { index, _ -> nKeys[index] = keys[index] }
                nValues.forEachIndexed { index, value -> nValues[index] = values[index] }
            }
        }

        keys = nKeys
        values = nValues

    }

    /**
     * Resizing enum for resize function with two possibilities; upsizing and downsizing
     */
    enum class Resize {
        UPSIZE,
        DOWNSIZE
    }

    /**
     * Get operation for key-value pairs. If key exists then it returns corresponding value
     * @return value if value exists
     */
    fun get(key: Key): Value? {
        val index = indexOf(key)

        return if (index == -1) null else values[index] as Value
    }

    /**
     * Returns index of given [key]
     * @param key key value
     * @return index value of [key] if key isn't in symbol table then returns -1
     */
    private fun indexOf(key: Key): Int {
        var index = -1
        for (i in keys.indices) {
            if (keys[i] == key) index = i
        }
        return index
    }

    /**
     * Delete operation for given key-value. If key doesn't exist then does nothing
     * @param key key value
     */
    fun delete(key: Key) {
        var index = indexOf(key)

        if (index != -1) {
            while (index + 1 <= keys.lastIndex && keys[index + 1] != null) {
                keys[index] = keys[index + 1]
                values[index] = values[index + 1]
                index++
            }

            waldo--
            keys[waldo] = null
            values[waldo] = null

            if (keys.size / 4 == waldo && keys.size > 4) resize(Resize.DOWNSIZE)
        }
    }

    /**
     * Checks if there is a value paired with key
     *
     * @param key key value
     * @return true if key is in symbol table
     *         false otherwise
     */
    fun contain(key: Key): Boolean {
        return indexOf(key) != -1
    }

    /**
     * Checks if symbol table is empty
     * @return true if symbol table is empty
     *         false otherwise
     */
    fun isEmpty(): Boolean {
        return keys[0] == null
    }

    /**
     * Size of symbol table
     * @return size of symbol table
     */
    fun size(): Int {
        return waldo
    }

    /**
     * All the keys in table
     * @return all the keys in the table
     */
    fun keys(): Iterable<Key> {
        val iterable = mutableListOf<Key>()
        for (k in keys)
            if (k != null) iterable.add(k as Key)

        return iterable
    }

    override fun toString(): String {
        var result = ""

        for (i in keys.indices) {
            if (keys[i] != null) result += "${keys[i]} : ${values[i]} \n"
        }
        return result
    }
}

fun main() {

    val st = ArrayST<String, Int>()

    println("Is table empty? ${st.isEmpty()}")
    println()

    st.put("One", 1)
    st.put("Two" , 2)
    st.put("Three", 3)
    st.put("Four", 4)

    println(st)
    println("Size of table is ${st.size()}")
    println("These are the keys ${st.keys()}")
    println("Is table empty? ${st.isEmpty()}")
    println("Does table contain Two ${st.contain("Two")}")

    println()
    st.delete("Two")
    println(st)
    println("Size of table is ${st.size()}")
    println("These are the keys ${st.keys()}")
    println("Is table empty? ${st.isEmpty()}")
    println("Does table contain Two ${st.contain("Two")}")

    println()
    st.delete("One")
    st.delete("Three")
    st.delete("Four")
    st.delete("Five")
    println("Size of table is ${st.size()}")
    println("These are the keys ${st.keys()}")
    println("Is table empty? ${st.isEmpty()}")
    println("Does table contain Two ${st.contain("Two")}")

}