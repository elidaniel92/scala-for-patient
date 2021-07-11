package chapters

object Cap3_1 extends App {
  /**
   * Fixed Length Array.
   * new Array[Type](length)
   * Array[Type](literal value(s))
   * Array(literal_value, literal_value...)
   * Above, the type is inferred.
   * intArray(0) // Access first element.
   */

  /**
   * Attention!
   * THREE integers, all initialized with zero;
   * ONE integer initialized with three.
   * THREE integers, all initialized with three;
   */
  val intArray1 = new Array[Int](3)
  val intArray2 = Array[Int](3)
  val intArray3 = Array[Int](3,3,3)

  // Ten strings initialized with null.
  val str1A = new Array[String](10)

  /**
   * The type is inferred.
   * Note: No new when you supply initial values.
   */
  val str2A = Array("Hello", "World")

  // Use () instead of [] to access elements.
  println(str2A(1))

  /**
   * Variable Length Array.
   * new ArrayBuffer[Type]
   * new ArrayBuffer[Type]()
   * ArrayBuffer[Type]()
   * ArrayBuffer[Type](literal value(s))
   * ArrayBuffer(literal value(s))
   */

  var intDynamicArray = new ArrayBuffer[Int]
  // Add an element at the end.
  intDynamicArray.addOne(13) // Or intDynamicArray += 13
  // Add multiple elements at the end.
  //intDynamicArray += (45, 17) // Deprecated in 2.13.3
  // Add any collection at the end.
  intDynamicArray.addAll(Array(45, 17)) // Or intDynamicArray ++= Array(45, 17)
  // Removes the last 2 elements.
  intDynamicArray.trimEnd(2)

  /**
   * Adding or removing elements at the end of an array buffer is an efficient
	 * (“amortized constant time”) operation.
   */

  // Insert 88 at index 1 (second position)
  intDynamicArray.insert(1, 88)

  // Insert 1, 2, 3 at the last position number (the result will be before the last element).
  //intDynamicArray.insert(intDynamicArray.length - 1, 1, 2, 3) // It is not possible in 2.13.3
  intDynamicArray.insertAll(intDynamicArray.length - 1, Array(1,2,3,4,5,6))

  // Remove second element.
  intDynamicArray.remove(1)

  /**
   * Remove 3 elements from the third,
   * in other words after the second.
   * Remove(from, number to remove)
   */
  intDynamicArray.remove(2, 3)

  /**
   * Dynamic and Fixed
   * After adding elements dynamically convert
   * to Fixed Array.
   */

  var intArray: Array[Int] = intDynamicArray.toArray
  // or
  intDynamicArray = intArray.to(ArrayBuffer)

  /**
   * Initializing arrays smartly
   * Smart array initialization
   */
  // intArray = Array(Range: _*)
  intArray = Array(1 to 10: _*)
  intArray = Array(10 to 1 by -1: _*)
  intArray = Array((1 to 10).reverse: _*)
  intArray = Array(Range.inclusive(10,1,-1): _*)

  /**
   * Note:
   * val intArrayConstant = Array[Int](1 to 10: _*)
   * intArrayConstant = Array[Int](2 to 20 by 2: _*)
   * The last line above does not compile because intArrayConstant is val
   * but it is possible change all elements from intArrayConstant
   * because the elements are not val (constant).
   * intArrayConstant.mapInPlace(_ * 2) or just intArrayConstant(0) = intArrayConstant(0) * 2
   * To have immutable elements use scala.immutable.ArraySeq
   * See the example below to understand how it all works.
   */

  val intArrayConstant = Array[Int](1 to 10: _*)
  // intArrayConstant = Array[Int](2 to 20 by 2: _*) // Does not compile

  // Change elements from intArrayConstant
  intArrayConstant.mapInPlace(_ * 2)
  intArrayConstant.transform(_ * 2) // It is deprecated since 2.13.0
  intArrayConstant(0) = 2

  // Immutable elements
  val intArrayConstantElements = scala.collection.immutable.ArraySeq[Int](1 to 10: _*)
  // intArrayConstantElements(0) = 1 // Does not compile

  /**
   * Transforming Arrays
   */

