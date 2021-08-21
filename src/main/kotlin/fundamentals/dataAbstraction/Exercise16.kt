package fundamentals.dataAbstraction

class Rational(private var numerator: Int, private var denominator: Int) {

    init {
        val d = gcd()
        numerator /= d
        denominator /= d
    }
    operator fun plus(b: Rational): Rational {
        return Rational(numerator * b.denominator + b.numerator * denominator, denominator * b.denominator)
    }

    operator fun minus(b: Rational): Rational {
        return Rational(numerator * b.denominator - b.numerator * denominator, denominator * b.denominator)
    }

    operator fun times(b: Rational): Rational {
        return Rational(numerator * b.numerator, denominator * b.denominator)
    }

    operator fun div(b: Rational): Rational {
        return Rational(numerator * b.denominator, denominator * b.numerator)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (javaClass != other.javaClass) return false

        val tmp = other as Rational

        return numerator.toDouble() / denominator == other.numerator.toDouble() / other.denominator
    }

    private fun gcd(p: Int = numerator, q: Int = denominator): Int {
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
    val a = Rational(1,2)
    val b = Rational(5,6)
    val c = Rational(2,4)
    val d = Rational(2,6)

    println("Rational(2,6) should print: 1/3: $d")

    println("plus operator: Rational(1,2).plus(Rational(5,6)) = ${a + b}")
    println("minus operator: Rational(1,2).minus(Rational(5,6)) = ${a - b}")
    println("times operator: Rational(1,2).times(Rational(5,6)) = ${a * b}")
    println("div operator: Rational(1,2).div(Rational(5,6)) = ${a / b}")

    println("equals method checks if Rational(1,2) is equal to Rational(5,6):${a == b}")
    println("equals method checks if Rational(1,2) is equal to Rational(2,4):${a == c}")

    println(Rational(2,0) * Rational(1,2))
    println("Int overflow Example: ${ Rational(Int.MAX_VALUE, 1) + Rational(2, 1)}")
}