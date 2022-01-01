
```kotlin
fun isEggsBroken(x: Int): Boolean {
    return when {
        x < F -> false 
        else  -> true
    }
}
```


### log N solution 
We use binary search to find F. Start with 

`lo = 0 and hi = N-1 where mid = (lo + hi) / 2`

if egg is broken at `mid` then we change hi to `mid` otherwise change `lo= mid+1` and repeat until
`lo == hi`

### 2 log F solution

We start from bottom and double every turn and check if egg is broken. This process takes at most log F

Then we use binary search between this point and last location where egg is broken. This range is at most F.

Solution is in 2logF
