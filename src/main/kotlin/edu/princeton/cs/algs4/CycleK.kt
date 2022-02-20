package edu.princeton.cs.algs4

private class CycleK(G:Graph){
    private val marked = BooleanArray(G.V()){ false }
    private val edgeTo = IntArray(G.V()) { -1 }
    var cycle: Stack<Int>? = null

    init {
        for (v in marked.indices){
            if (!marked[v]) dfs(G, -1, v)
        }
    }

    private fun dfs(G: Graph, u: Int, v: Int) {
        marked[v] = true
        for (w in G.adj(v)){
            if (cycle != null) return

            if (!marked[w]){
                edgeTo[w] = v
                dfs(G, v, w)
            }
            else if (w != u) { // avoiding previous vertex
                cycle = Stack()
                var x = v
                while (x != w){
                    cycle!!.push(x)
                    x = edgeTo[x]
                }
                cycle!!.push(w)
                cycle!!.push(v)
            }
        }
    }
}