package algs4

import algs4.Transaction.*
import java.util.*


/**
 * The [Transaction] class is an immutable data type to encapsulate a commercial transaction
 * with a customer name, date, and amount.
 *
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/12oop"> Section 1.2 </a> of
 * <i> Algorithms 4th ed. by Robert Sedgewick and Kevin Wayne</i>
 */
class Transaction(val who: String, val date: Date, val amount: Double): Comparable<Transaction> {
    /**
     * Returns the string representation of this transaction.
     *
     * @return a string representation of this transaction
     */
    override fun toString(): String {
        return "$who $date $amount"
    }

    /**
     * Compares this transaction to specified object
     *
     * @param other the other transaction
     * @return negative if this transaction is less than other
     *         zero     if this transaction is equal to other
     *         positive if this transaction is grater than other
     */
    override fun compareTo(other: Transaction): Int {
        return amount.compareTo(other.amount)
    }

    /**
     * Check if this transaction is equal to other transaction
     *
     * @param other the other transaction
     * @return true if this transaction is equal to other
     *         false otherwise
     */
    override fun equals(other: Any?): Boolean {
        if (other == this) return true
        if (other == null) return false
        if (other.javaClass != this.javaClass) return false
        val that = other as Transaction
        return (this.amount == that.amount) && (this.who == that.who) && (this.date == that.date)
    }

    /**
     * Returns hash code for this transaction.
     *
     * @return a hash code for this transaction
     */
    override fun hashCode(): Int {
        var hash = 1
        hash = 31*hash + who.hashCode()
        hash = 31*hash + date.hashCode()
        hash = 31*hash + amount.hashCode()
        return hash
    }

    /**
     * Compares two transaction by customer name.
     */
    object WhoOrder: Comparator<Transaction> {
        override fun compare(o1: Transaction, o2: Transaction): Int {
            return o1.who.compareTo(o2.who)
        }
    }


    /**
     * Compares two transaction by date.
     */
    object WhenOrder: Comparator<Transaction> {
        override fun compare(o1: Transaction, o2: Transaction): Int {
            return o1.date.compareTo(o2.date)
        }
    }


}


/**
 * Compares two transactions by amount.
 */
object HowMuchOrder : Comparator<Transaction> {
    override fun compare(v: Transaction, w: Transaction): Int {
        return java.lang.Double.compare(v.amount, w.amount)
    }
}
