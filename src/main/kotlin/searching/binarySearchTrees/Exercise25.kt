package searching.binarySearchTrees

private fun median(list: List<Int>): Int {
    return list.sum() / list.size
}

private fun insert(list: List<Int>, bst: BST<Int, Int>) {
    if (list.isEmpty()) return
    val m = median(list)
    bst.insert(key = m, value =  m)
    insert(list.filter { it > m }, bst)
    insert(list.filter { it < m }, bst)
}

fun main() {
    val list = List(5) { it }
    val bst = BST<Int, Int>()

    insert(list, bst)
    println(bst.root)
}
