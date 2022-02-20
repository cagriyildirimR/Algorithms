package graphs.unidirectedGraphs

import java.io.File
import java.util.*

// Parallel edge detection
fun detectParallelEdges(G:Graph){
    val marked = BooleanArray(G.numberOfVertices) { false }
    for (v in G.adj.indices){
        for (i in G.adj[v]){
            if (marked[i]){
                println("Parallel edge detected between $v -> $i")
            }
            marked[i] = true
        }
        for (i in marked.indices){
            marked[i] = false
        }
    }
}

fun main() {
    val file = File("src/datasets/parallelEdges.txt")
    val graph = Graph(file)
    println(graph)
    detectParallelEdges(graph)
}