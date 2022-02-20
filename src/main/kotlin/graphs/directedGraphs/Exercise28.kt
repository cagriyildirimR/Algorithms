package graphs.directedGraphs

import print
import java.io.File

fun hasEulerianCycle(file: File): Boolean {
    val digraphWithReverseAdj = DigraphWithReverseAdj(file)
    val degrees = Degrees(digraphWithReverseAdj)
    val strongComponent = StrongComponent(digraphWithReverseAdj)
    return strongComponent.count == 1 && degrees.outdegree.contentEquals(degrees.indegree)
}

fun main() {
    hasEulerianCycle(File("src/datasets/mediumDG.txt")).print()
    hasEulerianCycle(File("src/datasets/eulerianCycle.txt")).print()
}