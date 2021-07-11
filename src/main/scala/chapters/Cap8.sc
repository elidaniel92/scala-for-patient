import java.lang.NullPointerException

/**
 * A class can be extended by another class or an object
 * A final class can not be extended
 * Use override modify to override def, val and lazy val
 * super keyword to access superclass
 */

// AnyVal, Unit, Null and Nothing has different behavior.
// obj.isInstanceOf[Type] = Pattern Matching = an object is instance of (directly or indirectly) Type[Class, Trait]
// obj.getClass = return a Class
// classOf[Type]
/** Note: AnyVal cannot be used in pattern matching and isInstanceOf[AnyVaL] method */

//final class Dog {
class Dog {
  val breed: String = "Mutt"
  def getMessage: String = "Mutt Dog"
  lazy val test = "test"
}

// Overriding val and def

class PitBull extends Dog {
  // Overriding
  override val breed: String = "PitBull"
  val aggressiveness: String = "High"
  override lazy val test = "test1"
}
object MyDog extends Dog {
  val name: String = "Toby"
  // Overriding
  override def getMessage: String = s"$name is an ${super.getMessage}"
}

// Type Checks and Casts

def isDog(obj: Any): Boolean = obj.isInstanceOf[Dog]
// PatternMatching Version
val isDogPM: Any => Boolean = {
  case _: Dog => true
  case _ => false
}

val obj1 = new PitBull
assert(isDog(obj1) && isDogPM(obj1))

// Note: if obj is null, then returns false
val obj2: PitBull = null
assert(!isDog(obj2) && !isDogPM(obj2))
val obj3: Dog = null // even if is a null dog instance
assert(!isDog(obj3) && !isDogPM(obj3))

val obj4: Dog = new Dog

assert(obj1.getClass != classOf[Dog])
assert(obj4.getClass == classOf[Dog])

// singleton object
assert(MyDog.isInstanceOf[Dog])

// Unit, Null, Nothing
().getClass // It works
lazy val nullGetClass = null.getClass // Compile, throw a NullPointerException
lazy val nothingGetClass = (throw new Throwable).getClass // Compile

// All works
classOf[Unit]
classOf[Null]
classOf[Nothing]

().isInstanceOf[Unit]
// lazy val unitIsInstanceOfString = ().isInstanceOf[String] // Does not compile,
"".isInstanceOf[Unit]

// lazy val nullIsNull = null.isInstanceOf[Null] // Does not compile, type pattern or isInstanceOf
null.isInstanceOf[String]
// lazy val strIsNull = "".isInstanceOf[Null] // Does not compile, type pattern or isInstanceOf

//lazy val strIsNothing = "".isInstanceOf[Nothing] // Does not compile, type pattern or isInstanceOf
lazy val nothingIsUnit = (throw new Throwable).isInstanceOf[Unit]

val isUnit: Any => Boolean = {
  case _: Unit => true
  case _ => false
}

/** Note: Pattern matching == isInstanceOf[] */
//Does not compile, because type Null and Nothing cannot be used in type pattern or isInstanceOf
//val isNull: Any => Boolean = {
//  case _: Null => true
//  case _ => false
//}
//val isNothing: Any => Boolean = {
//  case _: Nothing => true
//  case _ => false
//}

/**
 * Conclusion about Unit, Null and Nothing
 * - classOf[Type] works for all
 * - .getClass works in runtime only for (), for null and nothing value works only in compilation time
 * - null value has isInstanceOf method and the Type Unit can be used in type pattern or isInstanceOf
 */
null.isInstanceOf[Unit]
lazy val nothingValueIsUnit = (throw new Throwable).isInstanceOf[Unit]

/*
  Correspondence between Scala and Java
  type checks and cast

  obj.isInstanceOf[Cl]  |  obj instanceof Cl
  obj.asInstanceOf[Cl]  |  (Cl) obj
  classOf[Cl]           |  Cl.class
*/

// protected[] are similar to the private[]

// Calling a superclass constructor
class Foo(val value: String)
object DefaultFoo extends Foo("Default")

/**
 * It is possible override a parameter less def to val (not var) but not the other way around.
 */
class Bar {
  def method1(arg: String = "arg") = "method with parameter: " + arg
  def method2() = "parameter less but with parentheses"
  def method3 = "no parentheses"
  def method4 = "method4"
  val value = "A"
}

// Note: override a method to val in primary constructor parameter
class Baz(override val method4: String = "val4 default") extends Bar {
  val method1 = "val1" // Not override
//  def value = "B" // Not possible overrides to def
//  val method2 = "val2" // Not possible, parameter less
//  val method3 = "val3" // Not possible, parameter less
//  override var method2 = "val2" // Not possible overrides to var
  override val method2 = "val2"
  override val method3 = "val3"
}

