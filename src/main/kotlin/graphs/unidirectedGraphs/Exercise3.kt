package graphs.unidirectedGraphs

import java.io.File
import java.util.*

class Graph(val file: File) {

    val numberOfVertices: Int
    val numberOfEdges: Int
    val adj: Array<MutableList<Int>>

    init {
        val scanner = Scanner(file)
        numberOfVertices = scanner.nextInt()
        numberOfEdges = scanner.nextInt()
        adj = Array(numberOfVertices) { mutableListOf() }

        for (i in 0 until numberOfEdges){
            val v = scanner.nextInt()
            val w = scanner.nextInt()

            this.addEdge(v, w)
        }
    }

    fun addEdge(v: Int, w: Int) {
        adj[v].add(w)
        adj[w].add(v)
    }

    fun copy(graph: Graph): Graph {
        return Graph(graph.file)
    }

    override fun toString(): String {
        var result = ""
        for (i in adj.indices) {
            result += "$i: "
            adj[i].forEach { result += " $it " }
            result += "\n"
        }
        return result
    }
}
