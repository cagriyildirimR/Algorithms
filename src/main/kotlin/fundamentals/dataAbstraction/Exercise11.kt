package fundamentals.dataAbstraction

import print
import java.lang.Exception

val months30days = listOf(4,6,9,11)
val months31days = listOf(1,3,5,7,8,10,12)

enum class Months {
    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
}

class SmartDate(val day: Int, val month: Int, val year: Int) {
    init {
        if (month !in 1..12) throw Exception("Month value should be between 1 and 12")
        if (month in months30days) {
            if (day !in 1..30) throw Exception("Day value should be between 1 and 30")
        } else if (month in months31days) {
            if (day !in 1..31) throw Exception("Day value should be between 1 and 31")
        } else {
            if (leapYear(year)) {
                if (day !in 1..29) throw Exception("$year is a leap year. Day value should be between 1 and 29")
            } else {
                if (day !in 1..28) throw Exception("$year is not a leap year. Day value should be between 1 and 28")
            }
        }
    }
    private fun leapYear(year: Int): Boolean {
        return if (year % 4 == 0) {
            if (year % 100 == 0) {
                year % 400 == 0
            } else {
                true
            }
        } else {
            false
        }
    }

    override fun toString(): String {
        return "$day-$month-$year"
    }
}

fun main() {
    SmartDate(29,2,2000).print()
}


