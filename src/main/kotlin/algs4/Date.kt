package algs4

class Date : Comparable<Date> {

    var month: Int
    var day: Int
    var year: Int
    private val DAYS = arrayOf(0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    /**
     * Initialize date by given string with a form of mm/dd/yyyy.
     * @param date a string of date
     * @throws IllegalArgumentException if date is invalid and date string is invalid
     */
    constructor(date: String) {
        val fields = date.split("[/.-]+".toRegex())
        if (fields.size != 3) throw IllegalArgumentException("Invalid date. Date must be of form mm/dd/yyyy")
        month = fields[0].toInt()
        day = fields[1].toInt()
        year = fields[2].toInt()
        if (!isValid(month, day, year)) throw IllegalArgumentException("Invalid date")
    }

    /**
     * Initialize date by given [month], [day] and [year] values
     * @param month the month
     * @param day the day
     * @param year the year
     * @throws IllegalArgumentException if date is invalid
     */
    constructor(month: Int, day: Int, year: Int) {
        if (!isValid(month, day, year)) throw IllegalArgumentException("Invalid date")
        this.month = month
        this.day = day
        this.year = year
    }

    /**
     * Checks if given date is valid.
     * @param day the day
     * @param month the month
     * @param year the year
     * @return false if date is invalid e.g. [day] is bigger than 31 or [month] is bigger than 12
     *         true otherwise
     */
    fun isValid(month: Int = this.month, day: Int = this.day, year: Int = this.year): Boolean {
        return when {
            month < 1 || month > 12                      -> false
            day < 1 || day > DAYS[month]                 -> false
            month == 2 && day == 29 == !isLeapYear(year) -> false
            else                                         -> true
        }
    }

    /**
     * Checks if given year is a leap year
     * @param year the year
     * @return true if year is a leap year
     *         false otherwise
     */
    private fun isLeapYear(year: Int): Boolean {
        return when {
            year % 400 == 0 -> true
            year % 100 == 0 -> false
            else            -> year % 4 == 0
        }
    }

    /**
     * Returns the next date in calendar.
     *
     * @return a date that represents the next day after this day
     */
    fun next(): Date {
        return when {
            isValid(month, day + 1, year)      -> Date(month, day + 1, year)
            isValid(month + 1, 1, year) -> Date(month + 1, 1, year)
            else                                    -> Date(1, 1, year + 1)
        }
    }

    /**
     * Compares tho dates chronologically
     *
     * @param other the other date
     * @return true if [this] date is after [other] date
     *         false otherwise
     */
    fun after(other: Date): Boolean {
        return compareTo(other) > 0
    }

    /**
     * Compares tho dates chronologically
     *
     * @param other the other date
     * @return true if [this] date is before [other] date
     *         false otherwise
     */
    fun before(other: Date): Boolean {
        return compareTo(other) < 0
    }

    /**
     * Compares two dates chronologically
     *
     * @return -1 if [this] date comes before [other] date
     *          1 if [this] date comes after [other] date
     *          0 if two dates are equal
     */
    override fun compareTo(other: Date): Int {
        return when{
            this.year < other.year -> -1
            this.year > other.year ->  1
            this.month < other.month -> -1
            this.month > other.month -> 1
            this.day < other.day -> -1
            this.day > other.day -> 1
            else -> 0
        }
    }

    /**
     * Returns a string representation of this date.
     *
     * @return the string representation of this date
     */
    override fun toString(): String {
        return "$month/$day/$year"
    }

    /**
     * Compares this date to specified date.
     *
     * @param other the other date
     * @return true of dates are equal; false otherwise
     */
    override fun equals(other: Any?): Boolean {
        return when {
            //other == this -> true //https://stackoverflow.com/a/56555536/14067017
            other == null -> false
            other.javaClass != this.javaClass -> false
            else -> {
                val that = other as Date
                (this.month == that.month) && (this.day == that.day) && (this.year == that.year)
            }
        }
    }

    /**
     * Returns an integer hash code for this date
     *
     * @return an integer hash code for this date
     */
    override fun hashCode(): Int {
        return day + 31 * month + 31 * 12 * year
    }
}

fun main() {
    val date = Date(1,1,2021)
    println(date)
    println(date.next())
    println(date == date.next())
}