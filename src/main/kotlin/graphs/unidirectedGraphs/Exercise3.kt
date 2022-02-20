package graphs.unidirectedGraphs

import java.io.File
import java.util.*

class Graph(val file: File) {
    val scanner = Scanner(file)

    val numberOfVertices: Int = scanner.nextInt()
    val numberOfEdges: Int = scanner.nextInt()
    val adj: Array<MutableList<Int>> = Array(numberOfVertices) { mutableListOf() }

    init {
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

    fun copy(): Graph {
        return Graph(file)
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
    val graph1 = Graph(File("src/datasets/tinyCG.txt"))
    val graph2 = graph1.copy()
    graph2.addEdge(0,3)
    println("First Graph")
    println(graph1)
    println("Second Graph")
    println(graph2)
}