  var newIntArray: Array[Int] = null
  var newIntDynamicArray: ArrayBuffer[Int] = null

  // Yield create a new collection of the same type as the original.
  newIntArray = for(i <- intArray) yield i * 2
  newIntDynamicArray = for(i <- intDynamicArray) yield i * 2

  // if true do statement/expression
  newIntArray = for(i <- intArray if i == 45) yield i

  // for comprehension or...
  for(i <- Array(1 to 10: _*) if i % 2 == 0) yield i * 100
  // filter and map
  Array(1 to 10: _*) filter(_ % 2 == 0) map(_ * 100)

  /**
   * Given a sequence of integers, remove
   * all negative numbers but the first negative number.
   * The traditional solution is inefficient because
   * remove variables from the front.
   */
  def traditionalSolution(s: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    var isFirst: Boolean = true
    var i: Int = 0
    while (i < s.length) {
      if (s(i) >= 0) {
        i = i + 1
      } else if (isFirst) {
        isFirst = false
        i = i + 1
      } else {
        s.remove(i)
      }
    }
    return s
  }
  // There is also a solution using drop instead "1 until negativesIndex..."
  def newSolution(s: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    // Take negative numbers indexes
    val negativesIndex = for(i <- 0 until s.length; if(s(i) < 0)) yield i
    // Traverse the Index (except first element) in reverse order.
    // Remove negative elements from the back.
    for(i <- (1 until negativesIndex.length).reverse) s.remove(negativesIndex(i))
    return s
  }

  //Sum
  val arraySum = Array(5,3,8).sum
  val arrayBufferSum = ArrayBuffer(5,3,8).sum
  // Max and Min
  val maxInt = Array(5,3,8).max
  val minInt = ArrayBuffer(5,3,8).min

  // Array(1, 7, 2, 9).sorted(_ < _) // It is not possible in 2.13.3

  // Sort ascending order without modifying the original.
  val sortedInAscendingOrder = ArrayBuffer(1, 7, 2, 9).sorted
  // Sort descending order without modifying the original.
  val sortedInDescendingOrder = Array(1, 7, 2, 9).sorted(Ordering.Int.reverse)

  /**
   * Sort a in ascending order.
   * Change the original.
   * Can not be ArrayBuffer.
   * Check scala.util.Sorting for more info.
   */
  val a = Array(1, 7, 2, 9)
  scala.util.Sorting.quickSort(a)

  // Array to String (toString)
  Array(1, 7, 2, 9).mkString(" and ")  // Separator
  ArrayBuffer(1, 7, 2, 9).mkString("<", "-", ">")  // Separator, Start, End

  //  This is the useless toString method from Java
  Array(1,2,3).toString

  // Useful toString from Scala
  ArrayBuffer(1,2,3).toString

  /**
   * NOTE: The methods for the Array class are listed under ArrayOps. Technically,
   * an array is converted to an ArrayOps object before any of the operations is
   * applied.
   */

  val setA: Int => Array[Int] = (n) => {
    new Array[Int](n).map((x) => util.Random.nextInt(n))
  }

  // Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
  val swapsAdjacentElements: Array[Int] => Array[Int] = (a) => {
    val newArray = new Array[Int](a.length)
    for(i <- 1 until a.length by 2) {
      newArray(i) = a(i-1)
      newArray(i-1) = a(i)
    }
    if(a.length % 2 != 0) {
      newArray(a.length - 1) = a(a.length - 1)
    }
    newArray
  }

  assert(swapsAdjacentElements(Array(1, 2, 3, 4, 5)) sameElements(Array(2, 1, 4, 3, 5)))
  assert(swapsAdjacentElements(Array(1, 2, 3, 4)) sameElements(Array(2, 1, 4, 3)))
  assert(swapsAdjacentElements(Array(1)) sameElements(Array(1)))

  // Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
  // Mutable version
  val swapsAdjacentElements2: Array[Int] => Array[Int] = (a) => {
    var temp: Int = 0
    for(i <- 1 until a.length by 2) {
      temp = a(i)
      a(i) = a(i-1)
      a(i-1) = temp
    }
    a
  }

