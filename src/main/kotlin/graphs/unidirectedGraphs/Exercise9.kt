package graphs.unidirectedGraphs

import java.io.File

const val SPACE = "   "
private class Exercise9(g: Graph, s: Int) {
    val marked = BooleanArray(g.numberOfVertices) { false }
    val edgeTo = IntArray(g.numberOfVertices) { -1 }
    var spaces = " "
    init {
        dfs(g, s)
        println("  $s done")
    }

    fun dfs(g: Graph, v: Int) {
        println("$spaces dfs($v)")
        spaces += SPACE
        marked[v] = true
        for (i in g.adj[v]) {
            if (!marked[i]){
                edgeTo[i] = v
                dfs(g, i)
                spaces = spaces.slice(0..spaces.lastIndex - 3)
                println("$spaces $i done")
            } else {
                println("$spaces check $i")
            }
        }
    }
}

fun main() {
    val graph = Graph(File("src/datasets/tinyGex2.txt"))
    val e = Exercise9(graph, 0)
    println(e.edgeTo.toList())
}