/**
• Fields in classes automatically come with getters and setters.
• You can replace a field with a custom getter/setter without changing the
  client of a class—that is the “uniform access principle.”
• Use the @BeanProperty annotation to generate the JavaBeans getXxx/setXxx
methods.
• Every class has a primary constructor that is “interwoven” with the class
definition. Its parameters turn into the fields of the class. The primary
constructor executes all statements in the body of the class.
• Auxiliary constructors are optional. They are called this.
 */

//In Scala, a class is not declared as public. A Scala source file can contain multiple
//classes, and all of them have public visibility.
class Counter {
  private var value = 0 // You must initialize the field
  def increment(): Unit = { value += 1 } // Methods, Var and Val are public by default
  def current: Int = value
}

val myCounter = new Counter // Optional Round Bracket

myCounter.increment()
myCounter.current

// Scala provides getter and setter methods for every VAR field.
class Model {
  var attribute: Int = 0
}
var model = new Model
println(model.attribute) // Calls the method attribute(), in scala a parameter less method
model.attribute = -10 // Calls attribute_=(10)
model.attribute_=(20)
/**
 * NOTE: To see these methods with your own eyes, compile the Person class
 * and then look at the bytecode with javap:
 * $ scalac Model.scala
 * $ javap -private Model
 * Compiled from "Model.scala"
 * public class Model extends java.lang.Object{
 * private int attribute;
 * public int attribute();
 * public void attribute_$eq(int);
 * public Model();
 * }
 */
// TIP: You can run the javap command inside the REPL as :javap -private Model

// Custom Getter and Setter
class Model2 {
  private var privateAttribute: Int = 0
  def attribute: Int = privateAttribute * 10
  def attribute_=(newValue: Int): Unit = { privateAttribute = math.abs(newValue) }
}

// Note: The attribute was changed from val to def but
// it's still called like a val (Uniform Access Principle)
var model2 = new Model2
println(model2.attribute)
model2.attribute = -10
model2.attribute_=(-10)
println(model2.attribute)

/**
 * TIP: It may sound scary that Scala generates getter and setter methods for
 * every field. But you have some control over this process.
 * • If the field is private, the getter and setter are private.
 * • If the field is a val, only a getter is generated.
 * • If you don’t want any getter or setter, declare the field as private[this]
 * (see Section 5.4, “Object-Private Fields,” on page 60).
 */

/**
 * // Uniform Access Principle
 * val x = obj.something
 * Is something a field of the object or a computed value?
 */


//    In Scala (as well as in Java or C++), a method can access the private fields of all
//    objects of its class.
class Model3 {
  private var privateAttribute: Int = 0
  def method(otherObject: Model3): Unit = {
    // Other Object but same Class
    privateAttribute += otherObject.privateAttribute
  }
}
// Now it is impossible
class Model4 {

  // This access is sometimes called
  // object-private, and it is common in some OO languages such as SmallTalk.
  //  For an object-private field, no getters and setters are generated at all.
  private[this] var privateAttribute: Int = 0

  def method(otherObject: Model4): Unit = {
    //    Not Compile because this attribute is also private between objects of same class
    //    privateAttribute += otherObject.privateAttribute
    ()
  }
}

class SpecialClass {
  def method(otherObject: Model5): Unit = {
    println(otherObject.privateAttribute)
  }
}

class Model5 {
  // Grant access rights to specific classes.
  private[SpecialClass] var privateAttribute: Int = 0
}

import scala.beans.BeanProperty
import scala.runtime.Nothing$
class Person {
  // generate 4 methods (get, set, name, name_=)
  @BeanProperty var name: String = ""
}

// Auxiliary Constructor must invoke another constructor of the same class as its first action
class Model6 {
  private var strValue: String = "Start"
  private var intValue: Int = 10

  def this(strValue: String) { // Auxiliary Constructor
    this() // Call Primary Construct in first action
    this.strValue += " " + strValue
  }

  def this(strValue: String, intValue: Int) { // Auxiliary Constructor
    this(strValue) // Call Aux. Constructor in first action
    this.intValue += intValue
  }
}

