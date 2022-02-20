package graphs.unidirectedGraphs

fun Graph.addEdgeButNoParallelEdgesAndNoSelfLoopsWow(v: Int, w: Int) {
    when {
        v != w && !adj[v].contains(w) -> {
            adj[v].add(w)
            adj[w].add(v)
        }
        else -> {}
    }
}
