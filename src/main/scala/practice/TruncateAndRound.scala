package practice

import scala.math.pow

object TruncateAndRound {
  /**
   * https://stackoverflow.com/questions/11106886/scala-doubles-and-precision/11107546#11107546
   */

  /**
   * check math.floor and math.ceil
   */

  def truncate(x: Double): Double = {
    math.floor(x  * 100) / 100
  }

  def round(x: Double): Double = {
    math.rint(x * 100) / 100
  }

  def truncateAt(n: Double, p: Int): Double = {
    val s = pow(10, p)
    math.floor(n * s) / s
  }

  def roundAt(n: Double, p: Int): Double = {
    val s = pow(10, p)
    math.round(n * s) / s
  }

  truncate(10.00999999)
  round(10.00999999)

  truncateAt(10.00999999, 3)
  roundAt(10.00999999, 3)
}
