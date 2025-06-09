<details>
<summary>Aufgabe 1</summary>

## Von objektorientiert zu funktional

Im ersten Vorlesungsblock haben wir das Ordering Beispiel mit Entwurfsmustern objektorien-
tiert implementiert. In dieser Aufgabe soll dieses Beispiel mit funktionalen Prinzipien vollstän-
dig neu implementiert werden. Es sollen beispielsweise ausschließlich Funktionen und Werte
und keine Klassen und Interfaces verwendet werden.
Als Orientierung können Sie den objektorientierten Code aus der Vorlesung verwenden: https:
//gist.github.com/alexdobry/57a4e88f975c8dd46926668ca16e2856.

<details>
<summary>Aufgabe 1.1</summary>

### Ordering als Funktionstyp und primitive Orderings

Als Erstes soll ein Ordering als Funktionstyp ausgedrückt werden. Ein Ordering ist eine Funk-
tion, die zwei Werte vom Typ A entgegennimmt und ein OrderResult zurückgibt. Also ei-
ne Funktion mit dem Typ (A, A)-> OrderResult. In Kotlin können wir einen Funktionsty-
pen mit dem Schlüsselwort typealias einen Namen geben (https://kotlinlang.org/docs/type-aliases.html).

So können wir ein Ordering als Funktion ausdrücken:
```
typealias Ordering <A> = (A, A) -> OrderResult
```
Die Alternative wäre, dass man überall den vollen Funktionstypen hinschreiben müsste, was
viel Schreibaufwand und Verbosität bedeutet.
Nun können wir ein primitives Int Ordering definieren, indem wir eine Funktion erzeugen, die
vom Typ Ordering<Int> ist:
```
val intOrd : Ordering <Int > = { left , right ->
if (left < right) OrderResult .Lower
else if (left > right) OrderResult .Higher
else OrderResult .Equal
}
```

Der Typ von intOrd ist zwar Ordering<Int>, allerdings ist Ordering<Int> nur ein Alias für den
Funktionstypen (Int, Int)-> OrderResult.

a) Implementieren Sie weitere primitive Orderings vom Typ Ordering<String>, Ordering<Double>
und Ordering<Boolean>.
</details>
<details>
<summary>Aufgabe 1.2</summary> 

### Funktionen höherer Ordnung auf Orderings

a) Da wir nun primitive Orderings haben, sollen Sie Funktionen mit Orderings definieren.
Jeder dieser Funktionen nimmt ein Ordering entgegen und gibt ein neues Ordering zurück:
+ reversed: Dreht das OrderResult des übergebenen Orderings um (vgl. ReversedOrdering).
+ debug: Gibt die zwei zu vergleichenden Elemente und das Ergebnis dessen auf der Konsole
aus (vgl. DebugOrdering).
+ none: Ignoriert das übergebene Ordering und gibt konstant Equal zurück.
Die Verwendung kann beispielsweise so aussehen:
```
fun main () {
val intDesc = reversed (intOrd )
val stringDebug = debug( stringOrd )
val doubleDesc = debug( reversed ( doubleOrd ))
val noOrd = none( stringOrd )
}
```

b) Beantworten Sie zudem folgende Fragen:
+ Warum sind reversed, debug und none Funktionen höherer Ordnung?

A: Dies ermöglicht es Ordering zu übergeben da Ordering eine Funktion ist. 
+ Welches Entwurfsmuster wurde durch die Verwendung von Funktionen höherer Ordnung
realisiert?

A: Das Entwurfsmuster welches implementiert wurde ist das Dekorierer Muster
+ Warum kann das Entwurfsmuster dadurch implementiert werden? Was ist die Grundle-
gende Struktur des Entwurfsmusters und inwiefern korreliert diese Struktur mit der von
Funktionen höherer Ordnung?

A: Das Entwurfsmuster erweitert den Input -> Man nutzt den Input und fügt Konstrukte hinzu um den Output zu erweitern. Das gleiche kann man mit einer Funktion höherer Ordnung machen, indem man die übergebene Funktion innerhalb der FHO aufruft und weitere sachen wie z.B. eine Print funktion auf den Output der übergebenen Funktion anwendet. 
</details>
<details>
<summary> Aufgabe 1.3</summary>

### Orderings mappen

Bislang haben wir Orderings auf primitive Typen wie Int, String, Double und Boolean definiert.
Nun wollen wir neue Orderings für eigene Typen, wie z.B. Personen, definieren. Allerdings soll
dies auf Grundlage bestehender Orderings passieren.
Das Ziel ist es, eine Funktion zu finden, die ein Ordering vom Typ A auf ein Ordering vom Typ
B überführt. Dieses Muster kennen wir als map Funktion, beispielsweise um von einer Liste von
A zu einer Liste von B zu mappen, indem man für jedes Element die Funktion A -> B anwendet:

