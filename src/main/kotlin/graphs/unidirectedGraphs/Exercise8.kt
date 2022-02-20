package graphs.unidirectedGraphs

import java.io.File

class Search(g: Graph, val s: Int) {
    val marked = BooleanArray(g.numberOfVertices) { false }
    val union = IntArray(g.numberOfVertices) { it }
    var count = 0
    init {
//        dfs(g, s)
        for (a in g.adj.indices){
            for (v in g.adj[a]) {
                union(a, v)
            }
        }
    }

    fun dfs(g: Graph, v: Int) {
        marked[v] = true
        count++
        for (i in g.adj[v]) {
            if (!marked[i]){
                dfs(g, i)
            }
        }
    }

    fun union(v: Int, w: Int) {
        val first = union[v]
        val second = union[w]
        for (i in union.indices) {
            if (union[i] == first) {
                union[i] = second
            }
        }
    }

    fun find(v: Int) = union[v] == union[s]
}

fun main() {
    val graph = Graph(File("src/datasets/tinyGex2.txt"))
    println(graph)
    val search = Search(graph, 0)
    println(search.find(5))
    println(search.union.toList())
}