package p2.ordering_object_oriented

enum class OrderResult {
    Lower, // -1
    Higher,  // 1
    Equal // 0
}

interface Ordering<A> {
    fun compare(left: A, right: A): OrderResult
}

// Strategien

class IntOrdering : Ordering<Int> {
    override fun compare(left: Int, right: Int): OrderResult {
        return if (left < right) OrderResult.Lower
        else if (left > right) OrderResult.Higher
        else OrderResult.Equal
    }
}

class StringOrdering : Ordering<String> {
    override fun compare(left: String, right: String): OrderResult {
        val result = left.compareTo(right)
        return if (result < 0) OrderResult.Lower
        else if (result > 0) OrderResult.Higher
        else OrderResult.Equal
    }
}

// Dekorierer

class ReversedOrdering<A>(val ord: Ordering<A>) : Ordering<A> {
    override fun compare(left: A, right: A): OrderResult {
        return ord.compare(right, left)
    }
}

class DebugOrdering<A>(val ord: Ordering<A>) : Ordering<A> {
    override fun compare(left: A, right: A): OrderResult {
        val result = ord.compare(left, right)
        println("$left is $result than $right")
        return result
    }
}

// Kompositum

data class Person(val name: String, val age: Int)

class PersonNameOrdering : Ordering<Person> {
    override fun compare(left: Person, right: Person): OrderResult {
        return StringOrdering().compare(left.name, right.name)
    }
}

class PersonAgeOrdering : Ordering<Person> {
    override fun compare(left: Person, right: Person): OrderResult {
        return IntOrdering().compare(left.age, right.age)
    }
}

class CombineOrdering<A>(val ord1: Ordering<A>, val ord2: Ordering<A>) : Ordering<A> {
    override fun compare(left: A, right: A): OrderResult {
        val ord1Result = ord1.compare(left, right)
        return if (ord1Result == OrderResult.Equal) ord2.compare(left, right)
        else ord1Result
    }
}

class Orderings<A>(val orderings: List<Ordering<A>>) : Ordering<A> {
    override fun compare(left: A, right: A): OrderResult {
        return when (orderings.size) {
            0 -> OrderResult.Equal
            1 -> {
                val ord = orderings.first()
                ord.compare(left, right)
            }

            else -> {
                val ord = orderings.reduce { ord1, ord2 -> CombineOrdering(ord1, ord2) }
                return ord.compare(left, right)
            }
        }
    }
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
                val result = ordering.compare(left, right)
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