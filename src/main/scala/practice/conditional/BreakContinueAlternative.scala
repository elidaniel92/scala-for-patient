package practice.conditional

import practice.Practice.isPrimeNumber

object BreakContinueAlternative {
  /**
   * Break and Continue Alternative
   * Example:
   * Exit the loop when the value is a prime number.
   * Print all even, Skip all odd.
   */

  // Java break continue example
  /*public static void printOnlyEven(int[] array) {
    for(int n: array) {
      if(isPrime(n)) {
        break;
      } else if(n % 2 != 0) {
        continue;
      }
      System.out.println(n);
    }
  }*/

  def printOnlyEven(array: Array[Int]) = {
    val length = array.length
    var i = 0
    while(i < length) {
      if(isPrimeNumber(array(i))) {
        i = length // break
      } else {
        if(array(i) % 2 == 0) {
          println(array(i))
        }
        i += 1 // increment
      }
    }
  }

  @scala.annotation.tailrec
  def printOnlyEvenRecursive(array: Array[Int]): Unit = {
    if(!isPrimeNumber(array.head)) {
      if(array.head % 2 == 0) {
        println(array.head)
      }
      if(array.length >= 2) printOnlyEvenRecursive(array.tail)
    }
  }

  def printOnlyEvenBreakable(array: Array[Int]): Unit = {
    scala.util.control.Breaks.breakable {
      for(i <- array) {
        if(isPrimeNumber(i)) {
          scala.util.control.Breaks.break()
        } else if(i % 2 == 0) {
          println(i)
        }
      }
    }
  }
}
