package graphs.directedGraphs

import java.io.File
import java.util.*

class DigraphWithReverseAdj(file: File) {
    val numberOfVertices: Int
    val numberOfEdges: Int
    val adj: Array<MutableList<Int>>
    val reversAdj: Array<MutableList<Int>>

    init {
        val scanner = Scanner(file)
        numberOfVertices = scanner.nextInt()
        numberOfEdges = scanner.nextInt()
        adj = Array(numberOfVertices) { mutableListOf() }
        reversAdj = Array(numberOfVertices) { mutableListOf() }

        for (i in 0 until numberOfEdges){
            val v = scanner.nextInt()
            val w = scanner.nextInt()

            this.addEdge(v, w)
            this.reverseAddEdge(v,w)
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

    fun reverseAddEdge(v: Int, w: Int) {
        reversAdj[w].add(v)
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

class StrongComponent(val g: DigraphWithReverseAdj) {
    val postOrder = mutableListOf<Int>()
    var marked = BooleanArray(g.numberOfVertices) { false }

    var count = 0
    val sc = IntArray(g.numberOfVertices) { -1 }

    init {
        for (v in g.reversAdj.indices) {
            if (!marked[v]){
                dfs(g, v)
            }
        }
        postOrder.reverse()

        marked = BooleanArray(g.numberOfVertices) { false }

        for (i in postOrder){
            if (!marked[i]){
                count++
                scDfs(g, i)
            }
        }
    }

    fun dfs(g: DigraphWithReverseAdj, v: Int) {
        marked[v] = true
        for (i in g.reversAdj[v]){
            if (!marked[i]){
                dfs(g, i)
            }
        }
        postOrder.add(v)
    }



    fun scDfs(g: DigraphWithReverseAdj, v: Int){
        marked[v] = true
        sc[v] = count

        for (i in g.adj[v]){
            if (!marked[i]){
                scDfs(g, i)
            }
        }
    }

    fun sc(v: Int, w: Int): Boolean {
        return sc[v] == sc[w]
    }
}

fun main() {
    val digraph = DigraphWithReverseAdj(File("src/datasets/mediumDG.txt"))
    val sc = StrongComponent(digraph)

    println("${sc.count}")
}