/*
class ClassName private/public (Primary Constructor Parameters and/or Fields) {
  attribute = value // can be an constructor argument
  methods and functions

  // alternatives constructor
  def this() {
      this() // execute primary constructor first
  }
  def this() ...
}
*/
/**
 * The Primary Constructor is always and the first thing executed
 * when a new instance is created even when the programmer use Aux Constructor.
 */

class Model7 (
               // Primary Constructor Parameters and Class Field declarations
               // Default parameter values
               private var strValue: String = "str", // A caller can omit those parameters.
               private var intValue: Int = 10
             ) {
  // Primary Constructor body
  println(strValue)
  println(intValue)
}

// Primary Constructor Parameter without val and var
// strValue is not a filed!!! Only a argument visible in primary constructor body
class Model8(strValue: String = "str", intValue: Int = 10) { //
  //  strValue = "X" // It's a val, "Reassignment to Val"
  println(strValue)
  println(intValue)
  /**
   * If a parameter without val or var is used inside at least one method, it becomes
   * a val Object-Private Field
   */
  def testValObjectPrivate(model: Model8): Unit = {
    //     intValue = 10 // It's a val, "Reassignment to Val"
    println(model.intValue) // Not compile in Read–eval–print loop
  }

  // Now intValue is a field
  def method(x: Int): Int = {
    intValue + x
  }
}

//(new Model8).intValue // Note: intvalue is a Object-Private Field

// This compile but there are not get/set methods
class Model9(@BeanProperty strValue: String = "str", @BeanProperty intValue: Int = 10) {
}

/**
 * Private Constructor
 */
// This compile but it is impossible create any instance
class Model10 private {
  private def this(intValue: Int) = {
    this()
    print(intValue)
  }

  // Public Constructor
  //  def this(strValue: String) = {
  //    this(1)
  //    println(strValue)
  //  }
}

/**
 * Nested Classes
 * It is possible define functions inside other functions,
 * and classes inside other classes
 * Define a class within another class
 * class Enclosing Class { // Outer Class
 *    class NestedClass { // Inner Class
 *     }
 * }
 * A nested class is a member of its enclosing class
 */
import scala.collection.mutable.ArrayBuffer
class Sport1(name: String) {
  override def toString: String = name
  class Athlete1(val name: String, val born: Int) {
    val inspirations = new ArrayBuffer[Athlete1]
    override def toString: String = s"name: $name, born: $born"
  }

  val athletes = new ArrayBuffer[Athlete1]

  def addAthlete(name: String, born: Int): Athlete1 = {
    val athlete = new Athlete1(name, born)
    athletes += athlete
    athlete
  }
}

val football1 = new Sport1("Football")
val messi1 = football1.addAthlete("Messi", 1980)
val maradona1 = football1.addAthlete("Maradona", 1960)
val pele1 = football1.addAthlete("Pele", 1940)

val basketball1 = new Sport1("Basketball")
val jordan1 = basketball1.addAthlete("Michael Jordan", 1963)
val leBron1 = basketball1.addAthlete("LeBron James", 1984)

/**
 * In Scala, each instance of Sport has its own class Athlete,
 * just like each instance has its own field athletes.
 * That is, football.Athlete and basketball.Athlete are different classes.
 */
maradona1.inspirations += pele1
messi1.inspirations ++= Array(maradona1, pele1)

leBron1.inspirations += jordan1
//leBron1.inspirations += pele1 // pele instance is not of basketBall.Athlete
//football1.athletes += leBron1 // leBron is not a football player or football.Athlete

// Bellow a solution for leBron.inspirations += pele, Companion object
import scala.collection.mutable.ArrayBuffer
object Sport2 { // This is a companion object
  class Athlete2(val name: String, val born: Int) {
    val inspirations = new ArrayBuffer[Athlete2]
    override def toString: String = s"name: $name, born: $born"
  }
}
class Sport2(name: String) {
  override def toString: String = name
  val athletes = new ArrayBuffer[Sport2.Athlete2]  // CompanionObject.Class
  def addAthlete(name: String, born: Int): Sport2.Athlete2 = { // CompanionObject.Class
    val athlete = new Sport2.Athlete2(name, born) // CompanionObject.Class
    athletes += athlete
    athlete
  }
}
val football2 = new Sport2("Football")
val pele2 = football2.addAthlete("Pele", 1940)
val basketball2 = new Sport2("Basketball")
val jordan2 = basketball2.addAthlete("Michael Jordan", 1963)
val leBron2 = basketball2.addAthlete("LeBron James", 1984)

