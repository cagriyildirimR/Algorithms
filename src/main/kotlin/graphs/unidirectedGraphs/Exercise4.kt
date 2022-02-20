package graphs.unidirectedGraphs

import java.io.File

fun Graph.hasEdge(v: Int, w: Int): Boolean {
    return this.adj[v].contains(w)
}

fun main() {
    val graph = Graph(File("src/datasets/tinyCG.txt"))
    println(graph)
    println("graph has edge between 0, 1: ${graph.hasEdge(0, 1)}")
    println("graph has edge between 0, 3: ${graph.hasEdge(0, 3)}")
}