val bazInstance = new Baz
bazInstance.method1 // Call val
bazInstance.method1()  // Call method
bazInstance.method2 // Call val
//bazInstance.method2() // Call a method doesn't work
bazInstance.method3 // Call val
bazInstance.method4 // Call val

def callingABarDef(bar: Bar): Unit = println(bar.method2())

callingABarDef(bazInstance) // Calling a method2 def that was overrides by a val

abstract class Person {
  def id: Int // Each person has an ID that is computed in some way
}

// Anonymous Class

// AnyRef similar java.lang.Object
val an1 = new AnyRef {
  def methodX = "Single Instance from an Anonymous Class"
}
val an2 = new AnyRef {
  def methodX = "Single Instance from an Anonymous Class"
}
// Different classes
assert(an1.getClass != an2.getClass)

/**
 * Structural Type
 * Note: the parameter type is all AnyRef Classes
 * that has a "def methodX: String"
 */
def structuralType(anonymous: AnyRef { def methodX: String }) {
  println(anonymous.methodX)
}
structuralType(an1)

object ObjMethodX {
  def methodX = "I have a method X!"
}
structuralType(ObjMethodX)

/**
 * In Scala, unlike Java, you do not use the abstract keyword for an abstract method.
 * You simply omit its body.
 * Only Trait and Abstract Class can have a method without body.
*/

/**
 * Override Keyword only in reimplemented methods
 */
abstract class SuperClass {
  def method: String
}
class SubAbstrClass extends SuperClass {
  // Implementation method without override keyword
  def method: String = "Implementation"
}

class NormalClass {
  def method: String = "Implementation"
}
class SubNormClass extends NormalClass {
  // Need override keyword because the method was reimplemented
  override def method: String = "Reimplementation"
}

// Abstract Field
abstract class Person {
  val id: Int
  var name: String
}
// The abstract fields needs a value
// (literal value or an constructor argument).
class Employee(val id: Int) {
  var name: String = ""
}

// Anonymous Class that extend Person
// It is similar: val fred = new Person(1729, "Fred")
val fred = new Person { val id = 1729; var name = "Fred" }


/**
 * When you override a val in a subclass and use the value in a superclass constructor,
 * the resulting behavior is unintuitive.
 */
class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}
class Ant extends Creature {
  override val range = 2
}
val ant = new Ant
// length == 0
assert(ant.env.length != 2 && ant.env.length != 10 && ant.env.length == 0)

/**
 * Solution: Early Definition Syntax
 * The syntax is horrible
 * Early Initializers are deprecated
 */
// TIP: You can debug construction order problems with the -Xcheckinit compiler flag
class Ant2 extends { override val range = 2 } with Creature
val ant2 = new Ant2
ant2.env.length

String

Unit

//Inheritance Hierarchy
/*

* Both AnyVal and AnyRef extend the Any class, the root of the hierarchy.
* AnyRef = "Java Object", Reference Type
  Note: there is boxing and unboxing to the JVM’s primitive types as needed,
  therefore primitive types could be also Java Object
* AnyVal = primitive types and Unit
* There are Universal Trait for special cases

* A method with return type Unit is analogous to a Java method which is declared void.
  There is only one value of type Unit, ()
  Unit cannot be extended (Nothing is an exception).
  val u: Unit = null // Compile but "u = ()"

* Null is the type whose sole instance is the value null.
  Null is subtype of all AnyRef types and Universal Traits (not AnyVal).
  Not recommended to be used.
  Null cannot be extended (Nothing is an exception).
  Although Null is subtype of AnyRef (and any common class), isInstanceOf[AnyRef] return false


* The Nothing type has no instances.
  Nothing is subtypes of all types.
  The throw expressions are of Nothing type but never returns Nothing instances.
  Any is the root Nothing is the leaf of a tree.
*/

// Receive AnyVal and AnyRef
def method(any: Any): Unit = {
  println(any.getClass)
}
method(1) // Int is AnyVal
method(new AnyRef {}) // An anonymous class
method(()) // Unit is AnyVal
method(null) // Runtime Exception

def method2(anyVal: AnyVal): String = {
  anyVal.toString
}
method2(1)
method2(()) // Unit is AnyVal

// In scala an object can be null
val emptyObject: Null = null
var someAnyRef = new AnyRef { }
someAnyRef = emptyObject

/**
 * The Nothing type is REALLY NOTHING therefore there is no instances of Nothing.
 * In Scala throw is also an expression.
 * Throw has a return type of Nothing but never return a value because
 * there is no value for Nothing type and an exception is thrown.
 */

