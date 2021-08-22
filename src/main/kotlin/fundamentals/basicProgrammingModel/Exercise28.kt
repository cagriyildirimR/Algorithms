package fundamentals.basicProgrammingModel

import print

fun main() {
    val whitelist = mutableListOf(1,2,3,3,4,5,6,6,7,8,8,9,1)

    whitelist.sort()
    whitelist.toSet().toList().print()
}

