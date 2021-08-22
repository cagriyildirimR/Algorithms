package fundamentals.basicProgrammingModel

import java.util.*

fun tabulate() {
    val scanner = Scanner(System.`in`)
    val l = mutableListOf<Table>()
    while (scanner.hasNext()) {
        val name = scanner.next().toString()
        if (name == "exit") break
        val nominator = scanner.nextInt()
        val denominator = scanner.nextInt()
        l.add(Table(name, nominator, denominator, (nominator.toDouble() / denominator).format(3).toDouble()))
    }
    l.forEach(::println)
    scanner.close()
}


data class Table(val name: String, val nominator: Int, val denominator: Int, val value: Double) {
    override fun toString(): String {
        return "name : $name | nom: $nominator | denom: $denominator | value: $value"
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