// Nothing is Inferred because it will never return a value.
def NothingTypeIsInferred = throw new Throwable
lazy val throwType: Nothing = throw new Throwable
// The ??? method (return type Nothing) throws NotImplementedError when it is invoked.
// Can be used in methods that was not implemented yet.

// Nothing is used in a empty collection, see the examples bellow

val arrayOfUnits = Array[Unit]((), (), ()) // 3 elements
val arrayOfNull = Array[Null](null, null, null) // 3 elements
val arrayOfNothing = new Array[Nothing](0) // It is impossible to have elements

val arrayOfAnyVal = Array[AnyVal](1, 2.0d, 'C', ()) // Unit is a AnyVal subtype
val arrayOfString = Array[String]("AB", "CD", null) // Null is a subtype of all AnyRef

def getInts = Array[Int](1, 2, ???) // Compile because Nothing is subtype of all Types

val emptyList: List[Nothing] = List.empty
// Now the type is Double because Nothing is a subtype of Double
val intList: List[Double] = emptyList.appended(2.0d)

// Note: () has implemented toString method in contrast null throw an Runtime Exception
().toString
def nullToString = null.toString
// Both can be print
print(())
print(null) // There is a conditional for null values

/**
 * Equality
 * eq, ne, equals, ==, !=
 */
//----------------------------------------------------------------------------------------------------------------------
/**
 * - eq and ne keyword is used for Referential Equality in AnyRef types (Universal Trait too)
 * - eq/ne are not methods so it is null safe.
 * - Note: In some cases, there is Object Pool, examples String literal and Integer.valueOf(-128/127),
 * so eq will return true for equals values because they are the same References.
 * - There is a stranger behavior, it can be possible use eq/ne for primitives types (except Unit).
 */

// String Literal use Java String Pool
var str1: String = "A"
var str2 = "A"
assert(str1 eq str2) // Same Reference
str1 = String.valueOf("A")
str2 = String.valueOf("A")
assert(str1 eq str2) // Same Reference

// Bellow does not use Pool
str1 = new String("A")
str2 = new String("A")
assert(str1 ne str2) // Not Same Reference
str1 = String.valueOf(str1.toString) // String.valueOf only work for literal String
str2 = String.valueOf(str2.toString)
assert(str1 ne str2) // Not Same Reference
str1 = 'A'.toString
str2 = 'A'.toString
assert(str1 ne str2) // Not Same Reference

// "This method will always cache values in the range -128 to 127,
// inclusive, and may cache other values outside of this range." by Integer.java
var integer1 = Integer.valueOf(1)
var integer2 = Integer.valueOf(1)
assert(integer1 eq integer2)
integer1 = Integer.valueOf(128)
integer2 = Integer.valueOf(128)
assert(integer1 ne integer2)
var literalObjectInt1: Integer = 1 // Integer.valueOf(1)
var literalObjectInt2: Integer = 1 // Integer.valueOf(1)
assert(literalObjectInt1 eq literalObjectInt2)

// Not Same Reference
integer1 = new Integer(1)
integer2 = new Integer(1)
assert(integer1 ne integer2)

val i: Int = 1
assert(i ne new Object) // Compile and Execute
//new Object eq i // Doesn't compile

/**
 *  - The trait Any has the abstract method equals: def equals(that: Any): Boolean
 *  Although the method is abstract it is not mandatory to implement because
 *  equals method is already implemented by java.lang.Object.
 *  - equals method by default is like eq, in which case each instance of the class is equal only to itself
 *  - equals can be override (reimplemented) to implement a notion of logical equality
 *  - equals and hashcode need to be implement together.
 */
trait Anything extends Any {
  // The method is already implemented
//  def equals(obj: Any): Boolean = { println(" override "); super.equals(obj) };  // override` modifier required
}
class Something(val value: Int) extends Anything { }
val one = new Something(1)
val two = new Something(2)
one.equals(two)

// AnyVal, Int, Rich does not implement equals but it is possible...
val integerOne: Int = 1
val integerTwo: Int = 2
integerOne.equals(integerTwo)

/**
 * The expression `x == that` is equivalent to `if (x eq null) that eq null else x.equals(that)`.
 * In other words, == is a null safe version of equals for AnyRef and Universal trait.
 */
val jObjNull: java.lang.Object = null
val sObjNull: AnyRef = null
val uniTraitNull: Something = null
assert(jObjNull == sObjNull && sObjNull == jObjNull && sObjNull == uniTraitNull && uniTraitNull == sObjNull)
sObjNull equals jObjNull
//----------------------------------------------------------------------------------------------------------------------