```
fun <A, B> map(list: List <A>, transform : (A) -> B): List <B> {
val bs = mutableListOf <B >()
for (a in list) bs += transform (a)
return bs
}
```

In dieser Aufgabe wollen wir eine ebenfalls eine map Funktion für Orderings schreiben, die die
gleiche Signatur wie die map Funktion für Listen hat, aber mit Ordering arbeitet. Allerdings
muss die Struktur der transform Funktion umgedreht werden, nämlich von B nach A. Da
wir die transform Funktion umdrehen müssen, nennen wir die gesamte Funktion contraMap1.

a) Implementieren Sie die contraMap Funktion, die auf Grundlage eines bestehenden ```Ordering<A>```
mithilfe der transform Funktion (B) -> A ein neues ```Ordering<B>``` erzeugt.
Hier ein Beispiel für die Verwendung von contraMap, wo ein alternatives String Ordering de-
finiert wird, welches Strings nach ihrer Länge vergleicht. Da die Länge vom Typ Int ist, wird
das in Aufgabe 1.1 definierte Int Ordering als Grundlage verwendet. Die transform Funktion
beschreibt die entsprechende Überführung von String nach Int:

```
fun main () {
// Wir erzeugen ein neues String Ordering das auf Zeichenl änge
vergleicht ...
val stringLengthOrd : Ordering <String > = contraMap (
ord = intOrd , // indem wir das bestehende Int Ordering nehmen ...
transform = { string -> string .length } // und beschreiben , welcher
Int Wert die Grundlage bilden soll. In diesem Fall die Länge des
Strings
)
}
```

b) Definieren Sie die Klasse Person, die aus einem Namen und einem Alter besteht.

c) Verwenden Sie contraMap, um folgende Person Orderings zu erzeugen:
+ Vergleich von Personen nach Namen.
+ Vergleich von Personen nach Alter.

d) Erzeugen Sie ein paar Personen und testen Sie die Personen Orderings.
</details>
<details>
<summary>Aufgabe 1.4</summary>

### Orderings zippen

Als Nächstes implementieren wir eine Funktion, um zwei Orderings unterschiedlichen Typs
miteinander zu kombinieren. Auch hier schauen wir uns zuerst das Äquivalent zu Listen an.
Wenn wir eine ```List<A>``` mit einer ```List<B>``` kombinieren, erhalten wir eine neue Liste, die jeweils
ein Paar der nten Elemente beider Listen enthält. Die Operation heißt zip und sieht so aus:

```
fun main () {
val ints: List <Int > = listOf (25, 33, 28)
val strings : List <String > = listOf ("Emma", "Alex", "Zah")
val intsWithStrings : List <Pair <Int , String >> = ints.zip( strings )
}
intsWithStrings ist die Kombination beider Eingangslisten und hat folgenden Inhalt:
[
Pair (25, "Emma"),
Pair (33, "Alex"),
Pair (28, "Zah")
]
```

Im Kontext von Orderings bedeutet eine Kombination von zwei Orderings, dass zuerst nach
dem ersten Ordering verglichen wird. Erst wenn das erste Ordering gleich ist, wird nach
dem zweiten Ordering verglichen (vgl. CombineOrdering).

a) Implementieren Sie eine Funktion namens zip, die zwei Orderings unterschiedlichen
Typs entgegennimmt und ein Ordering<Pair<A, B>> zurückgibt. Die Implementierung soll der
zuvor genannten Semantik folgen. Damit können wir ein Ordering erzeugen, welches z.B.
zuerst nach String und danach nach Int vergleicht:
```
val ord: Ordering <Pair <String , Int >> = zip(stringOrd , intOrd )
```
Ein Pair von String und Int bringt uns in unserer Anwendung nicht viel. Glücklicherweise
haben wir mit contraMap die Möglichkeit, um von Pair<String, Int> auf Person zu mappen.

b) Erweitern Sie den oben gezeigten Code, sodass ord ein Ordering<Person> ist, welches zuerst
nach Namen und danach nach dem Alter vergleicht. So können wir Personen nach mehreren
Kriterien vergleichen:

