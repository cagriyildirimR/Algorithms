1.1.1.

a. ``(0 + 15) / 2 = 7  ``

Kotlin's implementation of div has same return type as it's input. 
If we provide Int then we get Int as a result

    public operator fun div(other: Int): Int
    public operator fun div(other: Double): Double

b. ``2.0e-6 * 100_000_000.1 = 200.000_000_2``

c. ``true && false || true && true = true``