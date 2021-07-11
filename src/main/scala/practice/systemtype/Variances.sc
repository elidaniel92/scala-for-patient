/**
 * class Foo[+A] // A covariant class
 * class Bar[-A] // A contravariant class
 * class Baz[A]  // An invariant class
 * */

// Covariance

abstract class Animal {
  def name: String
}
case class Cat(name: String) extends Animal
case class Dog(name: String) extends Animal

class ListOfHashCode[+A](array: Array[A]) {
  def printAll = array.map(_.hashCode()).foreach(println)
}

def method(animal: Animal) = {
  new ListOfHashCode[Animal]()
}