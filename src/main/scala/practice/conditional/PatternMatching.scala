package practice.conditional

object PatternMatching {
  /**
   * val value = expression match {
   *   case(literalValue or Type or type is inferred) => expression
   *   case(x: Int) if x != 0 => expression
   *   case(_) => expression
   * }
   */

  val helloUSA = "Hello USA!"

  // Match Case is a expression
  val helloWorld1 = helloUSA.replace("USA", "World")
  val helloWorld2 = helloUSA match {
    case (x) => x.replace("USA", "World")
  }

  val result1 = 10 / 2
  val result2 = 2 match {
    case x => 10 / x // Optional Round Bracket
  }

  val result3 = (10, 2) match {
    case x => x._1 / x._2
  }
  // Destruct Tuple
  val result4 = (10, 2) match {
    case(a, b) => a / b
  }
  // Similar with function literal
  val result5 = ((x: (Int, Int)) => {
    x._1 / x._2
  }).apply(10, 2)

  /**
   * Pattern Matching as function literal
   */
  // Not Anonymous only literal, below not compile
  // case(a, b) => a + b
  // case(a: Int, b: Int) => a + b
  // (Int, Int) => case(a, b) => a + b
  // (Int, Int) => { case(a: Int, b: Int) => a + b }
  //  val patternMatchingFunc1: (Int, Int) => Int = case(a, b) => a + b // Not compile, need {...}
  //  Note the difference with function literal: (a, b) => a + b

  // Maybe the compiler create a literal function for pattern matching
  val patternMatchingExplicit: (Int, Int) => Int = (a: Int, b: Int) => {
    (a, b) match {
      case(a: Int, b: Int) => a / b
    }
  }
  // Is there a literal functions implicit? Is it a syntactic sugar?
  val patternMatchingLiteralFunction: (Int, Int) => Int = {
      case(a: Int, b: Int) => a / b
  }

  // Not allow div by 0
  val patternMatching1: (Int, Int) => Int = {
    case(a, 0) => 0 // if Any Int and 0 return 0
    case(a, b) => a / b // else a / b
  }

  // If in case, not inside case code block
  val patternMatching2: (Int, Int) => Int = {
    case(a, b) if b == 0 => 0 // Explicit if. Note: It is not case(a, b) => if...
    case(a, b) => a / b
  }

  // Explicit RuntimeException for unmatched case
  val patternMatching3: (Int, Int) => Int = {
    case(a, b) if b != 0 => a / b
    // A wildcard pattern is used to match the unmatched case
    case(_) => throw new scala.MatchError("The parameter b can not be zero!")
  }

  /**
   * There is not option for b == 0
   * For unmatched case the Pattern Matching
   * will throw a RuntimeException scala.MatchError.
   */
  val patternMatching4: (Int, Int) => Int = {
    case(a, b) if b != 0 => a / b
  }

  // The compiler do something similar (patternMatching4)
  val patternMatching5: (Int, Int) => Int = (a, b) => {
    if (b != 0) {
      a / b
    } else {
      throw new scala.MatchError(a,b)
    }
  }

  // Single Abstract Method Types (SAM Types)
  trait Hello {
    def method(name: String): Unit
  }
  val caseFuncSAM: Hello = {
    case(name) => println(s"Hello $name")
  }

  // null values
  val nullValue: String = null
  nullValue match {
    case str: String => println(str)
    case _ => println("It's not a String")
  }

}
