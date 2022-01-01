package fundamentals.analysisOfAlgorithms

import print

enum class Neighbour(val x: Int, val y: Int) {
    NORTH(-1, 0),
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1)
}

private fun check(arr: Array<Array<Int>>, neighbour: Neighbour, indices: Pair<Int, Int>): Boolean {

    val x = neighbour.x + indices.first
    val y = neighbour.y + indices.second

    return if (x !in arr.indices || y !in arr[0].indices) {
        true // handling index out of bounds error
    } else {
        arr[indices.first][indices.second] < arr[x][y]
    }
}

private fun localMinimum2D(arr: Array<Array<Int>>): Pair<Int, Int> {

    return localMinimum2D(arr, 0, arr.lastIndex)
}

private fun localMinimum2D(arr: Array<Array<Int>>, lo: Int, hi: Int): Pair<Int, Int> {

    val mid = (lo + hi) / 2

    var min = Pair(mid, 0)
    for (i in 1..arr[mid].lastIndex) { // N
        if (value(arr, min) > value(arr, Pair(mid, i))) min = Pair(mid, i) //c
    }

    var isLocalMinima = true
    for (d in Neighbour.values()) {
        isLocalMinima = check(arr, d, min) && isLocalMinima
    }

    return when {
        isLocalMinima -> min
        min.first == 0 -> localMinimum2D(arr, 1, hi)
        min.first == arr.lastIndex -> localMinimum2D(arr, lo, mid - 1)
        arr[min.first - 1][min.second] < arr[min.first + 1][min.second] -> localMinimum2D(arr, lo, mid - 1) // log n
        else -> localMinimum2D(arr, mid + 1, hi) // log n
    }
}

private fun value(arr: Array<Array<Int>>, indices: Pair<Int, Int>) = arr[indices.first][indices.second] //c

private fun localMinimum2DFast(arr: Array<Array<Int>>): Pair<Int, Int> {

    val midN = arr.size / 2
    val midM = arr[0].size / 2

    var min = Pair(midN, 0)
    for (i in 1..arr[midN].lastIndex) { // N
        if (value(arr, min) > value(arr, Pair(midN, i))) min = Pair(midN, i) //c
    }

    for (j in arr[0].indices) {
        if (value(arr, min) > value(arr, Pair(j, midM))) min = Pair(j, midM) //c
    }
    return localMinimum2DFast(arr, min)
}

private fun getMinimumNeighbour(arr: Array<Array<Int>>, idx: Pair<Int, Int>): Pair<Int, Int> {

    var min = idx
    for (d in Neighbour.values()) {
        val n = arr.getOrNull(idx.first +d.x)?.getOrNull(idx.second+d.y)
        if (n != null && arr[min.first][min.second] > n) {
                min = Pair(idx.first + d.x, idx.second + d.y)
        }
    }
    return min
}

private fun localMinimum2DFast(arr: Array<Array<Int>>, indices: Pair<Int, Int>): Pair<Int, Int> {

    var isLocalMinima = true
    for (d in Neighbour.values()) {
        isLocalMinima = check(arr, d, indices) && isLocalMinima
    }

    return when {
        isLocalMinima -> indices
        else -> localMinimum2DFast(arr, getMinimumNeighbour(arr, indices))
    }
}

fun main() {

    val arr = Array(6) { n -> Array(6) { m -> 10 * n + m } }
    println(arr.map { it.toList() }.toList())
    localMinimum2D(arr).print()

    arr[0] = arrayOf(1000, 1001, 1002, 1004, 1005)
    println(arr.map { it.toList() }.toList())
    localMinimum2D(arr).print()

    arr[2][2] = -10
    println(arr.map { it.toList() }.toList())
    localMinimum2D(arr).print()

    val arrF = Array(6) { n -> Array(6) { m -> 10 * n + m } }
    println(arrF.map { it.toList() }.toList())
    localMinimum2DFast(arrF).print()

    arrF[0] = arrayOf(1000, 1001, 1002, 1004, 1005)
    println(arrF.map { it.toList() }.toList())
    localMinimum2DFast(arrF).print()

    arrF[2][2] = -10
    println(arrF.map { it.toList() }.toList())
    localMinimum2DFast(arrF).print()
}