package searching.binarySearchTrees

// data NodeD = Empty | Node { key :: a, value :: b, left :: NodeD, right :: NodeD }
sealed class NodeD<out Key, out Value>

private object Empty : NodeD<Nothing, Nothing>() {
    override fun toString(): String {
        return "Empty"
    }
}

private data class Node<Key : Comparable<Key>, Value>(
    val key: Key,
    var value: Value,
    var size: Int,
    var left: NodeD<Key, Value>,
    var right: NodeD<Key, Value>
) : NodeD<Key, Value>()

class BST<Key : Comparable<Key>, Value> {
    var root: NodeD<Key, Value> = Empty // root of BST

    /**
     * Returns true if BST is empty
     * @return true if empty false otherwise
     */
    fun isEmpty(): Boolean = root is Empty

    /**
     * Returns the size of BST
     * @return size of the BST
     */
    fun size(): Int {
        return when (root) {
            is Empty -> 0
            is Node<Key, Value> -> (root as Node<Key, Value>).size
        }
    }

    fun insert(t: NodeD<Key, Value> = root, key: Key, value: Value) {
        when {
            root is Empty -> {
                root = Node(key, value, 1, Empty, Empty)
            }
            t is Node<Key, Value> -> {
                if (key == t.key) t.value = value
                else if (key > t.key) {
                    if (t.right is Empty) t.right = Node(key, value, 1, Empty, Empty) else {
                        insert(t.right, key, value)
                    }
                } else {
                    if (t.left is Empty) t.left = Node(key, value, 1, Empty, Empty) else insert(t.left, key, value)
                }
                val r = if (t.right is Empty) 0 else (t.right as Node<Key, Value>).size
                val l = if (t.left is Empty) 0 else (t.left as Node<Key, Value>).size

                t.size = 1 + r + l
            }
        }

    }

    fun search(node: NodeD<Key, Value> = root, key: Key): Value? {
        return when (node) {
            is Empty -> null
            is Node<Key, Value> -> {
                if (key == node.key) node.value
                else if (key < node.key) search(node.left, key)
                else search(node.right, key)
            }
        }
    }

    fun getNode(node: NodeD<Key, Value> = root, key: Key): NodeD<Key, Value> {
        return when (node) {
            is Empty -> Empty
            is Node<Key, Value> -> {
                if (key == node.key) node
                else if (key < node.key) getNode(node.left, key)
                else getNode(node.right, key)
            }
        }
    }

    fun contains(node: NodeD<Key, Value>, key: Key): Boolean {
        return when (node) {
            is Empty -> false
            is Node<Key, Value> -> {
                if (key == node.key) true
                else if (key < node.key) contains(node.left, key)
                else contains(node.right, key)
            }
        }
    }

    fun floor(tree: NodeD<Key, Value> = root, key: Key, best: Key? = null): Key {
        val result = when (tree) {
            is Empty -> best
            is Node<Key, Value> -> {
                if (tree.key > key) floor(tree.left, key, best)
                else if (tree.key < key) floor(tree.right, key, tree.key)
                else tree.key
            }
        } ?: throw NoSuchElementException("argument is too small")
        return result
    }

    fun select(rank: Int, tree: NodeD<Key, Value> = root): Key {
        require(rank >= 0) {
            "rank should be positive integer"
        }
        require(root !is Empty) {
            "root is empty"
        }
        tree as Node<Key, Value>
        require(tree.size > rank) { "rank should be smaller than tree size" }

        return when (val left = tree.left) {
            is Empty -> tree.key
            is Node<Key, Value> -> {
                when {
                    left.size == rank -> tree.key
                    left.size > rank -> select(rank, tree.left)
                    else -> select(rank - left.size - 1, tree.right)
                }
            }
        }
    }

    fun dfs(tree: NodeD<Key, Value> = root): List<Value> {
        return when (tree) {
            is Empty -> emptyList()
            is Node<Key, Value> -> dfs(tree.left) + listOf(tree.value) + dfs(tree.right)
        }
    }

    fun bfs(): List<Value> {
        return when(val tree = root) {
            is Empty -> listOf()
            is Node<Key, Value> -> {
                val q = mutableListOf<Key>(tree.key)
                bfs(q)
            }
        }
    }

    private fun bfs(queue: MutableList<Key>): List<Value> {
        if (queue.isNotEmpty()) {
            val k = queue.removeFirst()
            val tree = getNode(root, k)
            if (tree is Node<Key, Value>) {
                if (tree.left is Node<Key, Value>) {
                    queue.add((tree.left as Node<Key, Value>).key)
                }
                if (tree.right is Node<Key, Value>) {
                    queue.add((tree.right as Node<Key, Value>).key)
                }
            }
            return listOf(search(key = k)!!) + bfs(queue)
        }
        return listOf()
    }

}

fun main() {
    val ss = "63749"
    val bst = BST<Char, Int>()
    for (i in 0..ss.lastIndex) {
        bst.insert(key = ss[i], value = i)
    }
    println(bst.root)

    println(bst.dfs())

    println(bst.bfs())
}