leBron2.inspirations += jordan2
leBron2.inspirations += pele2 // Now compile
football2.athletes += leBron2 // Compile but leBron is not a football player!

// An other problem, how to know the the sport of the players
// leBron2 What is your sport? inspiration from football? from your sport?


// Bellow a solution for leBron.inspirations += pele (Type Projection Sport3#Athlete3)
// and for whatIsYourSport...
import scala.collection.mutable.ArrayBuffer
class Sport3(name: String) { mySport => // This syntax makes the val outer refer to Sport3.this
  override def toString: String = name
  class Athlete3(val name: String, val born: Int) {
    //ArrayBuffer[All Class Athlete3 from Sport Instance
    val inspirations = new ArrayBuffer[Sport3#Athlete3]  //  “An Athlete of Any Sport.”
    override def toString: String = s"name: $name, born: $born"

    // mySport is an alias for Enclosing Class Instance
    def sport: Sport3 = mySport // Access the this reference of the enclosing class
    def inspirationsFrom(sport: Sport3) = for(athlete <- inspirations if athlete.sport == sport) yield athlete
    def inspirationsFromMySport = for(athlete <- inspirations if athlete.sport == mySport) yield athlete

  }
  val athletes: ArrayBuffer[Athlete3] = new ArrayBuffer[Athlete3]
  def addAthlete(name: String, born: Int): Athlete3 = {
    val athlete = new Athlete3(name, born)
    athletes += athlete
    athlete
  }
}
val football3 = new Sport3("Football")
val pele3 = football3.addAthlete("Pele", 1940)
val basketball3 = new Sport3("Basketball")
val jordan3 = basketball3.addAthlete("Michael Jordan", 1963)
val leBron3 = basketball3.addAthlete("LeBron James", 1984)

leBron3.inspirations += jordan3
leBron3.inspirations += pele3
//football3.athletes += leBron3 // Not Compile, leBron is not a football player!

leBron3.inspirations
leBron3.inspirationsFromMySport
leBron3.inspirationsFrom(football3)
leBron3.sport.athletes

// Bellow there are good examples

//1.

class Counter2 {
  private var value = (Int.MaxValue - 1)
  def increment(): Unit = { if(value < Int.MaxValue) value += 1 }
  def current: Int = value
}

val myCounter2 = new Counter2

myCounter2.increment()
myCounter2.current
myCounter2.increment()
myCounter2.current

//2.
class BankAccount(var balance: Double) {
  //  Read-Only Property with get
  def getBalance: Double = {
    balance
  }

  def deposit(value: Double) = {
    balance += value
  }

  def withDraw(value: Double) = {
    balance -= value
  }
}

//3.

object Time {
  def apply(hours: Int, minutes: Int): Time = new Time(hours, minutes)
}
class Time(_hours: Int, _minutes: Int) {
  // Validate Primary Constructor Arguments
  require(hours >= 0 && hours <= 23) // Throw a IllegalArgumentException if condition is false
  require(minutes >= 0 && minutes <= 59, "Minute must be between 0 and 59")

