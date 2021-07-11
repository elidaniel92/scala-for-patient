import java.time.temporal.TemporalAmount

import PlayingCardSuit.Value
// One object is a class with a single instance
// A single instance of a class

object Accounts {
  // The constructor of an object is executed when the object is first used.
  // If an object is never used, its constructor is not executed;
  println("Primary Constructor")
  private var lastNumber = 0
  def newUniqueNumber() = { lastNumber += 1; lastNumber }
}

// In Java or C++, you often have a class with both instance methods and static methods.
// In Scala, you can achieve this by having a class and a “companion” object of the same name.

class Account {
  val id = Account.newUniqueNumber() // Call object companion method
  private var balance = 0.0
  def deposit(amount: Double) { balance += amount max 0 } // Instance method
  def withDraw(amount: Double) { balance -= amount max 0 }
}
object Account { // The companion object
  private var lastNumber = 0
  private def newUniqueNumber() = { lastNumber += 1; lastNumber } // Static method
}
// The class and its companion object can access each other's private features.
// They must be located in the same source file.


//An object can extend a class and/or one or more traits.
abstract class UndoableAction(val description: String) {
  def undo(): Unit
  def redo(): Unit
}

// A useful default is the “do nothing” action.
object DoNothingAction extends UndoableAction("Do nothing") {
  override def undo() {}
  override def redo() {}
}

// An object is the only instance of a class (singleton).
// This class can extends class, traits etc.
class Foo(val bar: String) { }

// Note: If a implemented class has a constructor it is necessary to give arguments.
object FooBar extends Foo("bar value") { }

//Typically, an apply method returns an object of the companion class.
object Person {
  def apply(firstName: String, lastName: String): Person = new Person(firstName, lastName)
}
class Person(firstName: String, lastName: String) {
  def fullName = firstName + " " + lastName
}
val applyMethod: Person = Person("Lionel", "Messi")
val newClass: Person = new Person("Lionel", "Messi")
// Why doesn’t one just use a constructor?
// Not having the new keyword is handy for nested expressions, such as Array(Array(1, 7), Array(2, 9))


// Note that now the class don't call object companion method
object Account2 {
  private var lastNumber = 0
  // Code Block instead newUniqueNumber method
  def apply(initialBalance: Double): Account2 = new Account2({ lastNumber += 1; lastNumber }, initialBalance)
}
class Account2 private(val id: Int, initialBalance: Double) {
  private var balance = initialBalance
  def deposit(amount: Double) = { balance += amount max 0 } // Instance method
  def withDraw(amount: Double) = { balance -= amount max 0 }
  def info = s"Id: $id Balance: $balance"
}

val account2 = Account2(100)
//new Account2(1, 100) // Impossible because primary constructor is private

/**
 * Scala Program
 */

object Counter {
  private var count = 1
  def intValue = { count += 1; count}
}

// Normal behavior: constructor of an object is executed (only once) when the object is first used.
object Program1 {
  val intValue = { println("Constructor Executed"); Counter.intValue }
  def method = 100

  def main(args: Array[String]) = {
    println("Hello World!")
  }
}

object Program2 extends App {
  val intValue = { println("Constructor Executed"); Counter.intValue }
  def method = 100
  println("Hello World!")
}

// Only once
Program1.intValue // Constructor Executed (if call it first)
Program1.method // Constructor Executed (if call it first)
Program1.main(null) // Constructor Executed (if call it first)

// Stranger Behavior
Program2.intValue // Constructor not Executed (Not print). Return zero if constructor is never executed.
Program2.method // Constructor not Executed (Not print)
Program2.main(null) // Constructor Executed every time main method is executed. Change the intValue in each execution


// Scala does not have enumerated types but the standard library provides an Enumeration helper class

object TrafficLightColor extends Enumeration {
  val Red, Yellow, Green = Value // Each color is a Value that contain id and name
}

TrafficLightColor.Red.id // Id 0
TrafficLightColor.Red.toString // Name Red (own field name)

object TrafficLightColor2 extends Enumeration {
//  Custom id and name
  val Red = Value(0, "Stop") // Id and Name
  val Yellow = Value(10) // Id
  val Green = Value("Go") // Name
}

// toString method return name
def method(light: TrafficLightColor2.Value) = {
  println(light.toString + ": " + light.id)
}

// Look up Enumeration Value by id or name
TrafficLightColor2.apply(10)
TrafficLightColor2.withName("Yellow")

import TrafficLightColor._
method(TrafficLightColor2.Red)


// Alias Example
object TrafficLightColor3 extends Enumeration {
  //  Custom id and name
  type TrafficLightColor3 = Value // Alias
  val Red = Value(0, "Stop") // Id and Name
  val Yellow = Value(10) // Id
  val Green = Value("Go") // Name
}
import TrafficLightColor3._
def method3(light: TrafficLightColor3) = {
  println(light.toString)
}
method3(TrafficLightColor3.Red)

//1.

// Similar a Java Class Static
object Conversions {
  def inchesToCentimeters(inches: Double) = inches * 2.54
  def gallonsToLiters(gallons: Double) = gallons * 3.785
  def milesToKilometers(miles: Double) = miles * 1.609
}

//2.

// more object-oriented
abstract class UnitConversion { def apply(unit: Double): Double }

// For singleton use object 
object InchesToCentimeters extends UnitConversion { def apply(inches: Double) = inches * 2.54 }
// Anonymous Class alternative
val GallonsToLiters = new UnitConversion { override def apply(gallons: Double) = gallons * 3.785 }
// SAM alternative
val MilesToKilometers: UnitConversion = (miles: Double) => miles * 1.609

//3.
object Origin extends java.awt.Point {
  
}

//4.
// Understand Super Constructor
class Foo2(val str: String) {
  def this(x: Int) = this(x.toString)
  def this(y: Double) = this(y.toString)
}

/**
 * When you define a subclass in Scala,
 * you need to specify the (only one) superclass constructor that will always be called
 * by its primary constructor.
 * Therefore in Scala a subclass just call one superclass constructor.
 * Only the primary constructor can call the superclass constructor.
 */

class Bar2(str: String) extends Foo2(str) {
  def this(x: Int) = this("")
}
class Point (x: Int = 0, y: Int = 0) extends java.awt.Point(x,y) { }
object Point {
  def apply(x: Int, y: Int): Point = new Point(x, y)
}

//5.
object Five extends App {
  args.reverse.foreach((x) => { print(x); print(" "); })
}

//6.
object PlayingCardSuit extends Enumeration {
  val clubs = Value("♣")
  val diamonds = Value("♦")
  val hearts = Value("♥")
  val spades = Value("♠")
}

//7.
def isRed(playingCardSuit: PlayingCardSuit.Value): Boolean = {
  (playingCardSuit.eq(PlayingCardSuit.diamonds) || playingCardSuit.eq(PlayingCardSuit.hearts))
}

//8.

object RGB extends Enumeration {
  val red = Value(0xff0000, "Red")
  val green = Value(0x00ff00, "Green")
  val blue = Value(0x0000ff, "Blue")

  val black = Value(0x000000, "Black")
  val white = Value(0xffffff, "White")

  val yellow = Value(0xffff00, "Yellow")
  val magenta = Value(0xff00ff, "Magenta")
  val cyan =  Value(0x00ffff, "Cyan")
}