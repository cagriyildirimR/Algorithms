package fundamentals.analysisOfAlgorithms

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.math.abs


enum class Status {
    HOT,
    COLD,
    DONE
}

private fun hotOrCold(N: Int): Boolean {
    val secret = (1 until N).random()
    println("Secret number is: $secret")

    val firstGuess = 1
    return hotOrCold(firstGuess, N,secret)
}

private fun hotOrCold(N: Int, secret: Int): Boolean {
    println("Secret number is: $secret")

    val firstGuess = 1
    return hotOrCold(firstGuess, N,secret)
}

private fun hotOrCold(lo: Int, hi: Int, secret: Int): Boolean {
    val newGuess = (lo + hi) / 2
    println("My new guess is $newGuess")

    return when(status(secret,newGuess, lo)) {
        Status.DONE -> {
            println("My final guess is $newGuess. Secret number was $secret")
            assertEquals(secret, newGuess)
            true
        }
        Status.COLD -> hotOrCold(lo, (lo+hi)/2, secret)
        Status.HOT  -> hotOrCold((newGuess + lo)/2, hi, secret)
    }
}

private fun status(secret: Int, newGuess: Int, oldGuess: Int): Status {
    return when {
        newGuess == secret                                 -> Status.DONE
        abs(secret-newGuess) > abs(secret-oldGuess)  -> Status.COLD
        else                                               -> Status.HOT
    }
}


fun main() {
    repeat(1000) {
        hotOrCold(100) // TODO("Reimplement the function. Currently it's faulty")
    }
//    hotOrCold(100, 88)
}

