package fundamentals.dataAbstraction

import kotlin.math.floor


val weekDays = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
fun SmartDate.dayOfTheWeek():String {

    // https://www.hackerearth.com/blog/developers/how-to-find-the-day-of-a-week/

    val t = listOf(0,3,2,5,0,3,5,1,4,6,2,4)
    val y = if (month < 3) year - 1 else year
    val result = (y + y / 4 - y / 100 + y / 400 + t[month-1] + day) % 7

    return weekDays[result]
}


fun main() {
    val day = SmartDate(29,7,2021)
    println(day.dayOfTheWeek())

    println(SmartDate(7,8,2021).dayOfTheWeek())
    println(SmartDate(7,8,1988).dayOfTheWeek())
}