  //  Read-Only Property with "uniform access principle.”
  def hours = _hours
  def minutes = _minutes
  override def toString: String = hours.formatted("%02d") + ":" + minutes.formatted("%02d")
  def before(other: Time): Boolean =  {
    other match {
      case t: Time if this.hours < other.hours => true
      case t: Time if this.hours == other.hours && this.minutes < other.minutes => true
      case _ => false
    }
  }
  def after(other: Time) = other before this
  def same(other: Time) = !(this before other) && !(this after other) // Stupid but funny code
  //  def same(other: Time) = this.hours = other.hours && this.minutes == other.minutes
}
assert(Time(11, 59) before Time(12, 0))
assert(Time(12, 1) after Time(12, 0))
assert(Time(12, 0) same Time(12, 0))

//6.

class Person (name: String, _age: Int = 0)  {
  val age: Int = if(_age >= 0) _age else 0
}

class PrimaryConstructor
object thisIsAnPrimaryConstructor { def apply() = new PrimaryConstructor}

class Person private (primaryConstructor: PrimaryConstructor, _name: String, _age: Int)  {
  val age: Int = _age
  def this(name: String, age: Int) = {
    this(thisIsAnPrimaryConstructor(), "", 1)
  }
}

object Person {
  var x = 0
  def nextDefaultName(): String = {
    val stringBuilder = new StringBuilder
    lazy val randomNames: Int => Unit = {
      case value if value <= 26 => stringBuilder.addOne((value + 64).toChar)
      case value => { randomNames((value - 1) / 26); randomNames((value - 1) % 26 + 1); }
    }
    x += 1
    randomNames.apply(x)
    stringBuilder.toString
  }
  def externalMethod = 0
}
class Person private (primaryConstructor: Unit, _name: String, _age: Int)  {
  val name: String = _name
  val age: Int = _age
  def this(name: String = Person.nextDefaultName(), age: Int = 0) = {
    // Primary Constructor is the first statement in all Auxiliary Constructor
    // because each Aux-Constructor must start with a call to a previously defined
    // Aux. or the Primary. Even if an Aux call other Aux in the end it call an Primary.
    // But... there is an alternative. Check bellow: code block, anonymous function,
    this(
      { def method = (); method; }, // Argument to differentiate Primary and Auxiliary Constructor
      (() => { name.toUpperCase }).apply(), // It is possible create and execute an anonymous function or method before the call to Primary
      {
        //        val Zero = this.internalMethod // It is not possible call class methods/val/var because the object was not created.
        val Zero = Person.externalMethod // Code Block, It is possible call external methods / function
        if (age >= Zero) age else Zero
      }
    )
  }
  lazy val internalMethod = 0
}

//7.

// indexWhere, (take, drop)
class Person1(name: String) {
  val (firstName, lastName) = {
    val firstWhiteSpace = name.indexWhere(_ == ' ');
    (name.take(firstWhiteSpace), name.drop(firstWhiteSpace + 1))
  }
}

// indexWhere, splitAt, tail
class Person2(name: String) {
  val (firstName, lastName) = {
    val (a, b) = name.splitAt(name.indexWhere(_ == ' '))
    (a, b.tail)
  }
}
// indexWhere, splitAt, tail (Pattern Matching Version)
class Person2PatternMatching(name: String) {
  val (firstName, lastName) = {
    name.splitAt(name.indexWhere(_ == ' ')) match { case (a, b) => (a , b.tail) }
  }
}

// indexWhere, slice, slice
class Person3(name: String) {
  val (firstName, lastName) = {
    val i = name.indexWhere(_ == ' ')
    (name.slice(0, i), name.slice(i + 1, name.length))
  }
}

// split, Array to Tuple2
class Person4(name: String) {
  val (firstName, lastName) = {
    val x = name.split("\\s+", 2)
    (x(0), x(1))
  }
}

class Person5(name: String) {
  val (firstName, lastName) = {
    val arrayName = name.toArray
    var len = arrayName.length
    var i = 0
    while(i < len) {
      if(arrayName.charAt(i) == ' ') {
        i -= 1
        len = i
      }
      i += 1
    }
    val firstName = new Array[Char](i)
    System.arraycopy(arrayName, 0, firstName, 0, i);
    val lastName = new Array[Char](name.length - i - 1)
    System.arraycopy(arrayName, i + 1, lastName, 0, name.length - i - 1);
    (firstName.mkString, lastName.mkString)
  }
}

val name = "Pedro Álvares Cabral"
var firstName, lastName = ""

val person1 = new Person1(name)
firstName = person1.firstName
lastName = person1.lastName
assert(firstName == "Pedro" && lastName == "Álvares Cabral")

val person2 = new Person2(name)
firstName = person2.firstName
lastName = person2.lastName
assert(firstName == "Pedro" && lastName == "Álvares Cabral")

val person2Match = new Person2PatternMatching(name)
firstName = person2Match.firstName
lastName = person2Match.lastName
assert(firstName == "Pedro" && lastName == "Álvares Cabral")

val person3 = new Person3(name)
firstName = person3.firstName
lastName = person3.lastName
assert(firstName == "Pedro" && lastName == "Álvares Cabral")

val person4 = new Person4(name)
firstName = person4.firstName
lastName = person4.lastName
assert(firstName == "Pedro" && lastName == "Álvares Cabral")

val person5 = new Person5(name)
firstName = person5.firstName
lastName = person5.lastName
assert(firstName == "Pedro" && lastName == "Álvares Cabral")

//8.

// Four Constructor, All require manufacturer and name. Default: year = -1, plate = empty String

class Car(val manufacturer: String, val modelName: String,
          val modelYear: Int = -1, var licensePlate: String = "")  {
}

class CarLongVersion(val manufacturer: String, val modelName: String,
          val modelYear: Int, var licensePlate: String)  {

  def this(manufacturer: String, modelName: String) = {
    this(manufacturer, modelName, -1, "")
  }

  def this(manufacturer: String, modelName: String, modelYear: Int) = {
    this(manufacturer, modelName, modelYear, "")
  }

  def this(manufacturer: String, modelName: String, licensePlate: String) = {
    this(manufacturer, modelName, -1, licensePlate)
  }
}

val car1 = new Car("Man", "Name")
val car2 = new Car("Man", "Name", 2000)
val car3 = new Car("Man", "Name", licensePlate="Plate")
var car4 = new Car("Man", "Name", 2000, "Plate")

val carLongVersion1 = new CarLongVersion("Man", "Name")
val carLongVersion2 = new CarLongVersion("Man", "Name", 2000)
val carLongVersion3 = new CarLongVersion("Man", "Name", "Plate")
var carLongVersion4 = new CarLongVersion("Man", "Name", 2000, "Plate")

assert(car1.manufacturer == "Man")
assert(car1.modelName == "Name")
assert(car1.modelYear == -1)
assert(car1.licensePlate == "")

assert(car2.manufacturer == "Man") 
assert(car2.modelName == "Name")   
assert(car2.modelYear == 2000)
assert(car2.licensePlate == "")

assert(car3.manufacturer == "Man") 
assert(car3.modelName == "Name")   
assert(car3.modelYear == -1)
assert(car3.licensePlate == "Plate")

assert(car4.manufacturer == "Man") 
assert(car4.modelName == "Name")   
assert(car4.modelYear == 2000)
assert(car4.licensePlate == "Plate")

assert(carLongVersion1.manufacturer == "Man") 
assert(carLongVersion1.modelName == "Name")   
assert(carLongVersion1.modelYear == -1)
assert(carLongVersion1.licensePlate == "")
                                   
assert(carLongVersion2.manufacturer == "Man") 
assert(carLongVersion2.modelName == "Name")   
assert(carLongVersion2.modelYear == 2000)
assert(carLongVersion2.licensePlate == "")
                                   
assert(carLongVersion3.manufacturer == "Man") 
assert(carLongVersion3.modelName == "Name")   
assert(carLongVersion3.modelYear == -1)
assert(carLongVersion3.licensePlate == "Plate")
                                   
assert(carLongVersion4.manufacturer == "Man") 
assert(carLongVersion4.modelName == "Name")   
assert(carLongVersion4.modelYear == 2000)
assert(carLongVersion4.licensePlate == "Plate")

//10.

class Employee1 private (val name: String, var salary: Double) {
  def this() = { this("John Q. Public", 0.0) }
}
class Employee2 {
  val name: String = "John Q. Public"
  var salary: Double = 0.0
}

// Or two argument or No Arguments
class Employee3 (val name: String, var salary: Double) {
  def this() = { this("John Q. Public", 0.0) }
}

// All possibility 
class Employee4 (val name: String = "John Q. Public", var salary: Double = 0.0) {
}
