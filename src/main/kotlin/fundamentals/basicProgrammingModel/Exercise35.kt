package fundamentals.basicProgrammingModel

import print
import kotlin.math.round

const val SIDES = 6

val dist = Array<Double>(2 * SIDES + 1 ) { 0.0 }

fun main() {
    for (i in 1..SIDES)
        for (j in 1..SIDES)
            dist[i+j] += 1.0

    for (k in 2..2* SIDES)
        dist[k] /= 36.0

    val n = 5_000_000
    nDiceRolls(n).map { it.first + it.second }.sorted().groupingBy { it }.eachCount().mapValues { it.value.toDouble() / n }.values.print()
    dist.slice(2..12).toList().map { round(it * 100000) / 100000 }.print()
}

fun nDiceRolls(n: Int): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    for (i in 0..n)
        result.add(Pair(roll(), roll()))
    return result
}

fun roll() = (1..6).random()