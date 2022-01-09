package graphs.unidirectedGraphs


sealed class Direction(open val value: Int, open val bin: Int, open val binO: Int)
private data class North(override val value: Int, override val bin: Int = 1, override val binO: Int = 4) :
    Direction(value, bin, binO)

private data class East(override val value: Int, override val bin: Int = 2, override val binO: Int = 8) :
    Direction(value, bin, binO)

private data class South(override val value: Int, override val bin: Int = 4, override val binO: Int = 1) :
    Direction(value, bin, binO)

private data class West(override val value: Int, override val bin: Int = 8, override val binO: Int = 2) :
    Direction(value, bin, binO)

val asset = listOf<String>("|__", "|__", "|__", "|__", "|  ", "|  ", "|  ", "|  ", "___",
    "___", "___", "___", "   ", "   ", "   ", "   ")

/**
 * Maze has [width] and [height]
 */
class Maze(private val width: Int, val height: Int) {
    val vertices: Array<MutableList<Direction>> = Array(width * height) { mutableListOf() } // N E S W
    private val visited: BooleanArray = BooleanArray(width * height) { false }
    private val render = IntArray(width * height) { 0 }

    init {
        initialize()
        dfs(0)
    }

    fun dfs(currentVertex: Int) {
        visited[currentVertex] = true
        vertices[currentVertex].shuffle()
        for (newVertex in vertices[currentVertex]) {
            if (!visited[newVertex.value]) {
                render[currentVertex] += newVertex.bin
                render[newVertex.value] += newVertex.binO
                dfs(newVertex.value)
            }
        }
    }

    /**
     * initializing a maze of which every vertex is connected to neighbouring vertices
     */
    fun initialize() {
        for (i in vertices.indices) {
            val v = vertices[i]
            if (i >= width) v.add(North(i - width))
            if ((i + 1) % width != 0) v.add(East(i + 1))
            if (i <= width * (height - 1) - 1) v.add(South(i + width))
            if (i % width != 0) v.add(West(i - 1))
        }
    }

    override fun toString(): String {
        var result = "   "

        repeat(width-1){
            result += "___"
        }
        result += "\n"

        for (i in render.indices) {
            if (i % width == 0 && i > 0) result += "|\n"
            result += asset[render[i]]
        }
        return result
    }
}

fun main() {
    val maze = Maze(14, 14)
    println(maze)
}