/**
 * Equals and Hashcode implementation
 *
 * ## is a null safe verison of hashCode
 */

class Item(val description: String, var price: Double) {
  /**
   * NOTE: We defined the method as final because
   * it is generally very difficult to correctly extend equality in a subclass.
   * The problem is symmetry.
   * You want a.equals(b) to have the same result as b.equals(a),
   * even when b belongs to a subclass.
   */
  final override def equals(obj: Any): Boolean = obj match {
    case that: Item => description == that.description// Do not use equals! Equals will throw NPE for null description
    case _ => false
  }
  final override def hashCode(): Int = description.## // null safe version of hashCode
}
val itemA = new Item("Ball", 50.0d)
val itemB = new Item("Gloves", 100.0d)
val itemC = new Item("Ball", 50.0d)
val itemNull: Item = null
val notItem = new AnyRef {}

val itemWithNullValue = new Item(null, 0) // Does not compile in REPL because it call hashCode method

assert(!itemA.equals(itemB) && itemA.equals(itemC) && !itemA.equals(itemNull) && !itemA.equals(notItem))
assert(!(itemA == itemB) && itemA == itemC && !(itemA == itemNull) && !(itemA == notItem) && (itemA != itemB))

// equals vs == NullPointerException
itemNull.equals(itemA) // NullPointerException
itemNull == itemA // Null Safe version of equals
itemNull == { val str: String = null; str; } // itemNull is equal to any object null
assert(!itemWithNullValue.equals(itemA) && !(itemWithNullValue == itemA)) // NPE will be not throw because equals was implemented with ==
assert(itemWithNullValue == new Item(null, 100)) // item with a null hash is equal to item with a null hash
assert(itemWithNullValue != itemNull) // item with a null hash is not equal to item null (null reference)

// hashCode vs ## NullPointerException
assert(itemNull.hashCode == 0) // NullPointerException
assert(itemNull.## == 0) // Null Safe version of hashCode
assert(itemWithNullValue.hashCode == 0) // NPE will be not throw because hashCode was implemented with ## instead hashCode

// Referential Equality: Operators eq and ne
val itemD = itemA
assert(itemA.eq(itemD) && itemA.ne(itemC))

/**
 * Joshua Bloch in his book Effective Java, 3rd edition,
 * p. 53 discourages usage of Objects.hash(...) if performance is critical.
 * Primitives are being autoboxed and there is a penalty of creating an Object array.*
 * Check: AutoValue framework, Guava’s com.google.common.hash.Hashing [Guava]
 */

class Car(val model: String, val modelYear: Int, var price: Double) {
  final override def equals(obj: Any): Boolean = obj match {
    case that: Car => that.model == model && that.modelYear == modelYear
    case _ => false
  }

  // Using Standard Library
//  final override def hashCode(): Int = java.util.Objects.hash(model, modelYear)

  // Effective Java
//  final override def hashCode(): Int = {
//      var result: Int = model.hashCode
//      result = result * 31 + modelYear.hashCode
//      result
//  }
  final override def hashCode(): Int = (model.##, modelYear.##).##
}

val car1 = new Car("A", 1990, 2000.0)
val car2 = new Car("A", 2000, 10000.0)
val car3 = new Car("A", 1990, 2000.0)
assert(car1 != car2 && car1.## != car2.## && car1 == car3 && car1.## == car3.##)

/**
 * A value class has these properties:
 * 1. The class extends AnyVal.
 * 2. Its primary constructor has exactly one parameter, which is a val, and no
 * body.
 * 3. The class has no other fields or constructors.
 * 4. The automatically provided equals and hashCode methods compare and hash
 * the underlying value.
 */

// Example: Int as a 24-hour clock
class Time24 private (val time: Int) extends AnyVal {
  def minutes: Int = time % 100
  def hours: Int = time / 100
  override def toString: String = s"${hours.formatted("%02d")}:${minutes.formatted("%02d")}"
  def +(time24: Time24): Time24 = {
    val minutesSum = time24.minutes + minutes
    Time24(((minutesSum / 60 + hours + time24.hours) % 24 * 100) + minutesSum % 60)
  }
  def *(factor: Int): Time24 = {
    val minutesFactor = minutes * factor
    Time24((minutesFactor / 60 + (hours * factor)) % 24 * 100 + minutesFactor % 60)
  }
}
object Time24 {
  def apply(t: Int): Time24 = if (0 <= t && t < 2400 && t % 100 < 60) new Time24(t) else throw new IllegalArgumentException
}

implicit def intToTime24(i: Int): Time24 = Time24(i)

