package fundamentals.dataAbstraction

import print
import java.text.SimpleDateFormat

private class Date(val day: Int, val month: Int, val year: Int): Comparable<Date> {
    override fun compareTo(other: Date): Int {
        val dayThis = SimpleDateFormat("dd-MM-yyyy").parse("$day-$month-$year")
        val dayOther = SimpleDateFormat("dd-MM-yyyy").parse("${other.day}-${other.month}-${other.year}")

        return when {
            dayThis == dayOther -> 0
            dayThis > dayOther  -> 1
            else                -> -1
        }
    }

    override fun toString(): String {
        return "$day/$month/$year"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (this.javaClass != other.javaClass) return false

        val that = other as Date
        if (day != that.day) return false
        if (month != that.month) return false
        return year == that.year
    }

    override fun hashCode(): Int { // auto-generated
        var result = day
        result = 31 * result + month
        result = 31 * result + year
        return result
    }
}

private class Transaction(val who: String, val date: Date, val amount: Double) {
    override fun toString(): String {
        return "$who spent $amount$ on $date"
    }

    override fun equals(other: Any?): Boolean {

        if (other == null)  return false
        if (this === other) return true
        if (this.javaClass != other.javaClass) return false

        val that = other as Transaction

        if (who != that.who) return false
        if (date != that.date) return false
        if (amount != that.amount) return false

        return true

    }

    override fun hashCode(): Int {
        var result = who.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + amount.hashCode()
        return result
    }
}


fun main() {
    Date(1, 1, 2000).compareTo(Date(2, 2, 2000)).print()
    Date(1, 1, 2000).compareTo(Date(2, 2, 1999)).print()
    Date(1, 1, 2000).compareTo(Date(1, 1, 2000)).print()

    val date = Date(1,8,2021)

    println(date == Date(5,4,7777))
    println(date == Date(1,8,2021))

    val t = Transaction("Cagri", date, 9000.0)
    println(t)

    val t1 = Transaction("Alice", date, 1000.0)
    val t2 = Transaction("Cagri", date, 9000.0)

    println(t1 == t)
    println(t2 == t)

}