package algs4

import kotlin.IllegalArgumentException
import kotlin.math.*
import kotlin.random.Random

object StdRandom {

    var seed: Long = System.currentTimeMillis()
        set(value) {
            random = Random(seed)
            field = value
        }
    var random = Random(seed)

    /**
     * Returns a real number uniformly in [0,1)
     *
     * @return random real number uniformly in [0,1)
     */
    fun uniform() = random.nextDouble()

    /**
     * Returns a random integer uniformly in [0, n)
     *
     * @param n outer bound for possible integers
     * @return random integer uniformly in [0, n)
     * @throws IllegalArgumentException if n is zero or negative
     */
    fun uniform(n: Int) = if (n <= 0) throw IllegalArgumentException("argument $n must be positive") else random.nextInt(n)

    /**
     * Returns a random long integer uniformly in [0, n)
     *
     * @param n outer bound for long integer
     * @return random long integer uniformly in [0, n)
     * @throws IllegalArgumentException if n is zero or negative
     */
    fun uniform(n:Long) = if (n <= 0) throw IllegalArgumentException("argument $n must be positive") else random.nextLong(n)

    // METHODS BELOW RELY ON KOTLIN RANDOM INDIRECTLY VIA METHODS ABOVE

    /**
     * Returns a random integer between [a, b)
     *
     * @param a the left end point
     * @param b the right ened point
     * @return random integer between [a, b)
     * @throws IllegalArgumentException if {@code b <= a}
     * @throws IllegalArgumentException if b - a >= Integer.MAX_VALUE
     */
    fun uniform(a: Int, b: Int): Int {
        if ((b <= a) || (b.toLong() - a >= Int.MAX_VALUE)) throw IllegalArgumentException("invalid range [$a, $b)")

        return a + uniform(b - a)
    }

    /**
     * Return a random real number uniformly in [a, b)
     *
     * @param a the left end point
     * @param b the right end point
     * @return a random real number uniformly in [a, b)
     * @throws IllegalArgumentException if a >= b
     */
    fun uniform(a:Double, b:Double): Double {
        if (a >= b) throw IllegalArgumentException("invalid range [$a, $b)")

        return a + uniform() * (b - a)
    }

    /**
     * Returns a random boolean from a Bernoulli distribution with success probability <em> p </em>
     *
     * @param p the probability of returning `true`
     * @return `true` with probability `p`
     *         `false` with probability `p`
     * @throws IllegalArgumentException unless 0 <= p <= 1
     */
    fun bernoulli(p: Double): Boolean {
        if (p !in 0.0..1.0) throw IllegalArgumentException("probability p must be between 0.0 and 1.0: p = $p")

        return uniform() < p
    }

    /**
     * Returns a random Bernoulli distribution with success probability 1/2
     *
     * @return `true` with probability 1/2
     *         `false` with probability 1/2
     */
    fun bernoulli(): Boolean = bernoulli(0.5)

    /**
     * Returns a random real number from a standard Gaussian distribution
     *
     * @return a random real number from a standard Gaussian distribution with zero mean and standard deviation is one
     */
    fun gaussian():Double {
        // use the polar form of Box-Muller transform
        var r = 0.0
        var x = 0.0
        var y = 0.0

        do {
            x = uniform(-1.0, 1.0)
            y = uniform(-1.0, 1.0)
            r = x*x + y*y
        } while (r >= 1.0 || r == 0.0)

        return x * sqrt(-2 * log(r, 10.0) / r)
    }

    /**
     * Returns a random real number from Gaussian distribution with mean &mu; and standard deviation &sigma;
     *
     * @param mu the mean
     * @param sigma the standard deviation
     * @return a real number distributed according to the Gaussian distribution with mean mu and the standard
     *         deviation sigma
     */
    fun gaussian(mu: Double, sigma: Double) = mu + sigma * gaussian()

    /**
     * Returns a random integer from a geometric distribution with success probability p
     *
     * @param p the parameter of geometric distribution
     * @return a random integer from a geometric distribution with success probability p; or
     *         is nearly equal to 1.0
     * @throws IllegalArgumentException if p != 0.0..1.0
     */
    fun geometric(p: Double): Double {
        if (p < 0 && p > 1) throw IllegalArgumentException("probability must between 0.0 and 1.0: p is $p")

        return ceil(ln(uniform())).toInt() / ln(1 - p)
    }

    /**
     * Returns a random integer from a Poisson distribution with mean lambda
     *
     * @param lambda the mean of Poisson distribution
     * @return a random integer from a Poisson distribution with mean lambda
     * @throws IllegalArgumentException unless lambda > 0.0 and not infinite
     */
    fun poisson(lambda: Double): Int {
        if (lambda <= 0.0) throw IllegalArgumentException("lambda must be positive: $lambda")
        if (lambda == Double.POSITIVE_INFINITY) throw IllegalArgumentException("lambda must not be infinite: $lambda")

        var k = 0
        var p = 1.0
        val expLambda = exp(-lambda)
        do {
            k++
            p *= uniform()
        } while (p >= expLambda)

        return k -1
    }

    /**
     * Returns a random real number from standard Pareto distribution
     *
     * @return a random real number from standard Pareto distribution
     */
    fun pareto() = pareto(1.0)

