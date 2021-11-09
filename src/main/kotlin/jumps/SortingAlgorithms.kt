package jumps

import print

fun <T : Comparable<T>> Array<T>.selectionSort(): Array<T> {
    for (i in 0 until lastIndex) {
        var min = i
        for (j in i + 1..lastIndex) {
            if (this[min] > this[j]) min = j // comp
        }
        if (min != i) this[i] = this[min].also { this[min] = this[i] } // exch
    }
    return this
}

fun <T : Comparable<T>> Array<T>.insertionSort(): Array<T> {
    for (i in 1 until lastIndex) {
        for (j in i - 1 downTo 0) {
            if (this[i] < this[j]) this[i] = this[j].also { this[j] = this[i] }
        }
    }
    return this
}

fun <T : Comparable<T>> Array<T>.bubbleSort(): Array<T> {
    var swap: Boolean
    do {
        swap = false
        for (i in 0 until lastIndex) {
            if (this[i] > this[i + 1]) {
                this[i] = this[i + 1].also { this[i + 1] = this[i] }
                swap = true
            }
        }

    } while (swap)

    return this
}
fun <T: Comparable<T>> Array<T>.shellSort(): Array<T> {
    val gapSequence = generateSequence(1) { it * 3 + 1 }
    val gaps = gapSequence.takeWhile { it <= this.size }.toList().reversed().toTypedArray()

    for (gap in gaps) {
        for (i in gap until this.size) {
            var j = i - gap

            while (j >= 0 && this[j+gap] < this[j] ) {
                this[j+gap] = this[j].also { this[j] = this[j+gap] }
                j -= gap
            }
        }
    }
    return this
}



fun main() {
    val bs = arrayOf(3, 5, 2, 7, 9, 0, 1,-20,5,30, -30,1,100, 2, 56, 200, -52, 3, 99, 33, 177)
    val cs = arrayOf('t', 'b', 'q', 'l', 'a', 'y', 'n', 'z')
    val ds = arrayOf(1,100, 2, 56, 200, -52, 3, 99, 33, 177)
    bs.selectionSort().toList().print()
    cs.selectionSort().toList().print()

    bs.insertionSort().toList().print()
    cs.insertionSort().toList().print()

    bs.bubbleSort().toList().print()
    cs.bubbleSort().toList().print()

    bs.shellSort().toList().print()
    cs.shellSort().toList().print()
    ds.shellSort().toList().print()

}