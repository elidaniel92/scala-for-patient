package chapters

object Cap2_3 extends App {
  /**
   * def funcName(parameterName: parameterType) = {expression}
   * Below the return type is the type of the expression.
   */
  def abs(x: Double) = if(x>=0) x else -x

  def facFor(n: Int) = {
    var total = 1
    for(i <- 1 to n) total = total * i
    total
  }

  /**
   * def funcName(parameterName: parameterType): returnType = {expression}
   */
  def facR1(n: Int): Int = (1 to n).product

  def facR2(n: Int): Int = if(n >= 2) n * (n - 1) * facR2(n - 2) else 1 // Stupid, facR2(2) = 2 * 1 * 1

  def facR3(n: Int): Int = if(n >= 2) n * facR3(n-1) else 1

  def fac(n: Int): Int = if(n <= 0) 1 else n * fac(n - 1) // Stupid, fac(1) = 1 * 1

  def fac2(n: Int): Int = if(n <= 1) 1 else n * fac(n - 1)

  /**
   * Default Argument
   */
  def helloMessage(userName: String = "user") = "Hello " + userName + "!"

  // without argument
  println(helloMessage())
  // with argument
  println(helloMessage("Jonh"))

  // Implementation without using variable...
  def htmlTxt(txt: String,
      bold: Boolean = false,
      italic: Boolean = false) = {
    def putI(txt: String, italic: Boolean) = {
      if(italic) "<i>" + txt + "</i>"
      else txt
    }
    def putB(txt: String, bold: Boolean) = {
      if(bold) "<b>" + txt + "</b>"
      else txt
    }
    putB(putI(txt, italic), bold)
  }

  // New htmlTxt Implementation with variable (newTxt).
  def htmlTxt2(txt: String,
      bold: Boolean = false,
      italic: Boolean = false) = {
    var newTxt: String = txt
    if(bold) {
      newTxt = "<b>" + newTxt + "</b>"
    }
    if(italic) {
      newTxt = "<i>" + newTxt + "</i>"
    }
    newTxt
  }

  /**
   * Supply only the necessary argument,
   * default arguments are applied.
   */
  println(htmlTxt("Word"))

  /**
   * Supply argument for the second
   * parameter (bold).
   */
  println(htmlTxt("Word", true))

  // Supply all arguments
  println(htmlTxt("Word", false, true))

  /**
   * Specify the parameter name (italic) for
   * the second argument.
   */
  println(htmlTxt("Word", italic=true))

  // Supply all arguments
  println(htmlTxt("Word", true, true))

  /**
   * Note: Array(1,3,7) != (1,3,7)
   * You can call this function
   * with as many int arguments as you like.
   * sum(int, int, int...)
   */
  def sum(args: Int*) = {
    var result: Int = 0;
    for(arg <- args) result += arg
    result
  }

  println(sum(1,3,7))
  /**
   * The argument cannot be a range of integers.
   * sum(1 to 5)
   * Bellow a solution.
   */

  println(sum(1 to 5: _*)) // The compiler consider as an argument sequence.

  // This is deprecated
  println(sum(Array(1,2,3): _*)) // Convert Array(1,2,3) to (1,2,3)
  // Alternatives
  println(sum(Array(1,2,3).toIndexedSeq: _*))
  println {
    sum (scala.collection.immutable.ArraySeq.unsafeWrapArray(Array(1, 2, 3)): _*)
  }

  def sumR(args: Int*): Int = {
    if(args.length == 0) 0
    else args.head + sumR(args.tail: _*) // Convert it to an argument sequence.
  }

  sumR()

  /**
   * When you call a Java method with variable arguments of type
	 * Object, such as PrintStream.printf or MessageFormat.format,
	 * you need to convert any primitive types by hand
	 * (42.asInstanceOf[AnyRef]).
   */
  // public static String format(String pattern, Object ... arguments) {
  println(java.text.MessageFormat.format("The answer to {0} is {1}", "everything", 42.asInstanceOf[AnyRef]))
}
