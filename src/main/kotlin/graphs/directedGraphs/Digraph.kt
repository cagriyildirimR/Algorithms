package graphs.directedGraphs

import java.io.File
import java.util.*

class Digraph(file: File) {
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

    /**
     * adds edge from [v] to [w]
     * @param v vertex the edge starts
     * @param w vertex the edge ends
     */
    fun addEdge(v: Int, w: Int) {
        adj[v].add(w)
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

fun main() {
    val file = File("src/datasets/tinyGex2.txt")
    val graph = Digraph(file)
    println(graph)
}
