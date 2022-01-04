package fundamentals.unionAndFind


internal interface UnionFind {
    fun union(p: Int, q: Int)
    fun find(p: Int): Int
    fun connected(p: Int, q: Int): Boolean
    fun count(): Int
    fun validate(p: Int)
}

private class QuickFindUF(val n: Int): UnionFind {
    var count = n
    val id = Array(n) { it }

    override fun union(p: Int, q: Int) {
        validate(p)
        validate(q)
        val pid = id[p]
        val qid = id[q]
        if (pid == qid) return
        for (i in id.indices) {
            if (id[i] == pid) id[i] = qid
        }
        count--
    }

    override fun find(p: Int): Int {
        validate(p)
        return id[p]
    }

    override fun connected(p: Int, q: Int): Boolean {
        validate(p)
        validate(q)
        return id[p] == id[q]
    }

    override fun count(): Int = count

    override fun validate(p: Int) {
        if (p !in 0 until n) throw IllegalArgumentException("index $p is not between 0 and ${n-1}")
    }
}

private class QuickUnionUF(val n: Int): UnionFind {

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
        var tmp = p
        while (tmp != id[tmp]) {
            tmp = id[tmp]
        }
        return tmp
    }

    override fun connected(p: Int, q: Int): Boolean {
        return find(p) == find(q)
    }

    override fun count(): Int = count

    override fun validate(p: Int) {
        if (p !in 0 until n) throw IllegalArgumentException("index $p is not between 0 and ${n-1}")
    }
}