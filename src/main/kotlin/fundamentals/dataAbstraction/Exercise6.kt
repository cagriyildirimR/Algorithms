package fundamentals.dataAbstraction

private fun String.circularRotation(that: String): Boolean {
    return (this.length == that.length) && (this + this).contains(that)
}
