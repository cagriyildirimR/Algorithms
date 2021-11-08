`````java
public static void sort(Comparable[] a) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
        int min = i;
        for (int j = i+1; i < n; j++) {
            if (less(a[j], a[i])) min = j
        }
        exch(a, i, min);
        assert isSorted(a, 0, i);
    }
    assert isSorted(a)    
}
`````

Example array = [5, 1, 2, 3, 4]

If an array's first element is the largest value and rest of the array is sorted then first element will move in each
iteration. Maximum number of exchanges will be N.

sort() uses exch() N times regardless of array. Each exchange has two elements. Average number of exchanges involving an
element will be 2.
