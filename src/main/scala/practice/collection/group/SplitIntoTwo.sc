import scala.util.Try

case class Person(name: String, gender: String, age: Int)
val people = Array[Person](
  Person("John", "male", 30),
  Person("Linda", "female", 25),
  Person("Robert", "male", 15),
  Person("Daniel", "male", 25),
  Person("Susan", "female", 15),
  Person("Anne", "female", 20))

val peopleX = Array[Person](
  Person("John", "male", 30),
  Person("Linda", "female", 25),
  Person("Robert", "male", 15),
  Person("Daniel", "male", 25),
  Person("Susan", "female", 15),
  Person("Anne", "female", 20),
  Person("X Name", "X Gender", 20))

val numbers = Array(1 to 25: _*)

// Split at is a alternative for take and drop
// grouped can be similar
val (first5, rest) = numbers.splitAt(5)
val takeFirst5 = numbers.take(5)
val dropFirst5 = numbers.drop(5)

// filter and notFilter
val (even, odd) = numbers.partition(_ % 2 == 0)
val (male, female) = people.partition(_.gender == "male")

// (filter and map) and (notFilter and map)
val (left, right) = numbers.partitionMap {
  (x) => if(x % 2 == 0) Left(x) else Right(x * -1)
}

// java.lang.ArithmeticException: / by zero when
people.filter(_.gender == "male").partitionMap {
  (x) => if(x.gender == "male") Left(x.age) else Right(x.age)
} match {
  case(male, female) => (male.sum / male.length, female.sum / female.length)
}

people.filter(_.gender == "male").partitionMap {
  (x) => if(x.gender == "male") Left(x.age) else Right(x.age)
} match {
  case(male, female) => (Try(male.sum / male.length).getOrElse(0), Try(female.sum / female.length).getOrElse(0))
}

// filter and filter, Not split but is useful
// Check Collect Sample
peopleX.collect {
  case(a) if a.gender == "male" => a
  case(b) if b.gender == "X Gender" => b
}

val strArray = Array[String](
  "Begin1", "A", "B", "C", "End",
  "Begin2", "E", "F", "G", "H", "End",
  "Begin3", "I", "J", "End")

// Splits array in Two (takeWhile, dropWhile)
val (begin1, rest) = strArray.span(_ != "Begin2")