```
fun main () {
val sorting = Sorting ()
val people = listOf (
Person ("Emma", 25) ,
Person ("Alex", 33) ,
Person ("Zah", 28) ,
Person ("Alex", 18) ,
Person ("Jens", 33) ,
)
val ord: Ordering <Person > = TODO(" contraMap (zip (...))")
println ( sorting .sort(people , ord))
}
```

Die Konsolenausgabe sieht folgendermaßen aus:

> Person(name=Alex , age =18)\
> Person(name=Alex , age =33)\
> Person(name=Jens , age =33)\
> Person(name=Emma , age =25)\
> Person(name=Zah , age =28)
</details>
<details>
<summary>Aufgabe 1.5</summary>

### Ergonomie verbessern

Schreiben Sie die Funktionen reversed, debug, contraMap und zip so um, dass sie als extension
functions auf dem jeweils ersten Parameter definiert sind (siehe https://kotlinlang.org/
docs/extensions.html). Erweitern Sie zudem die Klasse Person um eine weitere Eigenschaft,
wie z.B. height: Double, um ein weiteres Ordering zu verwenden. Der Code sollte jetzt in etwa
so aussehen:

```
data class Person (val name: String , val age: Int , val height : Double )
fun main () {
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
person .name to person .age to person .height // Syntaktischer
Zucker für Pair(Pair(person .name , person .age), person .height )
}
println ( sorting .sort(people , personOrd ))
}
```
</details>
</details>
<details>
<summary>Aufgabe 2</summary>

## Algebraische Datentypen

a) Welcher Typ in Kotlin ist äquivalent zur 1 in der Algebra?

A: Unit

b) Zeigen Sie, ob Either<A?, B> äquivalent bzw. isomorph zu Either<A, B>? ist. Achtung:
die '?' deuten auf nullfähige Typen hin!. Überführen Sie dazu die Typen in Algebra.

A: (a + 1) + b = (a + b) + 1
a + b + 1 = a + b + 1

c) Überführen Sie das Potenzgesetz a0 = 1 in Typen. Implementieren Sie auch die jeweilige
to- und from-Funktion. Die Funktionen sind:
```
fun <A> to(f: ( Nothing ) -> A): Unit = TODO ()
fun <A> from(unit: Unit): ( Nothing ) -> A = TODO ()
```
Bei der Implementierung von from dürfen Sie keine Exception werfen.

A: Nothing → A = Unit

d) Warum kann die from-Funktion implementiert werden, obwohl nur ein Nothing zur Verfügung steht, aber ein Wert vom Typ A zurückgegeben werden muss? Hinweis: Die Antwort liegt
im Subtyping-System von Kotlin.

A: Nothing ist ein Subtyp aller Oberklassen. Dies bedeutet das das returnen von {it} im fro für den Compiler korrekt ist aber an die Funktion nicht nutzen kann

e) Gegeben sei der Typ Either<A, B> und die Funktion makeEither, die aus einem A und B
ein Either<A, B> erzeugt:
```
sealed interface Either <out A, out B>
data class Left <A>( val a: A): Either <A, Nothing >
data class Right <B>( val b: B): Either <Nothing , B>
fun <A, B> makeEither (a: A, b: B): Either <A, B> = TODO ()
```
Implementieren Sie die makeEither Funktion, sodass der Code kompiliert.

f) Warum ist die Implementierung von makeEither nicht 100 %ig valide? Begründe Sie ihre
Antwort entweder mit der Verwendung von Algebra oder durch logische Argumente.

A: (A, B) → A + B = A + 0
(a + b) ^ (a * b) = a + 0 

Die Funktion kann nicht 100% valide sein, da sie nur einen Teil von Either zurückliefern kann

</details>

<details>
<summary>Aufgabe 3</summary>

##  Funktionale Programmierung

Ordnen Sie das gesamte Praktikumsblatt in das funktionale Paradigma ein.

a) Inwiefern werden die typischen Merkmale der funktionalen Programmierung erfüllt? Nen-
nen Sie auch hier ein paar Codestellen, das jeweilige Merkmal und ihre Begründung.

A: Funktionen wurden als Werte behandelt (OrderingFunctional.kt z 13)
Funktionen Höherer Ordnung wurden verwendet z 34
komposition und transformation durch verwendung von kombinatoren wie zip und contramap z 51
starke Typisierung durch orientirrung an algebraischen datentypen main z 5


b) Überlegen Sie für sich, welche Techniken und Denkweisen Sie aus der Bearbeitung des
Praktikumsblattes mitnehmen.

A: Ich habe vor allem gelernt wie man auch ohne klassen stark flexiblen code designen kann und auch eine andere sichtweise auf Daten, Datentypen und Funktionen kennengelernt.

</details>