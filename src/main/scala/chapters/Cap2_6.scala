package chapters

object Cap2_6 extends App {
  def signum(num: Int): Int = {
    if(num > 0) 1 else if(num < 0) -1 else 0
  }

  /**
   * The empty block return the
   * class Unit with the value ()
   */
  val v = {}

  var y: Int = 0
  var x: Unit = ()
  x = y = 1

  //for (int i = 10; i >= 1; i--) System.out.println(i);
  for(i <- 1 to 10 reverse) println(i)
  for(i <- 10 to 1 by -1) println(i)
  for(i <- Range.inclusive(10, 1, -1)) println(i)

  // Not recommended, less readable than others
  for(i <- 10 to (1,-1)) println(i)

  def xToY(x: Int, y: Int) = {
    if(x < y) x to y
    else x to y by -1
  }

  def countDown(n: Int) = for(i  <- n to 0 by -1) println(i)

  /**
   * Product of the Unicode Codes of all letter
   * in a String
   */
  // With for
  def product1(s: String) = (for(i <- s) yield BigInt.apply(i)).product
  // Without for
  def product2(s: String): BigInt = s.map(BigInt(_)).product
  // Recursive
  def product3(s: String): BigInt = if(s.length == 1) BigInt(s(0).toByte)
                                    else BigInt(s.head) * product3(s.tail)
  // Recursive 2
  def product4(s: String): BigInt = if(s.length > 1) BigInt(s.head) * product4(s.tail) else BigInt(s.head)

  def computeXN(x: Double, n: Int): Double = {
    if(n%2 == 0 && signum(n) == 1) pow(computeY(x, n), 2)
    else if(n%2 != 0 && signum(n) == 1) x * computeXN(x, n-1)
    else if(n == 0) 1
    // if n < 0
    else  1 / computeXN(x, -n)
  }

  def computeY(x: Double, n: Int): Double = {
    computeXN(x, n/2)
  }
}
