package fundamentals.basicProgrammingModel

import kotlin.math.ln

fun lnRecursive(N: Double): Double {
    if (N == 1.0) return 0.0
    return lnRecursive(N-1.0) + ln(N)
}