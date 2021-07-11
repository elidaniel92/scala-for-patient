package practice.systemtype

object CallByValueAndName {
  /**
   * Call by Name != function as a argument
   *
   * It's best to think of "call-by-name" as just syntactic sugar.
   * The parser just wraps the expressions in anonymous functions,
   * so that they can be called at a later point, when they are used.
   *
   * One def with "call-by-name" parameter can simulate a (parameter less) def as argument
   * like function as argument.
   */

  // Call by value
  def callByValue(i: Int): Int = {
    println("By Value")
    i + i
  }

  // Call by name
  def callByName(i: => Int): Int = {
    println("By Name")
    i + i
  }

  def methodAddOne(i: Int): Int = {
    println("addOne")
    i + 1
  }

  callByValue(1)
  callByValue(methodAddOne(1))

  callByName(methodAddOne(1))
  callByName(1)

  /**
   * Function as a argument is not equal CallByName
   * but is similar.
   */
  def funcAsArg(funcI: () => Int): Int = {
    println("Func as Argument")
    funcI() + funcI()
  }

//  funcAsArg(1) // Not Compile
  funcAsArg(() => 1)

//  funcAsArg(methodAddOne(1)) // Not Compile
  funcAsArg(() => methodAddOne(1))

  // Note: callByName can receive a function
  // but it is not possible a function with call by name parameter
  val func: () => Int = () => { println("Func"); 1; }
  callByName(func())
  // Not compile
  val callByNameFunc = (i: => Int) => {
    println("Function By Name")
    i + i
  }
}
