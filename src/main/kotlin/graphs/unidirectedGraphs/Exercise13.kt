package graphs.unidirectedGraphs

private class BreathFirstPaths(val graph: Graph, val v: Vertex) {
    val size = graph.numberOfVertices
    val marked = BooleanArray(size) { false }
    val distTo = IntArray(size) { Int.MAX_VALUE }
    val edgeTo = IntArray(size) { -1 }
    val adj = graph.adj

    init {
        bfs(graph, v)
    }

    fun distTo(t: Vertex) = distTo[t]

    fun bfs(graph: Graph, v: Vertex) {
        val q = mutableListOf<Vertex>()
        q.add(v)
        distTo[v] = 0
        marked[v] = true

        while (q.isNotEmpty()) {
            val vp = q.removeAt(0)
            for (i in adj[vp]) {
                if (!marked[i]){
                    edgeTo[i] = vp
                    distTo[i] = distTo[vp] + 1
                    marked[i] = true
                    q.add(i)
                }
            }
        }
    }

    fun hasPathTo(s: Vertex) = marked[s]

    fun pathTo(t: Vertex): String {
        var path = ""
        if (!hasPathTo(t)) return "there is no path"

        var x = t
        while (x != v) {
            path += "$x >- "
            x = edgeTo[x]
        }
        return "$path$v".reversed()
    }
}
