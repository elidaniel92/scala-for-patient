package practice

object TupleSample {
//  https://blog.ssanj.net/posts/2017-10-29-tuples-are-different-to-function-arguments-in-scala.html
  /**
   * Tuple Type:
   * This (String, Int) is a syntax sugar for Tuple2[String, Int]
   * Literal Tuple: ("Str", 10)
   * Literal Tuple: "Str" -> 10 // Note: is also a tuple but only use this for Map to be more readable
   */

  /**
   * Note: tuple is not a Array, a Argument In Sequence or a String
   * Unlike array or string positions, the component positions of a tuple start with 1,
   * not 0.
   */

  val tuple2: Tuple2[String, Int] = ("String", 10)
  val tuple2_2: (String, Int) = ("String", 10)
  val tuple2_3 = "String" -> 10

  println(tuple2._1) // Take first value
  println(tuple2._2) // Take second value

  // Reassignment to val
  // tuple2._1 = "Another String" // Not Compile, _1 is a val

  // Reassign tuple Values Alternative

  // Copy
  val person = ("Daniel", 20, "Male")
  val personCopy = person.copy(_1 = "Anne", _3 = "Female")

  // Use a var
  var person2 = ("Daniel", 20, "Male")
  person2 = person.copy(_1 = "Anne", _3 = "Female")

  // Convert a tuple to val
  val (firstValue: String, secondValue: Int) = tuple2

  // Convert a literal tuple to val (the Types are inferred)
  val (a, b, c) = (1, 2.0d, "A")

  // This method receive 0 or N literal Int.
  def sum(args: Int*): Int = {
    args.sum
  }

  // Three literal Int
  sum(1,2,3)

//  sum((1,2,3)) // Does not compile because sum doesn't receive a Tuple3[Int, Int, Int]

  def sumTwoNumbers(num1: Int, num2: Int): Int = {
    num1 + num2
  }

  sumTwoNumbers(1,2)

//  sumTwoNumbers((1,2)) // Does not compile, (1, 2) != 1, 2

  /**
   * Mutable Tuple2 alternative
   */
  // case mutable attributes
  case class Doublet[A,B](var _1: A, var _2: B) {}
  val doublet = Doublet(1, "old")
  // Mutable
  doublet._1 = 2
  doublet._2 = "new"
  // receive only tuple2
  def receiveTuple2[A,B](tuple2: (A, B)): String = tuple2.getClass.toString
  // implicit conversion
  implicit def doublet_to_tuple[A,B](db: Doublet[A,B]): (A, B) = (db._1, db._2)
  // implicit doublet_to_tuple(doublet)
  receiveTuple2(doublet)
}
