package practice.systemtype

object ValVarLazyDef {
  /**
   * TL-DR
   *
   * typeX = primitives (Int, Double), function...
   *
   * val, var lazy
   * name: type = {
   * // Evaluates when defined (lazy once when called)
   * }
   *
   * def
   * name(parameter): type = {
   * // Evaluates always when called
   * }
   *
   */

  /**
   * Syntax sugar
   * - Doesn't introduce any substantial new feature
   * to the language, it's just a sweeter syntax.
   * - It adds no actual features, but rather just makes the code easier to read and write.
   */

  /**
   * The Apply Method
   *
   * In mathematics and computer science, apply is a function that applies a function to arguments.
   * Mathematicians have their own little funny ways, so instead of saying
   * "then we call function f passing it x as a parameter" as we programmers would say,
   * they talk about "applying function f to its argument x".
   *
   * The apply method serves the purpose of closing the gap between
   * Object-Oriented and Functional paradigms in Scala.
   */

  /**
   * Note: "Every value is an Object in Scala"
   */

  /**
   * A statement is the smallest standalone element
   * that expresses some action to be carried out.
   * Whereas an expression in a programming language is
   * something that produces or returns a value.
   */

  // Constant
  val v1 = util.Random.nextInt

  // Function (or Method)
  def d1 = util.Random.nextInt
  def optionalEqualsSignForUnitReturn { () } // This syntax is deprecated

  /**
   * { ... } is just a code block (not a function)
   * The value is the value of last expression.
   * { ... } is here a expression too.
   */
  val v2 = {
    val x = util.Random.nextInt(10)
    x
  }

  // v2 equal to lines bellow
  val x = util.Random.nextInt(10)
  val v3 = x

  // Now it is a function
  val v4 = () => {
    1
  }

  // Calling a val function

  /**
   * Wrong! Unlike def.
   * This expression return a function (memory address of the object).
   * return () => Int
   */
  v4

  v4() // Syntax sugar for obj.apply()
  v4.apply() // v4 is a object

  /**
   * Val Function Syntax
   * val funcName: functionType = functionLiteral(parameter type optional)
   * Note: transform inputs into outputs.
   * input => output
   *
   * functionType
   * () => () // it receives Unit and return Unit
   * Int => String
   * Int, Int => Boolean
   *
   * functionLiteral
   * () => ()
   * (x: Int) => x // Anonymous function need parameter type
   * x => x
   *
   * val funcName: parameterType => returnType = parameterName => { expression }
   * val funcName: (parameterType, parameterType)  => returnType = (parameterName, parameterName) => { expression }
   */
  // Anonymous function
  () => x
  (() => x) () // Call anonymous function. Note: () is a syntax sugar for apply
  val funcName1: () => () = () => ()
  val funcName2: Int => Int = x => x
  val funcName3: (Int, Int) => Int = (a, b) => {
    a + b
  }

  // Explicit type
  val v5: () => Int = () => {
    1
  }

  /**
   * Note: intValue1 is equal 1
   * because v6 and v7 is the same function (object).
   * v8 is a Int Value and not a function.
   */
  var mutable = true
  val v6 = () => if (mutable) 1 else 0
  val v7 = v6 // Copy memory address of the object
  val v8 = v6()
  mutable = false
  val intValue1 = v7.apply()
  val intValue2 = v8
  assert(v6 == v7)

  /**
   * val Code Block vs def Code Block
   */

  // It is a expression. Return Int.
  {
    1
  }

  // print "hi" and Return Int
  {
    println("Hi") // Side effects
    1
  }

  // Also a expression
  1

  // All expression has the same value! 1!
  assert({
    1
  } == {
    println(1);
    1;
  } && 1 == {
    1
  })

  // A function
  val v9:
    () => Int // Function Type
  =
    () => {
      1
    } // Function Literal

  /*
  Does not Compile. Why?
  val v10: () => Int = {
    1
  }
  Because...
  val v10:
  () => Int // Type = (no parameter) return Int
  =
  { 1 } // Not a literal function. Just a int value
  */

  // This is a method.
  def d2 = 1

  def d3() = 1

  d2 // Execute d2 method, note d2() does not work
  d3() // Execute d3 method

  /**
   * Note the difference!
   *
   * For def { } is a body method, not a literal function and not a code block
   * def() a: typeX {
   * statement // This is a body method
   * expression // This is still a body method, the resulting value need to be typeX.
   * }
   *
   * For val { } is a code block, not a body function
   * The code block is executed when defined.
   * val() a: typeX {
   * statement // Executed once
   * expression // The resulting value need to be typeX.
   * }
   *
   * val() a: typeX {
   * literal typeX // Note the typeX can be a function.
   * }
   */
  def d4: Int = {
    println("Hi") // Always is called
    1
  }

