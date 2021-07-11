package practice.collection

import scala.collection.mutable.ArrayBuffer
import scala.math.pow

/**
 * Example to learn map, filter, reduce, fold, fold left...
 * Note: It is necessary to learn Higher-order function and
 * anonymous function (function literal)
 * before (or at the same time) learning map, filter...
 * In the example below
 */
object MapFilterReduce {

  val intArray = Array[Int](1 to 10: _*)
  val emptyIntArray = Array[Int]()
  val intArrayNumbers = intArray.concat(Array(-100))
  // The first position is Birth Weight
  // The second position is one month old, third is two month old...
  val  babyWeightByMonth = Array[Double](3.3, 4.5, 5.6, 6.4, 7.0, 7.5, 7.9, 8.3, 8.6, 8.9, 9.2, 9.4, 9.6)

  def square(x: Int): Int = { pow(x, 2).toInt }

  def round(x: Double): Double = {
    math.rint(x * 100) / 100
  }

  /**
   * Map Sample
   * Calculate the square of each integer.
   * element = element * element
   */

  /**
   * Note: intArray is val but it is possible to change its elements.
   */

  // Traditional Solution (Change intArray)
  for(i <- 0 until intArray.length) {
    intArray(i) *= intArray(i)
  }

  // Return a new Array
  for(i <- intArray) yield i * i

  /**
   * map
   * Given an array (List),
   * transform its elements and include the results in a new array.
   *  list.map(function(element))
   *  Note: transform = change the original values and/or change the type
   */

  /**
   * Bellow many different ways to do the Same Thing
   */

  intArray.map(x => pow(x, 2).toInt)

  intArray.map(pow((_: Int), 2).toInt)
  intArray.map(pow(_, 2).toInt)

  intArray.map(square) // implicit square(_)
  intArray map square // omitting . ( ) _

  intArray.map {
    x => pow(x, 2).toInt
  }
  intArray.map {
    pow((_: Int), 2).toInt
  }
  intArray.map {
    pow(_, 2).toInt
  }

  intArray.map {
    square // implicit square(_)
  }

  // Anonymous function, also known as a function literal.
  intArray.map((x:Int) => {
    x * x
  })
  intArray.map((x:Int) => x * x)
  intArray.map {
    (x:Int) => x * x
  }
  intArray.map(x => x * x)
  intArray.map {
    x => x * x
  }
  // Note: It is not possible intArray.map(_ * _)
  // Bellow also anonymous function

  /**
   * Anonymous function
   * To double the contents of a list:
   * Right: map(_ * 2)
   * Wrong: map(_ + _)
   * The last line above is wrong
   * because in Scala the second _
   * means the second argument.
   * On the other hand is right: reduce(_ + _)
   */

  // Double each List element
  intArray.map(_ * 2)
  intArray map 2.* // less readable
  intArray.map {
    _ * 2
  }

  // intArray.map(_ + _) // Does not compile

  /**
   * Filter Sample
   * Remove all odd numbers.
   */

  // Traditional Solution
  val newIntArrayBuffer = new ArrayBuffer[Int]
  for(i <- 0 until intArray.length) {
    if(i % 2 == 0) {
      newIntArrayBuffer.addOne(i)
    }
  }

  // Return a new Array
  for(i <- intArray if i % 2 == 0) yield i

  /**
   * Filter
   * Given an array (List),
   * return a new array that contain only the elements that satisfy a certain condition.
   * list.filter(function(boolean condition with element))
   * listOfNumbers.filter(isPositive(element))
   */

  // Better way
  intArray.filter(_ % 2 == 0)

  /**
   * In reduce the accumulator (result) must have the same type than the type of list elements.
   * val result: type1 = List[type1](element, element, ...).reduce {
   *                                                    function(accumulator, element) {
   *                                                            ...
   *                                                            return newAccumulator
   *                                                    }
   *                                            }
   * val result: Int = List[Int].reduce(...)
   * The first element will be used as the initial accumulator value.
   * The second element in list will be used as element value when
   * reducer function is called the first time.
   * fold is similar to the reduce.
   * The difference the fold has a initial value
   */

  /**
   * Exercises for reduce fold scan foldLeft
   * - Sum all elements
   * - Show the greater integer
   * - Sum only positive numbers
   * - Given an array which contains weights per age (month) of a baby,
   * calculate the weight gain for each month. Calculate also growth rate in %.
   * - After do the exercise above try to get the original array from the calculate results (Example for scan use).
   * - Return the smallest and largest integer with only one (loop) fold function.
   */

