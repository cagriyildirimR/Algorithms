package algs4

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class DateTest{

    @Test
    fun dateToString(){
        assertEquals("1/10/1938", Date(1,10,1938).toString())
    }

    @Test
    fun invalidDates(){
        assertThrows(IllegalArgumentException::class.java) { Date(13,1,1) }
        assertThrows(IllegalArgumentException::class.java) { Date(1,32,1) }
        assertThrows(IllegalArgumentException::class.java) { Date(2,29,1999) }
    }

    @Test
    fun date_nextDate() {
        val date = Date(12, 30, 2021)
        assertEquals(Date(12, 31, 2021), date.next())
        assertEquals(Date(1, 1, 2022), date.next().next())
    }

    @Test
    fun sameDateUsingDifferentConstructorAndDelimiter() {
        assertEquals(Date(5, 19, 1919), Date("5-19-1919"))
        assertEquals(Date(5, 19, 1919), Date("5/19/1919"))
        assertEquals(Date(5, 19, 1919), Date("05.19.1919"))
    }
}
