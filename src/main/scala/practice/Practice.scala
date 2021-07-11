package practice

object Practice {
  // Prime Number
  def isPrimeNumber(n: Int) = {
    val absN = scala.math.abs(n)
    if(absN >= 2) {
      !(2 to (absN / 2) exists (absN % _ == 0))
    } else {
      false
    }
  }

  /**
   * Return a String that contains:
   * "[Index]=PrimeNumbers
   *  [0]=5
   *  [2]=3
   *  ..."
   */

  // Impl with mkString
  def showPrimes(a: Array[Int]) = {
    (for(i <- 0 until a.length; if isPrimeNumber(a(i)) )
      yield (Array(i, a(i)).mkString("[", "]=",""))).mkString("[Index]=PrimeNumbers\n","\n","")
  }

  // Impl with map,reduce
  def showPrimes2(a: Array[Int]) = {
    val primes = (for(i <- 0 until a.length; if isPrimeNumber(a(i))) yield Array(i, a(i)))
    "[Index]=PrimeNumbers\n" + primes.
      map(x => "[" + x(0) + "]=" + x(1)).
      reduce((a,b) => a + "\n" + b)
  }

  def showPrimes3(a: Array[Int]) = {
    a.scanRight()
  }

  val a = Array(1,2,3,4,6,1,2,4,5,6,7)
  assert(showPrimes(a) == showPrimes2(a))
}
