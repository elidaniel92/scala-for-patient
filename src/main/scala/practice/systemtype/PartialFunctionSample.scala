package practice.systemtype

object PartialFunctionSample {
  // PartialFunction[A, B] extend A => B

  val partialFunc1: PartialFunction[Int, String] =
  // Partial Function Anonymous Implementation
    new PartialFunction[Int, String] {
      // Checks if a value is contained in the function's domain.
      override def isDefinedAt(x: Int): Boolean = x != 13 // Unlucky number
      // Function implementation
      override def apply(v1: Int): String = {
        if (isDefinedAt(v1)) s"Day $v1"
        else throw new IllegalArgumentException("The number 13 is synonymous with bad luck!")
      }
    }

  partialFunc1.applyOrElse(13, (number: Int) => s"Bad luck number: $number")
  partialFunc1.apply(13)
  partialFunc1.isDefinedAt(13)

  /**
   * It is possible implement PartialFunction (only apply method)
   * with function literal like bellow. Check Single Abstract Method Types (SAM Types)
   * The Scala compiler implement isDefinedAt method.
   * "override def isDefinedAt(x: Int): Boolean = true"
   */
  val partialFunc2: PartialFunction[Int, String] = (x: Int) => {
    if (x != 13) s"Day $x"
    else throw new IllegalArgumentException(s"The number $x is synonymous with bad luck!")
  }
  partialFunc2.applyOrElse(13, (number: Int) => s"Bad luck number: $number") // Does not work, because isDefinedAt return true
  partialFunc2.apply(13)
  partialFunc2.isDefinedAt(13) // Return true, impl by compiler

  // Pattern Matching Version (Wrong Way)
  val partialFunc3: PartialFunction[Int, String] = {
    case (a) if a != 13 => s"Day $a"
    case (b) => throw new IllegalArgumentException(s"The number $b is synonymous with bad luck!")
  }
  partialFunc3.applyOrElse(13, (number: Int) => s"Bad luck number: $number")
  partialFunc3.apply(13)
  partialFunc3.isDefinedAt(13) // Return true, impl by compiler

  // Pattern Matching Version (Right Way)
  val partialFunc4: PartialFunction[Int, String] = {
    case (day) if day != 13 => s"Day $day"
  }
  partialFunc4.applyOrElse(13, (number: Int) => s"Bad luck number: $number") // It's works
  partialFunc4.apply(13) // Error
  partialFunc4.isDefinedAt(13) // Return False

  /**
   * How is isDefinedAt method implemented?
   * def isDefinedAt(x: A): Boolean = {
   * case(day) if day != 13 => true // no apply method execution
   * case(_) => false
   * }
   */
  var sideEffects = 0
  val partialFunc5: PartialFunction[Int, String] = {
    case (day) if day != 13 => {
      println("Side Effects")
      sideEffects += 1
      s"Day $day"
    }
  }
  partialFunc5.applyOrElse(13, (number: Int) => s"Bad luck number: $number") // No Side Effects for 13 parameter
  partialFunc5.apply(13) // Side Effects for number different of 13
  partialFunc5.isDefinedAt(13) // No Side Effects

  val isBadLuckNumber: Int => Boolean = (n) => {
    println("Nontrivial Function: Poor Performance");
    n == 13
  }

  /**
   * Note that expression pf.applyOrElse(x, default) is equivalent to
   * if(pf isDefinedAt x) pf(x) else default(x)
   *
   * ...with nontrivial `isDefinedAt` method
   * it is recommended to override `applyOrElse` with custom implementation that avoids
   * double `isDefinedAt` evaluation. This may result in better performance
   * and more predictable behavior w.r.t. side effects.
   *
   * One alternative is use a pattern matching function literal.
   */
  val partialFunc6: PartialFunction[Int, String] =
  // Partial Function Anonymous Implementation
    new PartialFunction[Int, String] {
      // Checks if a value is contained in the function's domain.
      override def isDefinedAt(x: Int): Boolean = !isBadLuckNumber(x) // Unlucky number
      // Function implementation
      override def apply(v1: Int): String = {
        if (isDefinedAt(v1)) s"Day $v1"
        else throw new IllegalArgumentException("The number 13 is synonymous with bad luck!")
      }
    }
  partialFunc6.applyOrElse(1, (number: Int) => s"Bad luck number: $number") // Double `isDefinedAt` evaluation

  // pattern matching function literal
  val partialFunc7: PartialFunction[Int, String] = {
    case (day) if !isBadLuckNumber(day) => s"Day $day"
  }
  partialFunc7.applyOrElse(1, (number: Int) => s"Bad luck number: $number") // Note Double `isDefinedAt` evaluation
}