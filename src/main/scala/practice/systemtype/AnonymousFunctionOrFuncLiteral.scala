package practice.systemtype

object AnonymousFunctionOrFuncLiteral {
  /**
   * A function literal is just an expression that defines an unnamed function.
   */

  // Anonymous and Literal
  (x: Int) => x * x

  // Execute Anonymous Function
  ((x: Int) => x * x) (2)
  ((x: Int) => x * x).apply(2)

  // Not Anonymous but still is a literal function
  val namedFunc1 = (x: Int) => x * x // The namedFunc1 type is inferred

  // Explicit type
  val namedFunc2: Int => Int = x => x * x // Note: the x type is inferred

  // val namedFuncError = x => { x * x }  // The type can not be inferred

  // Implicit conversion
  val namedFunc3: Int => Double = x => x * x
  // Explicit conversion
  val namedFunc4: Int => Double = x => (x * x).toDouble

  // The namedFunc5 type is inferred, there is no explicit parameter.
  val namedFunc5 = (_: Int) * 2 // The parameter is also inferred

  // The _ type is inferred. There is no "(_) =>"
  val namedFunc6: Int => Int = _ * 2
  // Note: namedFunc7 syntax function for namedFunc8 and not namedFunc9
  val namedFunc7: Int => Int = {
    println("Not body function")
    _ * 2
  }
  val namedFunc8: Int => Int = {
    println("Not body function")
    (x) => x * 2
  }
  val namedFunc9: Int => Int = (x) => {
    println("Body function")
    x * 2
  }

  // val namedFuncError = _ * 2  // The type can not be inferred

  // (Int, Float) => Double
  val namedFunc10 = (_: Int) / 2.0d + (_: Float) // Note: for each _ one parameter
  val namedFunc11: (Int, Float) => Double = _ / 2 + _

  // Func11 and Func12 = addOne, same object
  val addOne: Int => Int = _ + 1
  val namedFunc12: Int => Int = addOne // There is no anonymous function
  val namedFunc13: Int => Int = {
    println("Not body function")
    addOne // There is no anonymous function
  }
  val namedFunc14: Int => Int = {
    println("Not body function")
    addOne(_) // There is an anonymous function here
  }
  assert(namedFunc12 eq addOne)
  assert(namedFunc13 eq addOne)
  assert(namedFunc14 != addOne)

  // less readable
  val namedFunc15: Int => Int = {
    2.*
  }

  // The compiler is smart.
  def method1: Int => Double = {
    println("I'm from body method")
    (x) => {
      println("I'm from body literal function")
      println("x type: " + x.getClass.getName)
      val calc = x * x
      println("x*x type: " + calc.getClass.getName)
      calc // It will be a Double
    }
  }

  // The compiler is VERY smart.
  def method2 = {
    println("I'm from body method")
    // An Anonymous function
    (() => {
      println("Executed Anonymous Function in body method")
      // The anonymous function return the function bellow
      // The type of the x is inferred
      // The return type is also inferred
      (x: Int) => {
        println("I'm from body literal function")
        println("x type: " + x.getClass.getName)
        val calc = x * x
        println("x*x type: " + calc.getClass.getName)
        calc // It will be a Float
      }
    }) ()
  }

  // Impossible
  /*def method3: Int => Int = {
    (() => {
      (x) => {
        x * x
      }
    })()
  }}*/

  /**
   * Tuples cannot be directly destructured in method or function parameters.
   * Check Pattern matching
   */
  val namedFunc16: Tuple2[Int, Int] => Tuple2[Int, Int] = (x) => x
//  val tuplesDirectlyDestructured: Tuple2[Int, Int] => Int = ((a, b)) => a
//  val tuplesDirectlyDestructured: ((Int, Int)) => Int = ((a, b)) => a
  val notTuples: (Int, Int) => Int = (a, b) => a

  /**
   * An alternative way of writing (not anonymous) functions LITERAL:
   * A pattern matching anonymous function.
   * Check Pattern matching
   */
}
