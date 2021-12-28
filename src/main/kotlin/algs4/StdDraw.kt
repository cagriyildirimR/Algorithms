package algs4

import java.awt.*
import java.awt.event.*
import java.awt.geom.Line2D
import java.awt.geom.Rectangle2D
import java.awt.image.BufferedImage
import java.util.*
import javax.swing.*
import javax.xml.stream.events.Characters
import kotlin.math.abs
import kotlin.math.round


object StdDraw : ActionListener, MouseListener, MouseMotionListener, KeyListener {

    private val BLACK = Color.BLACK
    val BLUE = Color.BLUE
    val CYAN = Color.CYAN
    val DARK_GRAY = Color.DARK_GRAY
    val GRAY = Color.GRAY
    val GREEN = Color.GREEN
    val LIGHT_GREY = Color.LIGHT_GRAY
    val MAGENTA = Color.MAGENTA
    val ORANGE = Color.ORANGE
    val PINK = Color.PINK
    val RED = Color.RED
    val WHITE = Color.WHITE
    val YELLOW = Color.YELLOW
    val BOOK_BLUE = Color(9, 90, 166)
    val BOOK_LIGHT_BLUE = Color(103, 198, 243)
    val PRINCETON_ORANGE = Color(245, 128, 37)
    val TURKISH_RED = Color(179, 17, 1)

    // default colors
    private val DEFAULT_PEN_COLOR = BLACK
    private val DEFAULT_CLEAR_COLOR = WHITE

    // current pen color
    private lateinit var penColor: Color

    // default canvas size is [DEFAULT_SIZE]-by-[DEFAULT_SIZE]
    private const val DEFAULT_SIZE = 512
    private var width = DEFAULT_SIZE
    private var height = DEFAULT_SIZE

    // default pen radius
    private val DEFAULT_PEN_RADIUS = 0.002

    // current pen radius
    private var penRadius = DEFAULT_PEN_RADIUS

    // show we deaw immediately or wait until next show?
    private var defer = false

    // boundary of drawing canvas, 0% border
    private const val BORDER = 0.00
    private const val DEFAULT_XMIN = 0.0
    private const val DEFAULT_XMAX = 1.0
    private const val DEFAULT_YMIN = 0.0
    private const val DEFAULT_YMAX = 1.0

    private var xmin = 0.0
    private var ymin = 0.0
    private var xmax = 0.0
    private var ymax = 0.0

    // for synchronization
    private var mouseLock = Any()
    private val keyLock = Any()

    // double buffered graphics
    private lateinit var offscreenImage: BufferedImage
    private lateinit var onscreenImage: BufferedImage
    private lateinit var offscreen: Graphics2D
    private lateinit var onscreen: Graphics2D

    // default font
    private val DEFAULT_FONT = Font("SansSerif", Font.PLAIN, 16)

    // current font
    private var font = DEFAULT_FONT

    // mouse state
    private var isMousePressed = false
    private var mouseX = 0.0
    private var mouseY = 0.0

    // the frame for drawing to the screen
    private lateinit var frame: JFrame

    // queue of typed key characters
    private lateinit var keysTyped: LinkedList<Characters>

    // set of key codes currently pressed down
    private lateinit var keysDown: TreeSet<Int>

    init {
        initialize()
    }

    /**
     * Sets the canvas-drawing aret ot be [width]-by-[height] pixels.
     * This also erases the current drawing and resets the coordinate system,
     * pen radius, pen color, and font back to their default values.
     * Ordinarily, this method is called once, at the very beginning of a program.
     *
     * @param canvasWidth the width of the canvas as number of pixels
     * @param canvasHeight the height of the canvas as number of pixels
     * @throws IllegalArgumentException if [canvasHeight] and [canvasWidth] are 0 or negative
     */
    fun setCanvasSize(canvasWidth: Int = DEFAULT_SIZE, canvasHeight: Int = DEFAULT_SIZE) {
        if (canvasWidth <= 0 || canvasHeight <= 0) throw IllegalArgumentException("width and height must be positive")
        width = canvasWidth
        height = canvasHeight
        initialize()
    }