  val v10: Int = {
    println("Hi, I'm a statement in code block") // Once time
    1
  }

  val v11: () => Int = {
    println("Hi, I'm a statement in code block") // Once time, when is declared.
    () => {
      println("Hi, I'm a statement in on function") // Always when is called.
      1
    }
  }

  val v12: () => Int = {
    () => {
      println("Hi, I'm a statement in on function") // Once time
      1
    }
  }

  val v13: () => Int = () => {
    println("Hi, I'm a statement in on function") // Once time
    1
  }

  // d5 is method that execute a function that return Int.
  def d5: Int = v11() // v11.apply(). Always execute v11 when is called.
  d5 // to execute the method, d5() does not work
  def d6(): Int = v11() // Similar a d5 but has a ().
  d6() // to execute the method, d6 does not work (warning)

  // v14 is a int val, not a function.
  val v14: Int = v11() // Execute v11 only once when is defined. Print "Hi" when declared.

  /**
   * d6 is a method that return a function (not execute).
   * To execute v10 call d6.apply() or d6() because d6 return a v10.
   */
  def d7: () => Int = v11

  d7 // return a function
  d7.apply() // execute a returned function
  d7() // syntax sugar for apply

  /**
   * Note: def d is different than def d()
   */
  def d8(): () => Int = v11

  d8() // return a function
  d8().apply() // execute a returned function
  d8()() // syntax sugar for apply (the last ())

  var implemented = false

  def method: Int = {
    println("def Evaluated")
    if (implemented) 1 else ??? // Always Runtime Error when is called and if implemented is false
  }

  /**
   * Lazy: execute the code block when called only once. (Lazy evaluation).
   * The last expression define the value.
   * If an error occurs, in the next call all code block will be executed.
   */
  var lazyCount = 0
  lazy val lazyEvaluation = {
    lazyCount += 1
    println("lazy Evaluated: " + lazyCount)
    if (implemented) 1 else ???
  }

  /**
   * Runtime Error when is defined if implemented is false.
   */
  val value = {
    println("val Evaluated")
    if (implemented) 1 else ???
  }

  /**
   * Conclusion
   *
   * val is a constant, evaluates when defined
   *
   * def is a method, evaluates on call
   *
   * A function is an object. A val can BE a function.
   * A method can RETURN a function (See DynamicReturn).
   *
   * def and val can be very similar when def has no "()".
   * See the difference between d7 and d8.
   *
   */

  val valFunc = () => {
    1
  }

  def defFunc = () => {
    1
  }

  // Very similar behavior but...
  valFunc // Return a object
  defFunc // Return a object

  def defFunc2 = valFunc
  assert(valFunc eq defFunc2) // Is really the equal?

  def defFunc3: () => Int = {
    println("The difference: body method")
    return valFunc
  }

  assert(valFunc eq defFunc3) // Return the same object
  assert(valFunc() eq defFunc3()) // Return the same Int
  // ... but defFunc3() has no the same behavior (print something).

  // {...} it is a code block!!! not a body function.
  val valNotFunc = {
    util.Random.nextInt()
  }

  // {...} it is a body method.
  def defAlwaysIsAMethod = {
    util.Random.nextInt()
  }

  // explicit {...}
  def defAlwaysIsAMethod1: () => Int = {
    () => {
      util.Random.nextInt()
    }
  }

  // Here the body method {...} is implicit
  def defAlwaysIsAMethod2: () => Int = () => {
    util.Random.nextInt()
  }

  def defAlwaysIsAMethodAndMaybeHasAParameter(a :Int): Int => Int = {
    (b) => {
      a + b
    }
  }

  def defDynamicReturn(positive: Boolean): Int => Int = {
    if(positive) _ + 1
    else _ - 1
  }

  def defDynamicReturnExplicit(positive: Boolean): Int => Int = {
    if(positive) (i: Int) => i + 1
    else (i: Int) => i - 1
  }

  val valDynamicReturn: Boolean => (Int => Int) = {
    if(_) _ + 1
    else _ - 1
  }

  val valDynamicReturnExplicit: Boolean => (Int => Int) = {
    (positive: Boolean) => {
      if(positive) (i: Int) => i + 1
      else (i: Int) => i - 1
    }
  }
}