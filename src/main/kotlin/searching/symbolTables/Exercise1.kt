package searching.symbolTables

import print
import java.util.*

fun main() {
    val gpa = mapOf<String, Double>(
        "A+"  to 4.33,
        "A"   to 4.00,
        "A-"  to 3.67,
        "B+"  to 3.33,
        "B"   to 3.00,
        "B-"  to 2.67,
        "C+"  to 2.33,
        "C"   to 2.00,
        "C-"  to 1.67,
        "D"   to 1.00,
        "F"   to 0.00
    )

    print("Enter a grade: ")

    val scanner = Scanner(System.`in`)
    val grade = scanner.next()


    if (gpa.containsKey(grade)){
        println("Average of number corresponding to the grade $grade is ${gpa[grade]}")
    } else {
        println("Please enter a valid grade between A+ and F")
    }

}
