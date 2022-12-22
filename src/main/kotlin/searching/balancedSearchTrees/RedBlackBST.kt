package searching.balancedSearchTrees

private const val RED = true
private const val BLACK = false

private sealed class NodeRB<out Key, out Value>()
private object Empty : NodeRB<Nothing, Nothing>() {
    override fun toString(): String {
        return "Empty"
    }
}

private data class Node<Key, Value>(
    val key: Key,
    var value: Value,
    var left: NodeRB<Key, Value>,
    var right: NodeRB<Key, Value>,
    var color: Boolean,
    var size: Int
) : NodeRB<Key, Value>()

private class RedBlackBST<Key : Comparable<Key>, Value> {

    private var root: NodeRB<Key, Value> = Empty

    private fun isRed(node: NodeRB<Key, Value>): Boolean = when (node) {
        is Empty -> false
        is Node<Key, Value> -> node.color == RED
    }

    private fun size(node: NodeRB<Key, Value>): Int = when (node) {
        is Empty -> 0
        is Node<Key, Value> -> node.size
    }

    fun size() = size(root)

    fun isEmpty() = root == Empty

    /**
     * return value associated with the given key
     * @param key the key
     * @param node tree to search for key-value pairs
     * @return value if node is not [Empty] else
     * @throws IllegalArgumentException if node is [Empty]
     */
    fun get(key: Key, node: NodeRB<Key, Value>): Value = when (node) {
        is Empty -> throw IllegalArgumentException("key is not in the tree")
        is Node<Key, Value> -> when {
            key < node.key -> get(key, node.left)
            key > node.key -> get(key, node.right)
            else -> node.value
        }
    }

    private fun rotateLeft(oldRoot: Node<Key, Value>): Node<Key, Value> {
        val newRoot = oldRoot.right as Node<Key, Value> // node is only RED if its not Empty
        oldRoot.right = newRoot.left
        newRoot.left = oldRoot
        newRoot.color = oldRoot.color
        oldRoot.color = RED
        newRoot.size = oldRoot.size
        oldRoot.size = 1 + size(oldRoot.left) + size(oldRoot.right)
        return newRoot
    }

    private fun rotateRight(oldRoot: Node<Key, Value>): Node<Key, Value> {
        val newRoot = oldRoot.left as Node<Key, Value>
        oldRoot.left = newRoot.right
        newRoot.right = oldRoot
        newRoot.color = oldRoot.color
        oldRoot.color = RED
        newRoot.size = oldRoot.size
        oldRoot.size = 1 + size(oldRoot.left) + size(oldRoot.right)
        return newRoot
    }

    private fun flipColors(node: Node<Key, Value>) {
        node.color = RED
        (node.left as Node<Key, Value>).color = BLACK
        (node.right as Node<Key, Value>).color = BLACK
    }

    private fun put(key: Key, value: Value, node: NodeRB<Key, Value>): Node<Key, Value> = when (node) {
        is Empty -> Node(key = key, value = value, left = Empty, right = Empty, color = RED, size = 1)
        is Node<Key, Value> -> {
            var newNode = node
            val cmp = key.compareTo(newNode.key)

            when {
                cmp < 0 -> newNode.left = put(key, value, newNode.left)
                cmp > 0 -> newNode.right = put(key, value, newNode.right)
                else -> newNode.value = value
            }

            if (isRed(newNode.right) && !isRed(newNode.left)) newNode = rotateLeft(newNode)
            if (isRed(newNode.left) && isRed((newNode.left as Node<Key, Value>).left)) newNode = rotateRight(newNode)
            if (isRed(newNode.left) && isRed(newNode.right)) flipColors(newNode)
            newNode.size = 1 + size(newNode.left) + size(newNode.right)

            newNode
        }
    }

    fun put(key: Key, value: Value) {
        root = put(key, value, root)
        (root as Node<Key, Value>).color = BLACK
    }

    fun is23(node: NodeRB<Key, Value> = root): Boolean = when (node) {
        is Empty -> true
        is Node<Key, Value> -> {
            when {
                isRed(node.right) -> false
                node != root && isRed(node) && isRed(node.left) -> false
                else -> is23(node.left) && is23(node.right)
            }
        }
    }

    fun is23(): Boolean {
        val stack = mutableListOf<NodeRB<Key, Value>>()
        stack.add(root)
        while (stack.isNotEmpty()) {
            when (val tmp = stack.removeFirst()) {
                is Empty -> {/* do nothing */
                }
                is Node<Key, Value> -> when {
                    isRed(tmp.left) && isRed(tmp.right)  -> return false
                    !isRed(tmp.left) && isRed(tmp.right) -> return false
                    else                                 -> {
                                                            stack.add(tmp.left)
                                                            stack.add(tmp.right)
                                                            }
                }
            }
        }
        return true
    }

    fun getNode(key: Key, node: NodeRB<Key, Value> = root): NodeRB<Key, Value> = when (node) {
        is Empty -> throw IllegalArgumentException("key is not in table")
        is Node<Key, Value> -> when {
            key < node.key -> getNode(key = key, node = node.left)
            key > node.key -> getNode(key = key, node = node.right)
            else -> node
        }
    }

    fun isBalanced(): Boolean {
        var black = 0
        var node = root
        while (node is Node<Key,Value>) {
            if (!isRed(node)) black++
            node = node.left
        }
        return isBalanced(root, black)
    }

    private fun isBalanced(node: NodeRB<Key, Value>, black: Int): Boolean = when(node) {
        is Empty            -> black == 0
        is Node<Key, Value> -> {
            var b = black
            if (!isRed(node)) b--
            isBalanced(node.left, b) && isBalanced(node.right, b)
        }
    }

    override fun toString(): String {
        return "$root"
    }
}

fun main() {
    val rb = RedBlackBST<Int, String>()

    for (i in 0..20) {
        rb.put(i, "$i")
    }
    println("$rb")

    println("is 23 ${rb.is23()}")

    println("is tree balanced ${rb.isBalanced()}")
}
