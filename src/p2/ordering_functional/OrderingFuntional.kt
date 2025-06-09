package p2.ordering_functional

import p2.ordering_object_oriented.OrderResult

typealias Ordering<T> = (T, T) -> OrderResult

val intOrd: Ordering<Int> = { left, right ->
    if(left < right) OrderResult.Lower
    else if (left > right) OrderResult.Higher
    else OrderResult.Equal
}

val stringOrd: Ordering<String> = {left, right ->
    if (left < right) OrderResult.Lower
    else if (left > right) OrderResult.Higher
    else OrderResult.Equal
}

val doubleOrd: Ordering<Double> = {left, right ->
    if (left < right) OrderResult.Lower
    else if (left > right) OrderResult.Higher
    else OrderResult.Equal
}

val booleanOrd: Ordering<Boolean> = { left, right ->
    if (left < right) OrderResult.Lower
    else if (left > right) OrderResult.Higher
    else OrderResult.Equal
}

//fun <A>reversed(ordering: Ordering<A>): Ordering<A> = {left, right -> ordering(right,left)}
//

fun <A>Ordering<A>.reversed(): Ordering<A> = {left, right -> this(right, left)}
//fun <A>debug(ordering: Ordering<A>): Ordering<A> = { left, right ->
//        val res = ordering(left,right)
//        println("$left is $res than $right")
//        res
//    }
fun <A>Ordering<A>.debug(): Ordering<A> = {left, right ->
    val res = this(left, right)
    println("$left is $res than $right")
    res
}
fun <A>none(ordering: Ordering<A>): Ordering<A> = {left, right -> OrderResult.Equal}

//fun <A, B>contraMap(ordering: Ordering<A>, transform: (B) -> A): Ordering<B> = { left, right ->
//        ordering(transform(left),transform(right))
//    }
//
fun <A, B>Ordering<A>.contraMap(transform: (B) -> A): Ordering<B> = {left, right ->
    this(transform(left), transform(right))
}
//fun <A, B>zip(ordering1: Ordering<A>, ordering2: Ordering<B>): Ordering<Pair<A, B>> = { (left1, right1), (left2, right2) ->
//    val res = ordering1(left1, left2)
//    if (res == OrderResult.Equal) ordering2(right1, right2)
//    else res
//}
fun <A, B>Ordering<A>.zip(ordering: Ordering<B>): Ordering<Pair<A, B>> = {(left1, right1), (left2, right2) ->
    val res = this(left1, left2)
    if (res == OrderResult.Equal) ordering(right1, right2)
    else res
}

class Sorting {
    fun <A> sort(list: List<A>, ordering: Ordering<A>): List<A> {
        if (list.isEmpty()) return emptyList()
        val copiedList = list.toMutableList()
        var sorted = false
        var tmp: A?
        while (!sorted) {
            sorted = true
            for (i in 0 until copiedList.lastIndex) {
                val left = copiedList[i]
                val right = copiedList[i + 1]
                val result = ordering(left, right)
                if (result == OrderResult.Higher) {
                    tmp = left
                    copiedList[i] = right
                    copiedList[i + 1] = tmp
                    sorted = false
                }
            }
        }
        return copiedList
    }
}