  assert(swapsAdjacentElements2(Array(1, 2, 3, 4, 5)) sameElements(Array(2, 1, 4, 3, 5)))
  assert(swapsAdjacentElements2(Array(1, 2, 3, 4)) sameElements(Array(2, 1, 4, 3)))
  assert(swapsAdjacentElements2(Array(1)) sameElements(Array(1)))

  // Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
  // yield version
  val swapsAdjacentElements3: Array[Int] => IndexedSeq[Int] = (a) => {
    for(i <- 0 until a.length) yield
      if(i % 2 != 0) a(i - 1)
      else if(i != a.length - 1) a(i + 1)
      else a(i)
  }

  // Performance Test

  val swapsPerformanceTest: (Array[Int] => Any) => Long = (function) => {
    val a = Array(1 to 100000: _*)
    val start: Long = java.lang.System.nanoTime()
    function(a)
    val end: Long = java.lang.System.nanoTime()
    end - start
  }

  println(swapsPerformanceTest(swapsAdjacentElements))
  println(swapsPerformanceTest(swapsAdjacentElements2))
  println(swapsPerformanceTest(swapsAdjacentElements3))

  val positiveAndNegative: Array[Int] => Array[Int] = (a) => {
    (a.filter(_ > 0).concat(a.filter(_ <= 0)))
  }

  val positiveAndNegative2: Array[Int] => Array[Int] = (a) => {
    val newArray: Array[Int] = new Array[Int](a.length)
    var count: Int = 0
    for(i <- a) {
      if(i > 0) {
        newArray(count) = i
        count += 1
      }
    }
    for(i <- a) {
      if(i <= 0) {
        newArray(count) = i
        count += 1
      }
    }
    newArray
  }

  val positiveAndNegative3: Array[Int] => ArrayBuffer[Int] = (a) => {
    val newArrayBuffer: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    for(i <- a) {
      if(i > 0) {
        newArrayBuffer.addOne(i)
      }
    }
    for(i <- a) {
      if(i <= 0) {
        newArrayBuffer.addOne(i)
      }
    }
    newArrayBuffer
  }

  val posNegPerformanceTest: (Array[Int] => Any) => Long = (function) => {
    val size = 10000
    val a = Array(1 to size: _*)
    val start: Long = java.lang.System.nanoTime()
    function(setAPositiveAndNegative(size))
    val end: Long = java.lang.System.nanoTime()
    end - start
  }

  val setAPositiveAndNegative: Int => Array[Int] = (n) => {
    new Array[Int](n).map((x) => {
      if(util.Random.nextInt(2) == 0) util.Random.nextInt(n) * -1
      else util.Random.nextInt(n)
    })
  }

  assert(positiveAndNegative(Array(-10,3,2,1,-3,-2,-1,10)) sameElements(Array(3,2,1,10,-10,-3,-2,-1)))
  assert(positiveAndNegative2(Array(-10,3,2,1,-3,-2,-1,10)) sameElements(Array(3,2,1,10,-10,-3,-2,-1)))
  assert(positiveAndNegative3(Array(-10,3,2,1,-3,-2,-1,10)) sameElements(ArrayBuffer(3,2,1,10,-10,-3,-2,-1)))

  ((for(i <- 1 to 1000) yield posNegPerformanceTest(positiveAndNegative2)).sum / 1000).toDouble / ((for(i <- 1 to 1000) yield posNegPerformanceTest(positiveAndNegative)).sum / 1000)

  ((for(i <- 1 to 1000) yield posNegPerformanceTest(positiveAndNegative3)).sum / 1000)

  val duplicatesRemoved: Array[Int] => Array[Int] = (a) => {
    a.distinct
  }

  // new solution using drop
  def newSolutionUsingDrop(s: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    // Take negative numbers indexes
    val negativesIndex = for(i <- 0 until s.length; if(s(i) < 0)) yield i
    // Traverse the Index (except first element) in reverse order.
    // Remove negative elements from the back.
    for(i <- negativesIndex.drop(1).reverse) s.remove(negativesIndex(i))
    return s
  }

  java.util.TimeZone.getAvailableIDs.filter(_.slice(0, 7) == "America").map(_.drop(8)).sorted
  java.util.TimeZone.getAvailableIDs.withFilter(_.slice(0, 7) == "America").map(_.drop(8)).sorted
}
