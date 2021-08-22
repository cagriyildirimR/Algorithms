package fundamentals.basicProgrammingModel

/**
 * https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter1/section1/Exercise27_BinomialDistribution.java
 */
var count = 0

fun binomial(N: Int, k: Int, p: Double): Double {
    println(count)
    count++
    if ((N == 0) && (k == 0)) return 1.0
    if ((N == 0) || (k < 0)) return 0.0
    return (1.0 - p) * binomial(N-1, k, p) + p * binomial(N-1,k-1, p)
}


fun binomial2Start(N: Int, k: Int, p: Double) {
    val arr = Array(N + 1) { DoubleArray(k + 1) }
    for (i in arr.indices) {
        for (j in 0 until arr[0].size) {
            arr[i][j] = (-1).toDouble()
        }
    }
    print(binomial2(arr, N, k, p))
}

fun binomial2(arr: Array<DoubleArray>, N: Int, k: Int, p: Double): Double {
    count++
    println(count)
    if (N == 0 && k == 0) return 1.0
    if (N < 0 || k < 0) return 0.0
    if (arr[N][k] == -1.0) {
        arr[N][k] = (1 - p) * binomial2(arr, N - 1, k, p) + p * binomial2(arr, N - 1, k - 1, p)
    }
    return arr[N][k]
}


fun main() {
    binomial2Start(100, 50, 0.25)
    println(count)
}