  // sum
  intArray.reduce(_ + _)
  emptyIntArray.reduce(_ + _) // reduce does not work with empty list
  emptyIntArray.fold(0)(_ + _) // It works

  // max
  intArray.reduce((a, b) => if(a > b) a else b)
  intArray.reduce(_ max _)
  emptyIntArray.reduce(_ max _) // reduce does not work with empty list
  emptyIntArray.fold(Int.MinValue)(_ max _) // It works

  /**
   * Note: For sum and max use array.sum, array.max
   * For empty list does not use max use fold instead.
   */

  /**
   * Bellow
   */
  assert({
    // Sum only positive numbers
    val sumPositiveNumbers = intArrayNumbers.fold(0)((acc, x) => if(x > 0) acc + x else acc)
    (sumPositiveNumbers == intArray.sum &&
      sumPositiveNumbers > intArrayNumbers.sum &&
      sumPositiveNumbers == intArrayNumbers.filter(_ > 0).sum &&
      sumPositiveNumbers == intArrayNumbers.filter(_ > 0).reduce(_ + _))
  })

  // Calculate weight gain monthly
  def weightGainEachMonth(weightByMonth: Array[Double]): Array[Double] = {
    absoluteGrowthRate(weightByMonth)
  }

  /**
   * Absolute growth rates
   * Real Increase or Decrease
   */
  def absoluteGrowthRate(a: Array[Double]): Array[Double] = {
    (for(i <- a.indices.dropRight(1)) yield {
      a(i + 1) - a(i)
    }).toArray
  }

  // To measure the growth rate of monthly

  /**
   * Relative growth rate (RGR) is growth rate relative to size.
   * It is also called the exponential growth rate,
   * or the continuous growth rate.
   * Return Percent Growth Rate between array positions.
   */
  def relativeGrowthRateBetweenIndices(a: Array[Double]): Array[Double] = {
    (for(i <- a.indices.drop(1)) yield relativeGrowthRate(a(i-1), a(i))).toArray
  }

  /**
   * Calculate the percent change from one period
   * to another.
   * Return Percent Increase & Decrease
   */
  def relativeGrowthRate(before: Double, after: Double): Double = {
    ((after / before) - 1) * 100
  }

  /**
   * Bellow two Example for scan use
   */

  /**
   * Get the growth from Absolute Growth Rate
   */
  val absoluteGrowth = weightGainEachMonth(babyWeightByMonth)
  // Start with birth weight 3.3 kg
  val originalValues1 = absoluteGrowth.scan(3.3)((a,b) => a + b).toArray

  /**
   * Get the growth from Relative Growth Rate
   */
  val relativeGrowth = relativeGrowthRateBetweenIndices(babyWeightByMonth)
  // Start with birth weight 3.3 kg
  val originalValues2 = relativeGrowth.scan(3.3)((a,b) => round(a * ((b/100) + 1)))

  // Compare two arrays
  originalValues1 sameElements babyWeightByMonth
  assert(java.util.Arrays.equals(originalValues1, babyWeightByMonth))

  // Compare two arrays
  originalValues2 sameElements babyWeightByMonth
  assert(java.util.Arrays.equals(originalValues2, babyWeightByMonth))

  /**
   * Smallest and Largest in one fold function
   */

  Array(1,2,3,-50,9,8,10,200,5,6,7).foldLeft(Int.MaxValue, Int.MinValue) {
    (a ,b) => {
      var min: Int = a._1
      var max: Int = a._2
      if(b < a._1) min = b
      if(b > a._2) max = b
      (min, max)
    }
  }
  // No Variables version
  Array(1,2,3,-50,9,8,10,200,5,6,7).foldLeft(Int.MaxValue, Int.MinValue) {
    (a ,b) => {
      (if(b < a._1) b else a._1, if(b > a._2) b else a._2)
    }
  }
  // getSmallestAndLargest as Reducer function
  Array(1,2,3,-50,9,8,10,200,5,6,7).foldLeft(Int.MaxValue, Int.MinValue) {
    getSmallestAndLargest
  }

  // (Int, Int) syntax sugar for Tuple2[Int, Int]
  def getSmallestAndLargest(tuple2: (Int, Int), number: Int): (Int, Int) = {
    (if(number < tuple2._1) number else tuple2._1, if(number > tuple2._2) number else tuple2._2)
  }
}
