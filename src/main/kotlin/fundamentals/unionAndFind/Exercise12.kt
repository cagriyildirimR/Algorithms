package fundamentals.unionAndFind

private class QuickUnionWithPathCompression(val n: Int): UnionFind {

    var count = n
    val id = Array(n) { it }

    override fun union(p: Int, q: Int) {
        val pid = find(p)
        val qid = find(q)
        if (pid == qid) return
        id[pid] = qid
        count--
    }

    override fun find(p: Int): Int {
        validate(p)
        var root = p
        while (root != id[root]) {
            root = id[root]
        }
        var tmp = p
        while (tmp != root) {
            id[tmp] = root
            tmp = id[tmp]
        }

        return root
    }

    override fun connected(p: Int, q: Int): Boolean {
        return find(p) == find(q)
    }

    override fun count(): Int = count

    override fun validate(p: Int) {
        if (p !in 0 until n) throw IllegalArgumentException("index $p is not between 0 and ${n-1}")
    }
}