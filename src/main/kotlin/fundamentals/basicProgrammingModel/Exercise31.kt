package fundamentals.basicProgrammingModel

import tornadofx.*

class MyView: View() {
    override val root = vbox {
        rectangle {
            x = 10.0
            y = 22.0

        }
    }
}

class MyViewApp: App(MyView::class)

fun main(args: Array<String>) {
    launch<MyViewApp>(args)
}