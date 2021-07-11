package practice.collection

import org.apache.commons.lang3.RandomStringUtils

object Sorting extends App {
  // Case class
  case class Person(name: String, gender: String, age: Int)
  val people = Array[Person](
    Person("John", "male", 30),
    Person("Linda", "female", 25),
    Person("Robert", "male", 15),
    Person("Daniel", "male", 25),
    Person("Susan", "female", 15),
    Person("Anne", "female", 20))

  // Order (sorted, sortBy, sortWith)

  case class Something(str: String, intValue: Int)
  val things = new Array[Something](10)
  for(i <- things.indices.dropRight(3)) {
    things(i) = Something(RandomStringUtils.randomAlphabetic(2),
      scala.util.Random.nextInt(10))
  }
  things(things.length -1) = Something("AA", 1)
  things(things.length -2) = Something("AA", 3)
  things(things.length -3) = Something("AA", 2)

  // Sort Array
  Array(3,1,2).sorted // ASC

  /**
   * Note: For Int, sorted.reverse has
   * better performance than sorted(Ordering[Int].reverse)
   * (maybe arrayInt.sorted.reverse use java.util.Arrays.sort)
   */

  // Ordering.Int.reverse = Ordering[Int].reverse
  Array(3,1,2).sorted(Ordering.Int.reverse) // DESC
  Array(3,1,2).sorted(Ordering[Int].reverse) // DESC
  Array(3,1,2).sorted.reverse // DESC

  Array("C","A","B").sorted(Ordering.String.reverse) // DESC
  Array("C","A","B").sorted(Ordering[String].reverse) // DESC
  Array("C","A","B").sorted.reverse // DESC

  // Return Unit, change intArray
  val intArray = Array(3,1,2)
  scala.util.Sorting.quickSort(intArray) // Using java.util.Arrays.sort

  // Sort by field
  things.sortBy(_.str) // ASC
  things.sortBy(_.str)(Ordering.String.reverse) // DESC
  things.sortBy(_.str)(Ordering[String].reverse) // DESC
  things.sortBy(_.str).reverse // DESC

  // Two field
  things.sortBy(x => (x.str, x.intValue)) // ASC
  things.sortBy(x => (x.str, x.intValue))(Ordering[(String, Int)].reverse) // DESC

  // Sort tuples
  Array(("a", 10), ("b", 30), ("d", 20), ("c", 20)).sortBy(_._2)
  Array(("a", 10), ("b", 30), ("d", 20), ("c", 20)).sortBy(x => (x._2, x._1))

  // SortWith
  Array(1,3,2).sortWith(_ < _) // ASC = sortWith((a, b) => a < b)
  Array(1,3,2).sortWith(_ > _) // DESC

  // Sort by using own function
  def sortByIntValueASC(something1: Something, something2: Something): Boolean = {
    something1.intValue < something2.intValue
  }

  // function as argument
  things.sortWith((a, b) => sortByIntValueASC(a,b)) // Explicit
  things.sortWith(sortByIntValueASC(_,_))
}