    private fun initialize() {
        frame = JFrame()
        frame.isVisible = false

        offscreenImage = BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_ARGB)
        onscreenImage = BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_ARGB)

        offscreen = offscreenImage.createGraphics()
        onscreen = onscreenImage.createGraphics()

        offscreen.scale(2.0, 2.0)

        setXScale()
        setYScale()
        offscreen.color = DEFAULT_CLEAR_COLOR
        offscreen.fillRect(0, 0, width, height)
        setPenColor()
        setPenRadius()
        setFont()
        clear()

        keysTyped = LinkedList<Characters>()
        keysDown = TreeSet<Int>()

        // add antialiasing
        val hints = RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        )
        hints[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY
        offscreen.addRenderingHints(hints)

        //frame stuff
        val icon = RetinaImageIcon(onscreenImage)
        val draw = JLabel(icon)

        draw.addMouseListener(this)
        draw.addMouseMotionListener(this)

        frame.contentPane = draw
        frame.addKeyListener(this)
        frame.focusTraversalKeysEnabled = false
        frame.isResizable = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.title = "Standard Draw"
        frame.jMenuBar = createMenuBar()
        frame.pack()
        frame.requestFocusInWindow()
        frame.isVisible = true

    }

    private fun createMenuBar(): JMenuBar {
        val menuBar = JMenuBar()
        val menu = JMenu("File")
        menuBar.add(menu)
        val menuItem1 = JMenuItem("Save...")
        menuItem1.addActionListener(this)
        menuItem1.accelerator = KeyStroke.getKeyStroke(
            KeyEvent.VK_S,
            Toolkit.getDefaultToolkit().menuShortcutKeyMaskEx
        )
        menu.add(menuItem1)
        return menuBar
    }

    /***********************************************************************************************
     * User and screen coordinate systems.
     ***********************************************************************************************/

    /**
     * throws an [IllegalArgumentException] if x is NaN or infinete
     */
    private fun validate(x: Double, name: String) {
        when {
            x.isNaN() -> throw IllegalArgumentException("$name is NaN")
            x.isInfinite() -> throw IllegalArgumentException("$name is infinite")
        }
    }

    /**
     * throw an [IllegalArgumentException] if x is negative
     */
    private fun validateNonnegative(x: Double, name: String) {
        if (x < 0) throw IllegalArgumentException("$name is negative")
    }

    /**
     *  Sets the x scale to the specified range.
     *
     *  @param min the minimum value of the x scale
     *  @param max the maximum value of the x scale
     *  @throws IllegalArgumentException if [min] == [max]
     *  @throws IllegalArgumentException if either [min] or [max] is NaN or infinite
     */
    fun setXScale(min: Double = DEFAULT_XMIN, max: Double = DEFAULT_XMAX) {
        validate(min, "min")
        validate(max, "max")
        val size = max - min
        if (size == 0.0) throw IllegalArgumentException("the min and max are the same")
        synchronized(mouseLock) {
            xmin = min - BORDER * size
            xmax = max + BORDER * size
        }
    }

    /**
     *  Sets the y scale to the specified range.
     *
     *  @param min the minimum value of the y scale
     *  @param max the maximum value of the y scale
     *  @throws IllegalArgumentException if [min] == [max]
     *  @throws IllegalArgumentException if either [min] or [max] is NaN or infinite
     */
    fun setYScale(min: Double = DEFAULT_YMIN, max: Double = DEFAULT_YMAX) {
        validate(min, "min")
        validate(max, "max")
        val size = max - min
        if (size == 0.0) throw IllegalArgumentException("the min and max are the same")
        synchronized(mouseLock) {
            ymin = min - BORDER * size
            ymax = max + BORDER * size
        }
    }

    /**
     *  Sets both x-scale and y-scale scale to the same specified range.
     *
     *  @param min the minimum value of the x and y scales
     *  @param max the maximum value of the x and y scales
     *  @throws IllegalArgumentException if [min] == [max]
     *  @throws IllegalArgumentException if either [min] or [max] is NaN or infinite
     */
    fun setScale(min: Double, max: Double) {
        validate(min, "min")
        validate(max, "max")
        val size = max - min
        if (size == 0.0) throw IllegalArgumentException("the min and max are the same")
        synchronized(mouseLock) {
            xmin = min - BORDER * size
            xmax = max + BORDER * size
            ymin = min - BORDER * size
            ymax = max + BORDER * size
        }
    }

    // helper functions that scale from user coordinates to screen coordinates and back
    private fun scaleX(x: Double): Double {
        println(xmin)
        println(xmax)
        println(ymin)
        println(ymax)
        println(width)
        println(height)

        return width * (x - xmin) / (xmax - xmin)
    }
    private fun scaleY(y: Double) = height * (ymax - y) / (ymax - ymin)
    private fun factorX(w: Double) = w * width / abs(xmax - xmin)
    private fun factorY(h: Double) = h * height / abs(ymax - ymin)
    private fun userX(x: Double) = xmin + x * (xmax - xmin) / width
    private fun userY(y: Double) = ymax - y * (ymax - ymin) / height


    /**
     * Clears the screen to the specified color (default is white)
     *
     * @param color the color to make the background
     */
    fun clear(color: Color = DEFAULT_CLEAR_COLOR) {
        offscreen.color = color
        offscreen.fillRect(0, 0, width, height)
        offscreen.color = penColor
        draw()
    }

    /**
     * Sets the radius of the pen to the specified size (default size = 0.002).
     * The pen is circular, so that lines have rounded ends, and when you set the
     * pen radius and draw a point, you get a circle of the specified radius.
     * The pen radius is not affected by coordinate scaling.
     *
     * @param radius the radius of the pen
     * @throws IllegalArgumentException if [radius] is negative, NaN or infinite
     */
    fun setPenRadius(radius: Double = DEFAULT_PEN_RADIUS) {
        validate(radius, "pen radius")
        validateNonnegative(radius, "pen radius")

        penRadius = radius
        val scaledPenRadius = (radius * DEFAULT_SIZE).toFloat()
        val stroke = BasicStroke(scaledPenRadius, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
        offscreen.stroke = stroke
    }

    /**
     * Sets the pen color to the given color (default color is black)
     *
     * The prefered pen colors are [StdDraw.BLACK], [StdDraw.BLUE], [StdDraw.CYAN], [StdDraw.DARK_GRAY],
     * [StdDraw.GRAY], [StdDraw.GREEN], [StdDraw.LIGHT_GREY], [StdDraw.MAGENTA], [StdDraw.ORANGE], [StdDraw.PINK],
     * [StdDraw.RED], [StdDraw.WHITE] and [StdDraw.YELLOW]
     *
     * @param color the color to make the pen
     */
    fun setPenColor(color: Color = DEFAULT_PEN_COLOR) {
        penColor = color
        offscreen.color = penColor
    }

    /**
     * Sets pen color to the given RGB color
     *
     * @param red the amount of red (between 0-255)
     * @param green the amount of green (between 0-255)
     * @param blue the amount of blue (between 0-255)
     * @throws IllegalArgumentException if [red], [green] or [blue] is outside of range 0-255
     */
    fun setPenColor(red: Int, green: Int, blue: Int) {
        if (red < 0 || red > 255) throw IllegalArgumentException("red must be between 0 and 255")
        if (green < 0 || green > 255) throw IllegalArgumentException("green must be between 0 and 255")
        if (blue < 0 || blue > 255) throw IllegalArgumentException("blue must be between 0 and 255")
        setPenColor(Color(red, green, blue))
    }

    /**
     * Sets the font to the given [font] (default font is Sans Serif with 16 points)
     *
     * @param font the font
     */
    fun setFont(font: Font = DEFAULT_FONT) {
        this.font = font
    }

    /***********************************************************************************************
     * Drawing geometric shapes
     ***********************************************************************************************/

    /**
     * Draws a line segment between ([x0],[y0]) and ([x1], [y1])
     *
     * @param x0 the x-coordinate of one endpoint
     * @param y0 the y-coordonate of one endpoint
     * @param x1 the x-coordinate of the other endpoint
     * @param y1 the y-coordinate of the other endpoint
     * @throws IllegalArgumentException if any coordinate is either NaN or infinite
     */
    fun line(x0: Double, y0: Double, x1: Double, y1: Double) {
        validate(x0, "x0")
        validate(y0, "y0")
        validate(x1, "x1")
        validate(y1, "y1")

        //offscreen.draw(Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)))
        offscreen.draw(Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)))

        draw()
    }

    /**
     * Draws one pixel at (x,y)
     * This method is private because pixels depend on the display.
     * To achieve the same effect, set the pen radius to 0 and call [point()]
     * @param x the x-coordinate of the pixel
     * @param y the y-coordinate of the pixel
     * @throws IllegalArgumentException if [x], [y] is either NaN or infinite
     */
    private fun pixel(x: Double, y: Double) {
        validate(x, "x")
        validate(y, "y")
        offscreen.fillRect(round(scaleX(x)).toInt(), round(scaleY(y)).toInt(), 1, 1)
    }

    /**
     * Draws a square of the specified size, centered at [x], [y]
     *
     * @param x the x-coordinate ot the center of the square
     * @param y the y-coordinate at the center of the square
     * @param halfLenght one half the length of the any side of the square
     * @throws IllegalArgumentException if [halfLength] is negative
     * @throws IllegalArgumentException if any argument is either NaN or infinite
     */
    fun square(x: Double, y: Double, halfLength: Double) {
        validate(x, "x")
        validate(y, "y")
        validate(halfLength, "half length")
        validateNonnegative(halfLength, "half length")

        val xs = scaleX(x)
        val ys = scaleY(y)
        val ws = factorX(2 * halfLength)
        val hs = factorY(2 * halfLength)

        if (ws <= 1 && hs <= 1) {
            pixel(x, y)
        } else {
            offscreen.draw(Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs))
        }

        draw()
    }

    /***********************************************************************************************
     * TODO()
     ***********************************************************************************************/

    /**
     * Copies offscreen buffer to onscreen buffer. There is no reason to call
     * this method unless double buffering is enabled.
     */
    private fun show() {
        onscreen.drawImage(offscreenImage, 0, 0, null)
        frame.repaint()
    }

    /**
     * draw onscreen if defer is false
     */
    private fun draw() {
        if (!defer) show()
    }

    override fun actionPerformed(e: ActionEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseClicked(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mousePressed(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseReleased(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseEntered(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseExited(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseDragged(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseMoved(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun keyTyped(e: KeyEvent?) {
        TODO("Not yet implemented")
    }

    override fun keyPressed(e: KeyEvent?) {
        TODO("Not yet implemented")
    }

    override fun keyReleased(e: KeyEvent?) {
        TODO("Not yet implemented")
    }

    private class RetinaImageIcon(image: Image) : ImageIcon(image) {

        override fun getIconWidth(): Int {
            return super.getIconWidth() / 2
        }

        override fun getIconHeight(): Int {
            return super.getIconHeight() / 2
        }

        override fun paintIcon(c: Component, g: Graphics, x: Int, y: Int) {
            val g2 = g.create() as Graphics2D
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            g2.scale(0.5, 0.5)
            super.paintIcon(c, g2, x * 2, y * 2)
            g2.dispose()
        }
    }

}

fun main() {
    StdDraw.line(0.0, 0.4, 0.9, 0.9)
    StdDraw.square(0.5, 0.5, 0.4)
}