package fundamentals.dataAbstraction

/**
 *  Note to future-self: to activate assert go to Run > Edit Configurations > VM options and add "-ea"
 **/

const val OVERFLOW_ERROR = "Operation cause overflow"

class RationalSafe(private var numerator: Int, private var denominator: Int) {

    init {
        val d = gcd()
        numerator /= d
        denominator /= d

        if (numerator > 0 && denominator < 0) {
            numerator *= -1
            denominator *= -1
        }
    }

    operator fun plus(b: RationalSafe): RationalSafe {
        return RationalSafe(numerator * b.denominator + b.numerator * denominator, denominator * b.denominator)
    }

    operator fun minus(b: RationalSafe): RationalSafe {
        return RationalSafe(numerator * b.denominator - b.numerator * denominator, denominator * b.denominator)
    }

    operator fun times(b: RationalSafe): RationalSafe {
        return RationalSafe(numerator * b.numerator, denominator * b.denominator)
    }

    operator fun div(b: RationalSafe): RationalSafe {
        return RationalSafe(numerator * b.denominator, denominator * b.numerator)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (javaClass != other.javaClass) return false

        val tmp = other as RationalSafe

        return numerator.toDouble() / denominator == other.numerator.toDouble() / other.denominator
    }

    fun gcd(p: Int = numerator, q: Int = denominator): Int {
        if (q == 0) return p
        val r = p % q
        return gcd(q, r)
    }

    override fun toString(): String {
        return "$numerator/$denominator"
    }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        return result
    }


}

fun main() {
    val a = RationalSafe(1, 2)
    val b = RationalSafe(5, 6)
    val c = RationalSafe(2, 4)
    val d = RationalSafe(2, 6)

    println("Rational(2,6) should print: 1/3: $d")

    println("plus operator: Rational(1,2).plus(Rational(5,6)) = ${a + b}")
    println("minus operator: Rational(1,2).minus(Rational(5,6)) = ${a - b}")
    println("times operator: Rational(1,2).times(Rational(5,6)) = ${a * b}")
    println("div operator: Rational(1,2).div(Rational(5,6)) = ${a / b}")

    println("equals method checks if Rational(1,2) is equal to Rational(5,6):${a == b}")
    println("equals method checks if Rational(1,2) is equal to Rational(2,4):${a == c}")

    println(RationalSafe(2, 0) * RationalSafe(1, 2))
    println("Int overflow Example: ${RationalSafe(Int.MAX_VALUE, 1) + RationalSafe(1, 2)}")

    println("When both negative: ${RationalSafe(-1, -2)}")
    println("If only denominator is negative ${RationalSafe(1, -2)}")
}