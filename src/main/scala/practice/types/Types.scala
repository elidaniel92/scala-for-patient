package practice.types

import scala.util.Try

object Types {
  def foo1[T](x: T)(implicit n: Numeric[T]) = n.toDouble(x)

  def foo2[T : Numeric](x: T) = implicitly[Numeric[T]].toDouble(x)

  def foo3[T](x: T)(implicit n: Numeric[T]) = {
    import n._
    val xx = 1
    val y = xx.doubleValue
    x.toDouble
  }

  // a dynamic solution which cast the type in runtime
  def toDoubleDynamic(x: Any): Double = x match {
    case s: String => s.toDouble
    case jn: java.lang.Number => jn.doubleValue()
    case _ => throw new ClassCastException("cannot cast to double")
  }

  // GetOrElse
  def main(args: Array[String]): Unit = {
    val xAny: Any = Try(1/1).getOrElse("Not Possible")
    val xInt: Int = Try(1/1).getOrElse(1)
  }
}
