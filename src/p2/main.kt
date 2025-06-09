package p2
import p2.ordering_functional.*
import p2.person.*

fun <A> to(f: ( Nothing ) -> A): Unit = Unit
fun <A> from(unit: Unit): ( Nothing ) -> A = { it }

sealed interface Either <out A, out B>
data class Left <A>( val a: A): Either <A, Nothing >
data class Right <B>( val b: B): Either <Nothing , B>
fun <A, B> makeEither (a: A, b: B): Either <A, B> = Left(a)

fun main(){
//    val p1 = Person("Alice", 29)
//    val p2 = Person("John", 31)
//    val p3 = Person("Gustav", 62)
//
//    val intDebug = debug(intOrd)
//    val stringDebug = debug(stringOrd)
//
//    val nameOrdering = contraMap(debug2, Person::name)
//    val ageOrdering = contraMap(debug1, Person::age)
//
//    nameOrdering(p1,p2)
//    ageOrdering(p3,p1)
//    val sorting = Sorting()
//    val people = listOf (
//        Person ("Emma", 25) ,
//        Person ("Alex", 33) ,
//        Person ("Zah", 28) ,
//        Person ("Alex", 18) ,
//        Person ("Jens", 33) ,
//    )
//    val ord: Ordering <Person > =  contraMap (zip (stringDebug,intDebug)){it -> Pair(it.name,it.age)}
//    println ( sorting .sort(people , ord))
    val sorting = Sorting ()
    val people = listOf (
        Person ("Emma", 25, 172.5) ,
        Person ("Alex", 33, 186.0) ,
        Person ("Zah", 28, 158.3) ,
        Person ("Alex", 18, 183.0) ,
        Person ("Jens", 33, 168.5) ,
    )
    val personOrd : Ordering <Person > =
        stringOrd
            .zip(intOrd . reversed ())
            .zip( doubleOrd )
            . contraMap { person ->
                person .name to person .age to person .height // Syntaktischer Zucker für Pair(Pair(person .name , person .age), person .height )
            }
    println ( sorting .sort(people , personOrd ))
}