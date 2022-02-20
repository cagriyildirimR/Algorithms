package graphs.directedGraphs

import java.io.File

open class Degrees(val G: DigraphWithReverseAdj) {

    val indegree = IntArray(G.numberOfVertices) { 0 }
    val outdegree = IntArray(G.numberOfVertices) { 0 }

    init {
        for (i in G.adj.indices) { // E + V
            for (j in G.adj[i]) {
                outdegree[i]++
                indegree[j]++
            }
        }
    }

    fun sources(): Iterable<Int> {
        return IntArray(G.numberOfVertices) { it }.filterIndexed { index, _ -> indegree[index] == 0 } //V
    }

    fun sinks(): Iterable<Int> {
        return IntArray(G.numberOfVertices) { it }.filterIndexed { index, _ -> outdegree[index] == 0 } //V
    }

    fun isMap(): Boolean {
        return outdegree.none { it != 1 } //V
    }

    override fun toString(): String {
        return "Sources: ${sources()}\n" +
                "Sinks: ${sinks()}\n" +
                "indgree values: ${indegree.toList()}\n" +
                "outdegree values ${outdegree.toList()}\n" +
                "is it a Map: ${if (isMap()) "YES" else "NO"}"
    }
}

fun main() {
    val file = File("src/datasets/tinyDGex2.txt")
    val graph = DigraphWithReverseAdj(file)
    println(graph)
    val degrees = Degrees(graph)
    println(degrees)

    val file2 = File("src/datasets/DigraphMap.txt")
    val graph2 = DigraphWithReverseAdj(file2)
    println(graph2)
    val degrees2 = Degrees(graph2)
    println(degrees2)
}