package chapters

import scala.io.StdIn.readLine
import scala.io.StdIn.readInt
import scala.math.sqrt

object Cap2_1 extends App {
  // if/else has a value
  var value: Int = if (20 > 10) 1 else -1
  println(value)
  // Same effect
  if (20 > 10) value = 1 else value = -1
  println(value)
  /**
   * In Scala every expression has a type.
   * In above expression is Int.
   * Below is Any because Any is the
   * common supertype of both branches (String and Int).
   */
  if (20 > 10) "positive" else -1

  /**
   * Below is equivalent to if (10 > 20) 1 else ()
   * The class Unit has one value: ()
   * () is similar to void in Java
   * Technically speaking, void has no value
   * whereas Unit has one value that signifies
	 * “no value”. If you are so inclined,
	 * you can ponder the difference between an
   * empty wallet and a wallet with a bill
   * labeled “no dollars”.
   */
  val noValue = if (10 > 20) 1
  println(noValue)

  /**
   * It is necessary semicolons to have
   * more than one statement on a single line.
   * Note no semicolon is needed after second statement.
   */
  if(20 > 10) { println("Stmt 1"); println("Stmt 2") }

  var value2: Int = 0

  // A long statement over two lines...
  if(20 > 10) {
    value2 = value + 1 *
    3
  }

  /**
   * Scala programmers favor
   * the Kernighan & Ritchie brace style.
   */
  if(20 > 10) {
    value = 2
    value2 = 3
  }

  /**
   * A Block Statement "{...}" has a value.
   * The value is the value of last expression.
   * E.g.
   */
  val ax = 10
  val ay = 10
  val bx = 0
  val by = 0
  val distance = {
    val dx = ax - bx
    val dy = ay - by
    sqrt(dx * dx + dy * dy)
  }
  println("Distance: " + distance)

  /**
   * Assigments have Unit value: ()
   * Chained Assignments is not possible:
   * val y: Int = x = 1
   * y = x = 1
   */
  var x: Int = 0
  val y: Unit = x = 1
  println(x)
  println(y)

  // C-style
  printf("Hello, %s! You are %d years old.\n", "Fred", 42)

  /**
   * Reading a line of input from the console...
   * The statement (readLine, readInt, readBoolean etc.)
   * are deprecated as informed by the REPL.
   * (Since version 2.11.0) Use the methods in scala.io.StdIn
   * import scala.io.StdIn._
   */
  println("Read Str:")
  val str = readLine
  println("Str: " + str)
  println("Read Int:")
  println("Int: " + readInt)
}
