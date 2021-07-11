package chapters

import scala.math._
import scala.util.Random

object Cap1 extends App {
  val CONST: Int = 1 // Constant
  var str: String = null // Variable
  //var d: Double // Error: need initialize it

  /**
   *	The rule of thumb is that
   *  a parameterless method that
   *  doesn’t modify the object
   *	has no parentheses.
   *  e.g "Hello".distinct
   */
  printf("Hello".distinct)
  println

  str = "String"
  // toUpperCase, method from java.lang.String
  println(str.toUpperCase)

  /**
   * Bolow, the java.lang.String object str
   * is implicitly converted to a StringOps object,
   * and then the intersect method of the StringOps class is applied.
   */
  printf(str.intersect("Strin") + "\n")

  /**
   * Similarly, there are classes RichInt, RichDouble, RichChar.
   * The Int CONST 1 is converted to a RichInt.
   * The types Int, Double, Float are classes.
   * There is no distinction between
   * primitive types and class types in Scala
   */

  java.lang.System.out.println(CONST.to(10))
  // Above I called the println from Java.

  // "a.method(b)" equal "a method b", so below I can...
  printf((10 to 20).toString())

  var int1, int2 = 1
  var varDouble1 = 0.1
  var varDouble2 = 0.2d
  var varfloat = 0.3f

  /**
   *  a + b is a shorthand for a.+(b),
   *  "+" is a method from object a.
   */
  printf("\n" + (int1.+(int2)) + "\n")

  /**
   * "printf(int1)" this command doesn't compile
   * because int1 is not a String.
   * When the method + receives a parameter String
   * it returns a String. In the line below is not
   * necessary use toString method.
   */
  printf(int1 + "")

  /**
   * Str.*(3) return a String.
   * Below a shorthand.
   */
  print("Str" * 3)

  // Scala does not have ++ and -- operator. Use += and -=.
  int1 += 1

  // Calling a Function from "import scala.math._"
  varDouble1 = sqrt(25)
  varDouble2 = pow(2, 4)

  /**
   * Scala does not have static methods,
   * has companion object (singleton object).
   * It's similar a static methods.
   */

  var character: Char = ' '

  /**
   * str(0) looks like a function call.
   * It is a shortcut for str.apply(0)
   */
  character = str(0)

  /**
   * The BigInt companion object has a method apply
   * that convert numbers or Strings to BigInt object.
   */
  var bInt = BigInt("123")

  /**
   * In Scala is possible "import" method.
   * BigInt.probablePrime(100, Random)
   * Importing probablePrime method from BigInt:
   * import scala.math.BigInt.probablePrime or
   */
  scala.math.BigInt.probablePrime(100, Random) // import scala.util.Random

  /**
   * Companion Object is a usual way for
   * constructing objects.
   */
  var listOfNumbers = Array(1, 2, 3, 4, 7, 11)
}