    /**
     * Returns a random real number from a Pareto distribution with shape parameter alpha
     *
     * @param alpha shape parameter
     * @return a random real number from a Pareto distribution with shape parameter alpha
     * @throws IllegalArgumentException unless alpha > 0.0
     */
    fun pareto(alpha: Double): Double {
        if (alpha <= 0.0) throw IllegalArgumentException("alpha must be positive: $alpha")
        return (1 - uniform()).pow(-1.0 / alpha) - 1.0;
    }

    /**
     * Returns a random real number from a Cauchy distribution
     *
     * @return a random real number from a Cauchy distribution
     */
    fun cauchy(): Double = tan(Math.PI * (uniform() - 0.5))

    /**
     * Returns a random integer from the specified discrete distribution
     *
     * @param probabilities the probability of occurrence of each integer
     * @return a random integer from the specified discrete distribution
     *         `i` with probability `probabilities[i]`
     * @throws IllegalArgumentException if sum of array entries are not equal to 1.0
     * @throws IllegalArgumentException unless probabilities >= 0 for each index
     */
    fun discrete(probabilities: DoubleArray): Int {
        val EPSILON = 1.0E-14
        var sum = 0.0
        for (p in probabilities) {
            if (p < 0) throw IllegalArgumentException("probability values must be positive: $p")
            sum += p
        }
        if (sum > 1.0 + EPSILON || sum < 1.0 - EPSILON) throw IllegalArgumentException("sum of array must be one: $sum")

        while (true) {
            val r = uniform()
            sum = 0.0
            for (i in probabilities.indices) {
                sum += probabilities[i]
                if (sum > r) return i
            }
        }
    }

    /**
     * Returns a random integer from the specified discrete distribution
     *
     * @param frequencies the frequency of occurrence of each integer
     * @return a random integer from the specified discrete distribıtion
     *         i with probability proportional to frequencies[i]
     * @throws IllegalArgumentException if all array entries are zero
     * @throws IllegalArgumentException if frequencies[i] is negative for any index
     * @throws IllegalArgumentException if sum of frequencies exceeds Int.MAX_VALUE
     */
    fun discrete(frequencies: IntArray): Int {
        var sum = 0L
        for (f in frequencies) {
            if (f < 0) throw IllegalArgumentException("frequencies must not be negative: $f")
            sum += f
        }
        if (sum == 0L) throw IllegalArgumentException("at least one entry must be positive")
        if (sum >= Int.MAX_VALUE) throw IllegalArgumentException("sum of frequencies overflows an int")

        val r = uniform(sum.toInt())
        sum = 0
        for (i in frequencies.indices) {
            sum += frequencies[i]
            if (sum > r) return i
        }

        assert(false)
        return -1
    }

    /**
     * Returns a random real number from an exponential distribution with rate lambda
     *
     * @param lambda the rate of the exponential distribution
     * @return a random real number from an exponential distribution with rate lambda
     * @throws IllegalArgumentException if lambda is negative
     */
    fun exp(lambda: Double): Double {
        if (lambda < 0.0) throw IllegalArgumentException("lambda must be positive: $lambda")
        return -ln(1 - uniform()) / lambda
    }

    /**
     * Rearranges the elements of the specified array in uniformly random order
     *
     * @param a the array to shuffle
     */
    fun <T> shuffle(a:Array<T>) {
        val n = a.size
        for (i in a.indices) {
            val r = i + uniform(n - i)
            a[i] = a[r].also { a[r] = a[i] }
        }
    }

    /**
     * Rearranges the elements of specified subarray in uniformly random order.
     *
     * @param a the array to shuffle
     * @param lo the left endpoint inclusive
     * @param hi the right endpoint exclusive
     * @throws IllegalArgumentException unless hi <= a.length and lo in 0..hi
     */
    fun <T> shuffle(a: Array<T>, lo: Int, hi: Int) {
        if (lo !in 0..hi) throw IllegalArgumentException("lo must be between 0 and hi")
        if (hi > a.size) throw IllegalArgumentException("hi cannot exceed number of elements in a")
        for (i in lo..hi) {
            val r = i + uniform(hi - i)
            a[i] = a[r].also { a[r] = a[i] }
        }
    }

    /**
     * Returns a uniformly random permutation of n elements
     *
     * @param n number of elements
     * @return an array of length n that is a uniformly random permutation of (0..n-1)
     * @throws IllegalArgumentException if n is negative
     */
    fun permutation(n: Int): Array<Int> {
        if (n < 0) throw IllegalArgumentException("argument is negative")
        val perm = Array(n){it}
        shuffle(perm)
        return perm
    }

    /**
     * Returns a uniformly random permutation of k of n elements
     *
     * @param n number of elements
     * @param k number of elements to select
     * @return an array of length k that is a uniformly random permutation of
     *         k of the elements from (0..n-1)
     */
    fun permutation(n: Int, k: Int):Array<Int> {
        if (n < 0) throw IllegalArgumentException("argument is negative")
        if (k < 0 || k > n) throw IllegalArgumentException("k must be between 0 and n")
        val perm = Array(k) { 0 }
        for (i in perm.indices) {
            val r = uniform(i+1)
            perm[i] = perm[r].also { perm[r] = i }
        }
        for (i in k until n) {
            val r = uniform(i+1)
            if (r < k) perm[r] = i
        }
        return perm
    }
}
