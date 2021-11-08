package algs4

import java.util.*

/**
 * A library for reading in 1D and 2D arrays of integers, doubles, and booleans from standard input and printing
 * them to standard output
 */
object StdArrayIO {

    val scanner = Scanner(System.`in`)

    /**
     * Reads 1D array of doubles from standard input and returns it
     *
     * @return 1D array of Doubles
     */
    fun readDouble1D(): DoubleArray {
        val n: Int = scanner.nextInt()
        return DoubleArray(n) { scanner.nextDouble() }
    }

    /**
     * Print an array of doubles to standard output
     *
     * @param arr the 1D array of doubles
     */
    fun print(arr: DoubleArray) {
        print("[")
        for (i in arr.indices) {
            if (i == arr.size - 1) print("${arr[i]}") else print("${arr[i]}, ")
        }
        print("]")
    }

    /**
     * Reads a 2D array of doubles from standard input and returns it
     *
     * @return the 2D array of doubles
     */
    fun readDouble2D(): Array<DoubleArray> {
        val m = scanner.nextInt()
        val n = scanner.nextInt()
        return Array(m) { DoubleArray(n) { scanner.nextDouble()} }
    }

    /**
     * Print the 2D array of doubles to standard output
     *
     * @param arr the 2D array of doubles
     */
    fun print(arr: Array<DoubleArray>) {
        print("[")
        for (i in arr.indices) {
            when (i) {
                arr.lastIndex -> {
                    print(" ")
                    this.print(arr[i])
                    print("],")
                }
                0 -> {
                    this.print(arr[i])
                    println()
                }
                else -> {
                    print(" ")
                    this.print(arr[i])
                    println()
                }
            }
        }
    }

    /**
     * Reads 1D array of integers from standard input and returns it
     *
     * @return the 1D array of integers
     */
    fun readInt1D(): IntArray {
        val n = scanner.nextInt()
        return IntArray(n) { scanner.nextInt()}
    }

    /**
     * Prints an array of integers to standard output
     *
     * @param arr the 1D array of integers
     */
    fun print(arr: IntArray) {
        print("[")
        for (i in arr.indices) {
            if (i == arr.size - 1) print("${arr[i]}") else print("${arr[i]}, ")
        }
        print("]")
    }

    /**
     * Reads 2D array of integers from standard input and returns it
     *
     * @return the 2D array of integers
     */
    fun readInt2D(): Array<IntArray> {
        val m = scanner.nextInt()
        val n = scanner.nextInt()
        return Array(m) { IntArray(n) { scanner.nextInt()} }
    }

    /**
     * Prints a 2D array of integers to standard output
     *
     * @param arr the 2D array of integers
     */
    fun print(arr: Array<IntArray>) {
        print("[")
        for (i in arr.indices) {
            when (i) {
                arr.lastIndex -> {
                    print(" ")
                    this.print(arr[i])
                    print("],")
                }
                0 -> {
                    this.print(arr[i])
                    println()
                }
                else -> {
                    print(" ")
                    this.print(arr[i])
                    println()
                }
            }
        }
    }

    /**
     * Reads 1D array of boolean from standard input and returns it
     *
     * @return the 1D array of booleans
     */
    fun readBoolean1D(): BooleanArray {
        val n = scanner.nextInt()
        return BooleanArray(n) { scanner.nextBoolean() }
    }

    /**
     * Prints an array of booleans to standard output
     *
     * @param arr the 1D array of booleans
     */
    fun print(arr: BooleanArray) {
        print("[")
        for (i in arr.indices) {
            if (i == arr.size - 1) print("${arr[i]}") else print("${arr[i]}, ")
        }
        print("]")
    }

    /**
     * Reads 2D array of booleans from standard input and returns it
     *
     * @return the 2D array of booleans
     */
    fun readBoolean2D(): Array<BooleanArray> {
        val m = scanner.nextInt()
        val n = scanner.nextInt()
        return Array(m) { BooleanArray(n) { scanner.nextBoolean() } }
    }

    /**
     * Prints a 2D array of booleans to standard output
     *
     * @param arr the 2D array of booleans
     */
    fun print(arr: Array<BooleanArray>) {
        print("[")
        for (i in arr.indices) {
            when (i) {
                arr.lastIndex -> {
                    print(" ")
                    this.print(arr[i])
                    print("],")
                }
                0 -> {
                    this.print(arr[i])
                    println()
                }
                else -> {
                    print(" ")
                    this.print(arr[i])
                    println()
                }
            }
        }
    }
}
