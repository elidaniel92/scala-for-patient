package practice

import scala.util.{Failure, Success, Try}

object DivisionByZero {
  // StackOverFlow https://stackoverflow.com/questions/43938418/scala-division-by-zero-yields-different-results#comment100376679_43942784
  // Scala Doc https://www.scala-lang.org/api/2.13.3/scala/util/Try.html

  val x, y = 0

  Try(x/y).getOrElse(0)
  Try(x/y).recover{ case _: ArithmeticException => 0 }.get
  Try(x/y).toOption

  // StackOverFlow
  def safeDiv: PartialFunction[(Double, Double), Double] = {
    case(a,b) if b != 0.0 => a/b
  }
  // StackOverFlow
  def exec() {
    println(safeDiv.isDefinedAt(1.0, 1.0)) // true
    println(safeDiv.isDefinedAt(1.0, 0.0)) // false
    println(safeDiv(1.0, 1.0))             // 1.0
    println(safeDiv(1.0, 0.0))             // crash
  }

  // Scala Doc
  def divide: Try[Int] = {
    val dividend = Try {
      scala.io.StdIn.readLine("Enter an Int that you'd like to divide:\n").toInt
    }

    val divisor = Try {
      scala.io.StdIn.readLine("Enter an Int that you'd like to divide by:\n").toInt
    }

    val problem = dividend.flatMap(x => divisor.map(y => x/y))

    problem match {
      case Success(v) =>
        println("Result of " + dividend.get + "/"+ divisor.get +" is: " + v)
        Success(v)
      case Failure(e) =>
        println("You must've divided by zero or entered something that's not an Int. Try again!")
        println("Info from the exception: " + e.getMessage)
        divide
    }
  }

  def divisionByZeroIf(x: Int): Int = {
    if(x != 0) 10 / x else 0
  }
  def divisionByZeroGetOrElse(x: Int): Int = {
    Try(10/x).getOrElse(0)
  }
  def divisionByZeroRecover(x: Int): Int = {
    Try(10/x).recover{ case _: ArithmeticException => 0 }.get
  }
  def divisionByZeroToOption(x: Int): Int = {
    Try(10/x).toOption match {
      case Some(result) => result
      case None => 0
    }
  }
  def xsGenerator(zero: Int): Array[Int] = {
    val length = 999999
    val zerosLength = (zero / 100d * length).ceil.toInt
    val zerosArray = new Array[Int](zerosLength)
    val rest = new Array[Int](length - zerosLength)
    zerosArray.concat(rest.map(_ + 1))
  }

  def xsGenerator2(zero: Int): Array[Int] = {
    val length = 999999
    val zerosLength = (zero / 100d * length).ceil.toInt
    val zerosArray = new Array[Int](zerosLength)
    val rest = new Array[Int](length - zerosLength)
    zerosArray.concat(rest.map(_ + 1))
  }



  def test(zero: Int, division: Int => Int): Long = {
    val start = java.lang.System.nanoTime()
    val result = xsGenerator(zero).map(division(_))
    val end = java.lang.System.nanoTime()
    (end - start)
  }

  def testPerformance: Unit = {
    for(i <- 0 to 100 by 20) {
      val list: Map[String, Long] = Map(
        "If" -> test(i, divisionByZeroIf),
        "Get" -> test(i, divisionByZeroGetOrElse),
        "Recover" -> test(i, divisionByZeroRecover),
        "Option" -> test(i, divisionByZeroToOption))
      val sortedList = list.toArray.sortBy(_._2)
      sortedList.foreach {
        case(name, time) => println(s"$name: $time")
      }
      println("")
    }
  }

}

