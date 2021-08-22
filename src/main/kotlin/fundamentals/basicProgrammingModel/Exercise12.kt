package fundamentals.basicProgrammingModel

fun main() {
    // same as
    (0..9).forEach(::println)
}

/**
 * int[] a = new int[10] // create int array of size 10
 * for (int i = 0; i < 10; i++)
 *     a[i] = 9 - i; //  a = [9,8,7,6,5,4,3,2,1,0]
 * for (int i = 0; i < 10; i++)
 *     a[i] = a[a[i]]; // a = [0,1,2,3,4,5,6,7,8,9]
 * for (int i = 0; i < 10; i++)
 *     System.out.println(a[i])
 */